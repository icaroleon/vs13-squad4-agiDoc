import data.Data;
import navigation.Navigation;

import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws Exception {
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

            // TODO: Validar informações de login; Verificar se será usado método login do entities.employee.Employee
            isNotValidLogin = false;
        }

        boolean running = true;
        Navigation nav = new Navigation();
        Data.seed();

        while (running) {
            if (history.isEmpty()) {
                String navigationOption = nav.showMenu("0");

                switch (navigationOption) {
                    case "0" -> running = false;
                    case "1", "2" -> history.push(navigationOption);
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
