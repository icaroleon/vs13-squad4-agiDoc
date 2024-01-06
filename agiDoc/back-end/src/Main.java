import employee.Employee;
import employee.EmployeeController;

import java.lang.reflect.Array;
import java.util.ArrayList;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        EmployeeController employeeController = new EmployeeController();

        ArrayList<Employee> employees = new ArrayList<>();

        Employee employee = new Employee("1", "John Doe", "johndoe", "password123", "123 Main St", "555-0100");
        employeeController.create(employee);

        System.out.print(employeeController.getEmployees());



    }
}