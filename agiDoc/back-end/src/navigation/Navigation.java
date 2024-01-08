package navigation;

import controller.DocumentController;
import controller.CompetitorController;
import controller.EmployeeController;
import controller.ProcessController;
import entities.process.Process;

import java.util.Scanner;

public class Navigation {
    private Scanner scanner;
    private String inputProcessId;
    private Process currentProcess;

    public Navigation() {
        this.scanner = new Scanner(System.in);
    }

    public String showMenu(String option) throws Exception {
        this.scanner = new Scanner(System.in);
        String chooseNavigation = "";

        switch (option) {
            case "0" -> chooseNavigation = this.mainMenu();
            case "1" -> chooseNavigation = this.processesMenu();
            case "2" -> chooseNavigation = this.employeesMenu();
            case "3" -> chooseNavigation = this.oneProcessMenu();
            case "5" -> chooseNavigation = this.documentsMenu();
            case "6" -> chooseNavigation = this.competitorsMenu();
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
        System.out.println("| 2 - Funcionários                          |");
        System.out.println("|                                           |");
        System.out.println("| 0 - Sair                                  |");
        System.out.println("+-------------------------------------------+");
        System.out.print("Digite uma opção: ");
        option = scanner.nextLine();

        return option;
    }

    public String employeesMenu() throws Exception {
        String option;
        boolean running = true;

        do {
            System.out.println("\n\n");
            System.out.println("+-------------------------------------------+");
            System.out.println("|           agiDoc | Funcionários           |");
            System.out.println("+-------------------------------------------+");
            System.out.println("| 1 - Cadastrar funcionário                 |");
            System.out.println("| 2 - Listar funcionários                   |");
            System.out.println("| 3 - Editar funcionário                    |");
            System.out.println("| 4 - Remover funcionário                   |");
            System.out.println("|                                           |");
            System.out.println("| 0 - Sair                                  |");
            System.out.println("| 9 - Voltar                                |");
            System.out.println("+-------------------------------------------+");
            System.out.print("Digite uma opção: ");
            option = scanner.nextLine();

            switch (option) {
                case "1" -> EmployeeController.createEmployee();
                case "2" -> EmployeeController.getAll();
                case "3" -> EmployeeController.update();
                case "4" -> EmployeeController.delete();
                case "0", "9" -> running = false;
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (running);

        return option;
    }

    public String processesMenu() {
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
                    System.out.print("Digite o identificador do processo: ");
                    this.inputProcessId = scanner.nextLine();
                    currentProcess = ProcessController.get(inputProcessId);
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
            System.out.printf("|           agiDoc | Processo: %s       |\n", this.inputProcessId);
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
                case "1" -> ProcessController.closeProcess(inputProcessId);
                case "2" -> ProcessController.chooseCompetitor(inputProcessId);
                case "3" -> ProcessController.update(inputProcessId);
                case "4" -> {
                    boolean isDeleted = ProcessController.delete(inputProcessId);

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

    public String documentsMenu() {
        DocumentController document = new DocumentController(currentProcess.getDocuments());
        String option;
        boolean running = true;

        do {
            System.out.println("\n\n");
            System.out.println("+-------------------------------------------+");
            System.out.println("|            agiDoc | Documentos            |");
            System.out.println("+-------------------------------------------+");
            System.out.println("| 1 - Adicionar Documento                   |");
            System.out.println("| 2 - Listar Documentos                     |");
            System.out.println("| 3 - Pesquisar um documento                |");
            System.out.println("| 4 - Editar Documento                      |");
            System.out.println("| 5 - Excluir Documento                     |");
            System.out.println("| 6 - Assinar Documento                     |");
            System.out.println("|                                           |");
            System.out.println("| 0 - Sair                                  |");
            System.out.println("| 9 - Voltar                                |");
            System.out.println("+-------------------------------------------+");
            System.out.print("Digite uma opção: ");
            option = scanner.nextLine();

            switch (option) {
                case "1" -> document.createDocument(currentProcess, inputProcessId);
                case "2" -> document.getAllDocuments();
                case "3" -> document.getDocument();
                case "4" -> document.updateDocument(currentProcess, inputProcessId);
                case "5" -> document.deleteDocument(currentProcess);
                case "6" -> document.signDocument(currentProcess);
                case "0", "9" -> running = false;
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (running);

        return option;
    }

    public String competitorsMenu() {
        CompetitorController competitor = new CompetitorController(currentProcess.getCompetitors());
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
                case "1" -> competitor.createCompetitor(currentProcess);
                case "2" -> competitor.getAll();
                case "3" -> competitor.update(currentProcess);
                case "4" -> competitor.delete(currentProcess);
                case "0", "9" -> running = false;
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (running);

        return option;
    }
}
