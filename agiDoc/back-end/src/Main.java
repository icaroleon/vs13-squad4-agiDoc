import controller.UserController;
import model.user.User;
import navigation.Navigation;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean isNotValidLogin = true;
        User user = new User();
        Stack<String> history = new Stack<>();
        Navigation nav = new Navigation();
        boolean running = true;

        while (running) {
            try {
                System.out.println("+-------------------------------------------+");
                System.out.println("|              Menu | agiDoc                |");
                System.out.println("+-------------------------------------------+");
                System.out.println("| 1 - Login                                 |");
                System.out.println("| 2 - Criar novo usuário                    |");
                System.out.println("+-------------------------------------------+");
                System.out.print("Escolha uma opção: ");
                int option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1:
                        do {
                            System.out.println("+-------------------------------------------+");
                            System.out.println("|              Login | agiDoc               |");
                            System.out.println("+-------------------------------------------+");
                            System.out.println("| 9 - Voltar                                |");
                            System.out.println("+-------------------------------------------+");

                            System.out.print("Digite seu usuário: ");
                            String userName = scanner.nextLine();
                            if (userName.equals("9")) break;

                            System.out.print("Digite sua senha: ");
                            String password = scanner.nextLine();
                            if (password.equals("9")) break;

                            if (UserController.login(userName, password)) {
                                isNotValidLogin = false;
                                running = false;
                            } else {
                                System.out.println("Usuário não existe ou credenciais inválidas. Tente novamente.");
                            }
                        } while (isNotValidLogin);
                        break;

                    case 2:
                        System.out.println("+-------------------------------------------+");
                        System.out.println("|     Criar novo usuário | agiDoc           |");
                        System.out.println("+-------------------------------------------+");
                        System.out.println("| 9 - Voltar                                 |");
                        System.out.println("+-------------------------------------------+");

                        System.out.print("Digite o nome: ");
                        String name = scanner.nextLine().trim();
                        if (name.equals("9")) break;
                        user.setName(name);

                        System.out.print("Digite o nome de usuário: ");
                        String userUser = scanner.nextLine().trim();
                        if (userUser.equals("9")) break;
                        user.setUser(userUser);

                        System.out.print("Digite a senha: ");
                        String password = scanner.nextLine().trim();
                        if (password.equals("9")) break;
                        user.setPassword(password);

                        System.out.print("Digite o cargo: ");
                        String position = scanner.nextLine().trim();
                        if (position.equals("9")) break;
                        user.setPosition(position);

                        try {
                            UserController.addUser(user);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    default:
                        System.out.println("Opção inválida. Por favor, tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, insira uas opções acima.");
                scanner.nextLine();
            }
        }
        running = true;
        while (running) {
            if (history.isEmpty()) {
                String navigationOption = nav.showMenu("0");

                switch (navigationOption) {
                    case "0" -> running = false;
                    case "1", "2", "3" -> history.push(navigationOption);
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
