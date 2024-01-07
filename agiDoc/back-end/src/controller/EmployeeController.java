package controller;

import data.Data;
import entities.employee.Employee;
import entities.institution.Institution;
import entities.juridical.AbstractJuridical;
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
    private static final EmployeeService employeeService = new EmployeeService();
    private static final Institution institution = new Institution();

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

        System.out.println(employeeService.getAll().toString());
    }

    public static void get() throws Exception {
        System.out.println("Digite a matrícula do colaborador que deseja pesquisar: ");
        registration = scanner.nextLine();
        System.out.println(employeeService.get(registration).toString());
    }

    public static void getAll(){
        System.out.println((employeeService.getAll().toString()));
    }

    public static void update() throws Exception{

        System.out.println("Digite o nome da matrícula que quer fazer as alterações: ");
        registration = scanner.nextLine();

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

        Employee newEmployee = new Employee(name, user, password, address, contact);
        employeeService.update(registration, newEmployee);
    }

    public static void delete() throws Exception {
        System.out.println("Digite a matrícula do funcionário que quer deletar: ");
        registration = scanner.nextLine();

        employeeService.delete(registration);
    }
}
