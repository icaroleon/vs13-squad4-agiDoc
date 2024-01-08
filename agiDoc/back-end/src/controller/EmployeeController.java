package controller;

import data.Data;
import entities.employee.Employee;
import entities.institution.Institution;
import service.EmployeeService;

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

        System.out.println("Digite o nome do funcionário: ");
        name = scanner.nextLine();

        System.out.println("Digite o nome de usuário: ");
        user = scanner.nextLine();

        System.out.println("Digite a senha: ");
        password = scanner.nextLine();

        System.out.println("Digite o endereço: ");
        address = scanner.nextLine();

        System.out.println("Digite o contato: ");
        contact = scanner.nextLine();

        Employee newEmployee = new Employee(name, user, password, address, contact, institutionData);
        employeeService.create(newEmployee);

        Data.institution.setEmployees(employeeService.getAll());

        getAll();
    }

    public static void get() throws Exception {
        System.out.println("Digite a matrícula do colaborador que deseja pesquisar: ");
        registration = scanner.nextLine();
        employeeService.get(registration);
    }

    public static void getAll(){
        for (Employee employee : EmployeeService.getAll()) {
            System.out.println(employee.toString());
        }
    }

    public static void update() throws Exception {

        System.out.println("Digite o nome da matrícula que quer fazer as alterações: ");
        registration = scanner.nextLine();
        Employee existingEmployee = employeeService.get(registration);

        System.out.println("Digite o nome do funcionário: ");
        name = scanner.nextLine();
        name = (name.isEmpty()) ? existingEmployee.getName() : name;

        System.out.println("Digite o nome de usuário: ");
        user = scanner.nextLine();
        user = (user.isEmpty()) ? existingEmployee.getUser() : user;

        System.out.println("Digite a senha: ");
        password = scanner.nextLine();
        password = (password.isEmpty()) ? existingEmployee.getPassword() : password;

        System.out.println("Digite o endereço: ");
        address = scanner.nextLine();
        address = (address.isEmpty()) ? existingEmployee.getPassword() : address;

        System.out.println("Digite o contato: ");
        contact = scanner.nextLine();
        contact = (contact.isEmpty()) ? existingEmployee.getContact() : contact;

        try {
            Employee employeeToUpdate = new Employee(name, user, password, address, contact);
            employeeService.update(registration, employeeToUpdate).toString();
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public static void delete() throws Exception {
        try {
            System.out.println("Digite a matrícula do funcionário que quer deletar: ");
            registration = scanner.nextLine();
            employeeService.delete(registration);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
