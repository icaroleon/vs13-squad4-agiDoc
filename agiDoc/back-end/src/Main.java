import navigation.Navigation;

import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Stack<String> history = new Stack<>();
        boolean isNotValidLogin = true;

        while (isNotValidLogin) {
            System.out.println("+-------------------------------------------+");
            System.out.println("|              Login | agiDoc               |");
            System.out.println("+-------------------------------------------+");
            System.out.print("Digite seu usuários: ");
            String user = scanner.nextLine();

            System.out.print("Digite sua senha: ");
            String password = scanner.nextLine();

            // TODO: Validar informações de login; Verificar se será usado método login do Employee
            isNotValidLogin = false;
        }

        boolean running = true;
        Navigation nav = new Navigation();

        while (running) {
            if (history.isEmpty()) {
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
                String option = scanner.nextLine();

                switch (option) {
                    case "0" -> running = false;
                    case "1", "2" -> history.push(option);
                    default -> System.out.println("Opção inválida! Tente novamente.");
                }
            } else {
                String navigationOption = nav.showMenu(history.peek());

                switch (navigationOption) {
                    case "9" -> history.pop();
                    case "0" -> running = false;
                    default -> history.push(navigationOption);
                }
            }
        }
    }
}