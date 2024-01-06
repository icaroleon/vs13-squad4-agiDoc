package navigation;

import java.util.Scanner;

public class Navigation {
    private Scanner scanner;

    public Navigation() {
        this.scanner = new Scanner(System.in);
    }

    public String showMenu(String option) {
        String chooseNavigation = "";

        switch (option) {
            case "2" -> chooseNavigation = this.employeeMenu();
        }

        return chooseNavigation;
    }

    public String employeeMenu() {
        String option = "";
        boolean running = true;

        do {
            System.out.println("\n\n");
            System.out.println("+-------------------------------------------+");
            System.out.println("|           agiDoc | Funcionários           |");
            System.out.println("+-------------------------------------------+");
            System.out.println("| 1 - Cadastrar funcionário                 |");
            System.out.println("| 2 - Listar funcionários                   |");
            System.out.println("| 2 - Editar funcionário                    |");
            System.out.println("| 2 - Excluir funcionário                   |");
            System.out.println("|                                           |");
            System.out.println("| 0 - Sair                                  |");
            System.out.println("| 9 - Voltar                                |");
            System.out.println("+-------------------------------------------+");
            System.out.print("Digite uma opção: ");
            option = scanner.nextLine();

            switch (option) {
                case "1" -> System.out.println();
                case "2" -> System.out.println();
                case "3" -> System.out.println();
                case "4" -> System.out.println();
                case "0", "9" -> running = false;
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (running);

        return option;
    }
}
