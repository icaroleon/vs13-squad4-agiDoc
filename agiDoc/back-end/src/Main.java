import employee.Employee;
import employee.EmployeeService;

import java.lang.reflect.Array;
import java.util.ArrayList;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        EmployeeService employeeController = new EmployeeService();

        ArrayList<Employee> employees = new ArrayList<>();

        Employee employee = new Employee("0", "John Doe", "johndoe", "password123", "123 Main St", "555-0100");

        employeeController.create(employee);
        System.out.println("teste getall");
        System.out.println(employeeController.getAll());

        ArrayList<Employee> novaListaEmployees = new ArrayList<>();
        Employee employeeNovo = new Employee("0", "testizinho", "johndoe", "password123", "123 Main St", "555-0100");
        Employee employeeNovo2 = new Employee("1", "teste", "johndoe", "password123", "123 Main St", "555-0100");
        novaListaEmployees.add(employeeNovo);
        novaListaEmployees.add(employeeNovo2);
        employeeController.setEmployees(novaListaEmployees);
        System.out.println("teste employess set por lista");
        System.out.println(employeeController.getAll());


        System.out.println("teste update");
        Employee employeeUpdate = new Employee("012", "nome editado", "userEditado", "editado", "addressEditado", "contactEditado");
        Employee employeeUpdate2 = new Employee("122", "nome editado novamente", "userEditado", "editado", "addressEditado", "contactEditado");
        employeeController.update("0", employeeUpdate);
        employeeController.update("1", employeeUpdate2);

        System.out.println("teste delete");
        employeeController.delete("1");

        System.out.println("teste employee search");
        employeeController.get("teste");
    }
}