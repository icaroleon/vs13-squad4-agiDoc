package navigation;

import controller.CompetitorController;
import controller.InstitutionController;
import controller.UserController;
import controller.ProcessController;
import exception.DatabaseException;
import model.process.Process;
import model.user.User;

import java.util.Scanner;

public class Navigation {
    private Scanner scanner;
    private int inputProcessId;
    private Process currentProcess;

    public Navigation() {
        this.scanner = new Scanner(System.in);
    }

    public String showMenu(String option) throws DatabaseException {
        this.scanner = new Scanner(System.in);
        String chooseNavigation = "";

        switch (option) {
            case "0" -> chooseNavigation = this.mainMenu();
            case "1" -> chooseNavigation = this.processesMenu();
            case "2" -> chooseNavigation = this.userMenu();
            case "3" -> chooseNavigation = this.oneProcessMenu();
        //    case "5" -> chooseNavigation = this.documentsMenu();
            case "6" -> chooseNavigation = this.competitorsMenu();
            case "7" -> chooseNavigation = this.institutionMenu();
        }

        return chooseNavigation;
    }

    public String mainMenu() {
        String option;

        System.out.println("\n\n");
        System.out.println("+-------------------------------------------+");
        System.out.println("|                  agiDoc                   |");
        System.out.println("+-------------------------------------------+");
        System.out.println("| 1 - Processos                             |");
        System.out.println("| 2 - Usuários                              |");
        System.out.println("| 7 - Instituição                           |");
        System.out.println("|                                           |");
        System.out.println("| 0 - Sair                                  |");
        System.out.println("+-------------------------------------------+");
        System.out.print("Digite uma opção: ");
        option = scanner.nextLine();

        return option;
    }

