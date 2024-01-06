/* package employee;
import employee.Employee;
import employee.EmployeeService;
import juridicalEntity.AbstractJuridicalEntity;

import java.lang.reflect.Array;
import java.util.ArrayList;




import java.util.ArrayList;

public class EmployeeTest {

    EmployeeService employeeService = new EmployeeService();
    Employee employeeClass = new Employee();

    ArrayList<Process> processes = new ArrayList<>();
    ArrayList<Employee> employees = new ArrayList<>();
    String cnpj = "12.345.678/0001-99";
    String address = "1234 Main Street, Cidade Exemplo, Estado Exemplo, 01010-010";
    String contact = "+55 11 1234-5678";


    Institution institution = new Institution(cnpj, address, contact, processes, employees);

    Employee employee = new Employee("0", "John Doe", "johndoe", "password123", "123 Main St", "555-0100", institution);
        employeeService.create(employee);
        employee.login("johndoe", "password123");

    Employee employee2 = new Employee("0", "John Doe", "johndoe", "password123", "123 Main St", "555-0100", institution);
        employeeService.create(employee2);
        employee.login("johndoe2", "UsuarioCertoEPasswordErrado");
        employee.login("usuarioErradoESenhaCerta", "password123");



        employeeService.create(employee);
        System.out.println("teste getall");
        System.out.println(employeeService.getAll());

    ArrayList<Employee> novaListaEmployees = new ArrayList<>();
    Employee employeeNovo = new Employee("123", "testizinho", "johndoe", "password123", "123 Main St", "555-0100", institution);
    Employee employeeNovo2 = new Employee("321", "teste", "johndoe", "password123", "123 Main St", "555-0100", institution);
        novaListaEmployees.add(employeeNovo);
        novaListaEmployees.add(employeeNovo2);
        employeeService.setEmployees(novaListaEmployees);
        System.out.println("teste employess set por lista");
        System.out.println(employeeService.getAll());



        System.out.println("teste update");
    Employee employeeUpdate = new Employee("123", "nome editado", "userEditado", "editado", "addressEditado", "contactEditado", institution);
    Employee employeeUpdate2 = new Employee("321", "nome editado novamente", "userEditado", "editado", "addressEditado", "contactEditado", institution);
        employeeService.update("123", employeeUpdate);
        employeeService.update("321", employeeUpdate2);

        System.out.println("teste delete");
        employeeService.delete("123");

        System.out.println("teste employee existente search");
        employeeService.get("321");

        System.out.println("teste employee não existente search");
        employeeService.get("123");
}

 */