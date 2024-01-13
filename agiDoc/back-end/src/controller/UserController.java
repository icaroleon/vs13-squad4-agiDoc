package controller;

import database.Data;
import model.user.User;
import model.institution.Institution;
import service.UserService;

import java.util.ArrayList;
import java.util.Scanner;

public class UserController {

    private static String registration;
    private static String name;
    private static String user;
    private static String password;
    private static String address;
    private static String contact;

    private static final Institution institutionData = Data.institution;
    private static final Scanner scanner = new Scanner(System.in);
    private static final UserService USER_SERVICE = new UserService(Data.institution.getEmployees());

    public static void createEmployee(){

        System.out.print("Digite o nome do funcionário: ");
        name = scanner.nextLine();

        System.out.print("Digite o nome de usuário: ");
        user = scanner.nextLine();

        System.out.print("Digite a senha: ");
        password = scanner.nextLine();

        System.out.print("Digite o endereço: ");
        address = scanner.nextLine();

        System.out.print("Digite o contato: ");
        contact = scanner.nextLine();

        User newUser = new User(name, user, password, address, contact, institutionData);
        USER_SERVICE.create(newUser);

        Data.institution.setEmployees(USER_SERVICE.getAll());

        System.out.print(newUser.toString());
    }

    public static void get() {
        try {
            System.out.print("Digite a matrícula do colaborador que deseja pesquisar: ");
            registration = scanner.nextLine();
            USER_SERVICE.get(registration);
        } catch (Exception e) {
            System.out.print("Colaborador não encontrado");
        }
    }

    public static void getAll(){
        for (User user : USER_SERVICE.getAll()) {
            System.out.print(user.toString());
        }
    }

    public static void update() {

        try {
            System.out.print("Os funcionários cadastrados na instituição são: ");
            System.out.print(USER_SERVICE.getAll());
            System.out.println();
            System.out.println();
            System.out.print("Digite o nome da matrícula que quer fazer as alterações: ");
            registration = scanner.nextLine();
            User existingUser = USER_SERVICE.get(registration);

            System.out.print("Digite o novo nome. Caso queira que permaneça a " +
                    "mesma informação, apenas deixe em branco: ");
            name = scanner.nextLine();
            name = (name.isEmpty()) ? existingUser.getName() : name;

            System.out.print("Digite o novo user. Caso queira que permaneça a " +
                    "mesma informação, apenas deixe em branco: ");
            user = scanner.nextLine();
            user = (user.isEmpty()) ? existingUser.getUser() : user;

            System.out.print("Digite a nova senha. Caso queira que permaneça a " +
                    "mesma informação, apenas deixe em branco: ");
            password = scanner.nextLine();
            password = (password.isEmpty()) ? existingUser.getPassword() : password;

            System.out.print("Digite o endereço. Caso queira que permaneça a " +
                    "mesma informação, apenas deixe em branco: ");
            address = scanner.nextLine();
            address = (address.isEmpty()) ? existingUser.getPassword() : address;

            System.out.print("Digite o contato: Caso queira que permaneça as " +
                    "mesmas informações, apenas deixe em branco: ");
            contact = scanner.nextLine();
            contact = (contact.isEmpty()) ? existingUser.getContact() : contact;

            User userToUpdate = new User(name, user, password, address, contact);
            USER_SERVICE.update(registration, userToUpdate).toString();
        } catch (Exception e) {
            System.out.print("Erro: " + e.getMessage());
        }
    }

    public static void delete() {
        try {
            System.out.println("Os funcionários cadastrados na instituição são: ");
            System.out.print(USER_SERVICE.getAll());
            System.out.println();
            System.out.println();
            System.out.print("Digite a matrícula do funcionário que quer deletar: ");
            registration = scanner.nextLine();
            System.out.print("Tem certeza que quer deletar? (Y/N) ");
            String confirmation = scanner.nextLine().toLowerCase();
            if (confirmation.equals("y")){
                USER_SERVICE.delete(registration);
                System.out.println("Deletando...");
                System.out.print("Colaborador excluído!");
            } else if (confirmation.equals("n")) {
                System.out.println("Operação cancelada.");
            } else {
                System.out.println("Entrada inválida. Operação cancelada.");
            }
        } catch (Exception e) {
            System.out.print("Colaborador não encontrado!");
        }
    }

    public static boolean login(String user, String password){
        ArrayList<User> listaUsers = USER_SERVICE.getAll();
        for(User employee : listaUsers){
            if(employee.getUser().equals(user) && employee.getPassword().equals(password)) {
                System.out.println("Login efetuado!");
                return true;
            }
        }
        System.out.println("Usuário ou senha errados");
        return false;
    }
}
