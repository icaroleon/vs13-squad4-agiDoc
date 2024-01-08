package controller;

import data.Data;
import entities.competitor.Competitor;
import entities.employee.Employee;
import entities.institution.Institution;
import service.EmployeeService;

import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeController {

    private static String registration;
    private static String name;
    private static String user;
    private static String password;
    private static String address;
    private static String contact;

    private static final Institution institutionData = Data.institution;
    private static final Scanner scanner = new Scanner(System.in);
    private static final EmployeeService employeeService = new EmployeeService(Data.institution.getEmployees());

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

        Employee newEmployee = new Employee(name, user, password, address, contact, institutionData);
        employeeService.create(newEmployee);

        Data.institution.setEmployees(employeeService.getAll());

        System.out.print(newEmployee.toString());
    }

    public static void get() {
        try {
            System.out.print("Digite a matrícula do colaborador que deseja pesquisar: ");
            registration = scanner.nextLine();
            employeeService.get(registration);
        } catch (Exception e) {
            System.out.print("Colaborador não encontrado");
        }
    }

    public static void getAll(){
        for (Employee employee : employeeService.getAll()) {
            System.out.print(employee.toString());
        }
    }

    public static void update() {

        try {
            System.out.print("Os funcionários cadastrados na instituição são: ");
            System.out.print(EmployeeService.getAll());
            System.out.println();
            System.out.println();
            System.out.print("Digite o nome da matrícula que quer fazer as alterações: ");
            registration = scanner.nextLine();
            Employee existingEmployee = employeeService.get(registration);

            System.out.print("Digite o novo nome. Caso queira que permaneça a " +
                    "mesma informação, apenas deixe em branco: ");
            name = scanner.nextLine();
            name = (name.isEmpty()) ? existingEmployee.getName() : name;

            System.out.print("Digite o novo user. Caso queira que permaneça a " +
                    "mesma informação, apenas deixe em branco: ");
            user = scanner.nextLine();
            user = (user.isEmpty()) ? existingEmployee.getUser() : user;

            System.out.print("Digite a nova senha. Caso queira que permaneça a " +
                    "mesma informação, apenas deixe em branco: ");
            password = scanner.nextLine();
            password = (password.isEmpty()) ? existingEmployee.getPassword() : password;

            System.out.print("Digite o endereço. Caso queira que permaneça a " +
                    "mesma informação, apenas deixe em branco: ");
            address = scanner.nextLine();
            address = (address.isEmpty()) ? existingEmployee.getPassword() : address;

            System.out.print("Digite o contato: Caso queira que permaneça as " +
                    "mesmas informações, apenas deixe em branco: ");
            contact = scanner.nextLine();
            contact = (contact.isEmpty()) ? existingEmployee.getContact() : contact;

            Employee employeeToUpdate = new Employee(name, user, password, address, contact);
            employeeService.update(registration, employeeToUpdate).toString();
        } catch (Exception e) {
            System.out.print("Erro: " + e.getMessage());
        }
    }

    public static void delete() {
        try {
            System.out.println("Os funcionários cadastrados na instituição são: ");
            System.out.print(EmployeeService.getAll());
            System.out.println();
            System.out.println();
            System.out.print("Digite a matrícula do funcionário que quer deletar: ");
            registration = scanner.nextLine();
            System.out.print("Tem certeza que quer deletar? (Y/N) ");
            String confirmation = scanner.nextLine().toLowerCase();
            if (confirmation.equals("y")){
                employeeService.delete(registration);
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
}