    public String userMenu() {
        String option;
        boolean running = true;

        do {
            System.out.println("\n\n");
            System.out.println("+-------------------------------------------+");
            System.out.println("|           agiDoc | Usuários               |");
            System.out.println("+-------------------------------------------+");
            System.out.println("| 1 - Listar usuários                       |");
            System.out.println("| 2 - Listar um usuário específico          |");
            System.out.println("| 3 - Editar usuário                        |");
            System.out.println("| 4 - Remover usuário                       |");
            System.out.println("|                                           |");
            System.out.println("| 0 - Sair                                  |");
            System.out.println("| 9 - Voltar                                |");
            System.out.println("+-------------------------------------------+");
            System.out.print("Digite uma opção: ");
            option = scanner.nextLine();

            User user = new User();
            switch (option) {
                case "1" -> UserController.getUsers();
                case "2" -> {
                    System.out.print("Digite o ID do usuário para obter: ");
                    int idUser = scanner.nextInt();
                    scanner.nextLine();
                    UserController.getUserById(idUser);
                }
                case "3" -> {
                    System.out.print("Digite o ID do usuário para editar: ");
                    int userId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Digite o nome: ");
                    user.setName(scanner.nextLine().trim());

                    System.out.print("Digite o nome de usuário: ");
                    user.setUser(scanner.nextLine().trim());

                    System.out.print("Digite a senha: ");
                    user.setPassword(scanner.nextLine());

                    System.out.print("Digite o cargo: ");
                    user.setPosition(scanner.nextLine());

                    UserController.editUser(userId, user);
                }
                case "4" -> {
                    System.out.print("Digite o ID do usuário para remover: ");
                    int userId = scanner.nextInt();
                    scanner.nextLine();
                    UserController.removeUser(userId);
                }

                case "0", "9" -> running = false;
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (running);

        return option;
    }

    public String processesMenu() throws DatabaseException {
        String option;
        boolean running = true;

        do {
            System.out.println("\n\n");
            System.out.println("+-------------------------------------------+");
            System.out.println("|            agiDoc | Processos             |");
            System.out.println("+-------------------------------------------+");
            System.out.println("| 1 - Listar Processos                      |");
            System.out.println("| 2 - Acessar Processo                      |");
            System.out.println("| 3 - Criar Processo                        |");
            System.out.println("|                                           |");
            System.out.println("| 0 - Sair                                  |");
            System.out.println("| 9 - Voltar                                |");
            System.out.println("+-------------------------------------------+");
            System.out.print("Digite uma opção: ");
            option = scanner.nextLine();

            switch (option) {
                case "1" -> ProcessController.getAll();
                case "2" -> {
                    System.out.print("Digite o número do processo: ");
                    String inputProcessNumber = scanner.nextLine();
                    currentProcess = ProcessController.getProcessByNumber(inputProcessNumber);
                    inputProcessId = currentProcess.getId();
                    if(currentProcess != null){
                        option = "3";
                        running = false;
                    }
                }
                case "3" -> ProcessController.createProcess();

                case "9", "0" -> running = false;
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (running);

        return option;
    }

    public String oneProcessMenu() {
        String option;
        boolean running = true;

        do {
            System.out.println("\n\n");
            System.out.println("+-------------------------------------------+");
            System.out.printf("|           agiDoc | Processo: %s       |\n", this.currentProcess.getProcessNumber());
            System.out.println("+-------------------------------------------+");
            System.out.println("| 1 - Encerrar Processo                     |");
            System.out.println("| 2 - Eleger Contratado                     |");
            System.out.println("| 3 - Editar Processo                       |");
            System.out.println("| 4 - Excluir Processo                      |");
            System.out.println("|                                           |");
            System.out.println("| 5 - Documentos                            |");
            System.out.println("| 6 - Concorrentes                          |");
            System.out.println("|                                           |");
            System.out.println("| 0 - Sair                                  |");
            System.out.println("| 9 - Voltar                                |");
            System.out.println("+-------------------------------------------+");
            System.out.print("Digite uma opção: ");
            option = scanner.nextLine();

            switch (option) {
                case "1" -> ProcessController.closeProcess(Integer.valueOf(inputProcessId));
                case "2" -> ProcessController.chooseCompetitor(Integer.valueOf(inputProcessId));
                case "3" -> ProcessController.update(Integer.valueOf(inputProcessId));
                case "4" -> {
                    boolean isDeleted = ProcessController.delete(Integer.valueOf(inputProcessId));

                    if (isDeleted) {
                        option = "9";
                        running=false;
                    }
                }
                case "5", "6", "9", "0" -> running = false;
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (running);

        return option;
    }

//    public String documentsMenu() {
//        DocumentController document = new DocumentController(currentProcess.getDocuments());
//        String option;
//        boolean running = true;
//
//        do {
//            System.out.println("\n\n");
//            System.out.println("+-------------------------------------------+");
//            System.out.println("|            agiDoc | Documentos            |");
//            System.out.println("+-------------------------------------------+");
//            System.out.println("| 1 - Adicionar Documento                   |");
//            System.out.println("| 2 - Listar Documentos                     |");
//            System.out.println("| 3 - Pesquisar um documento                |");
//            System.out.println("| 4 - Editar Documento                      |");
//            System.out.println("| 5 - Excluir Documento                     |");
//            System.out.println("| 6 - Assinar Documento                     |");
//            System.out.println("|                                           |");
//            System.out.println("| 0 - Sair                                  |");
//            System.out.println("| 9 - Voltar                                |");
//            System.out.println("+-------------------------------------------+");
//            System.out.print("Digite uma opção: ");
//            option = scanner.nextLine();
//
//            switch (option) {
//                case "1" -> document.createDocument(currentProcess, String.valueOf(inputProcessId));
//                case "2" -> document.getAllDocuments();
//                case "3" -> document.getDocument();
//                case "4" -> document.updateDocument(currentProcess, String.valueOf(inputProcessId));
//                case "5" -> document.deleteDocument(currentProcess);
//                case "6" -> document.signDocument(currentProcess);
//                case "0", "9" -> running = false;
//                default -> System.out.println("Opção inválida. Tente novamente.");
//            }
//        } while (running);
//
//        return option;
//    }

    public String competitorsMenu() {
        CompetitorController competitor = new CompetitorController();
        String option;
        boolean running = true;

        do {
            System.out.println("\n\n");
            System.out.println("+-------------------------------------------+");
            System.out.println("|           agiDoc | Concorrentes           |");
            System.out.println("+-------------------------------------------+");
            System.out.println("| 1 - Adicionar Concorrente                 |");
            System.out.println("| 2 - Listar Concorrentes                   |");
            System.out.println("| 3 - Editar Concorrente                    |");
            System.out.println("| 4 - Excluir Concorrente                   |");
            System.out.println("|                                           |");
            System.out.println("| 0 - Sair                                  |");
            System.out.println("| 9 - Voltar                                |");
            System.out.println("+-------------------------------------------+");
            System.out.print("Digite uma opção: ");
            option = scanner.nextLine();

            switch (option) {
                case "1" -> competitor.createCompetitor(inputProcessId);
                case "2" -> competitor.listAll(inputProcessId);
                case "3" -> competitor.update();
                case "4" -> competitor.delete();
                case "0", "9" -> running = false;
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (running);

        return option;
    }

    public String institutionMenu() throws DatabaseException {
        InstitutionController institution = new InstitutionController();
        String option;
        boolean running = true;

        do {
            System.out.println("\n\n");
            System.out.println("+-------------------------------------------+");
            System.out.println("|            agiDoc | Instituição           |");
            System.out.println("+-------------------------------------------+");
            System.out.println("| 1 - Editar Instituição                    |");
            System.out.println("|                                           |");
            System.out.println("| 0 - Sair                                  |");
            System.out.println("| 9 - Voltar                                |");
            System.out.println("+-------------------------------------------+");
            System.out.print("Digite uma opção: ");
            option = scanner.nextLine();

            switch (option) {
                case "1" -> institution.update();
                case "9", "0" -> running = false;
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (running);

        return option;
    }
}
