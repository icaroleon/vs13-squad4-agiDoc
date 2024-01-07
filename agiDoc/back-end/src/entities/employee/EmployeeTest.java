/* package employee;
import entities.employee.Employee;
import service.EmployeeService;
import juridicalEntity.AbstractJuridicalEntity;

import java.lang.reflect.Array;
import java.util.ArrayList;




import java.util.ArrayList;

public class EmployeeTest {

    service.EmployeeService employeeService = new service.EmployeeService();
    entities.employee.Employee employeeClass = new entities.employee.Employee();

    ArrayList<entities.process.Process> processes = new ArrayList<>();
    ArrayList<entities.employee.Employee> employees = new ArrayList<>();
    String cnpj = "12.345.678/0001-99";
    String address = "1234 Main Street, Cidade Exemplo, Estado Exemplo, 01010-010";
    String contact = "+55 11 1234-5678";


    entities.institution.Institution institution = new entities.institution.Institution(cnpj, address, contact, processes, employees);

    entities.employee.Employee employee = new entities.employee.Employee("0", "John Doe", "johndoe", "password123", "123 Main St", "555-0100", institution);
        employeeService.create(employee);
        employee.login("johndoe", "password123");

    entities.employee.Employee employee2 = new entities.employee.Employee("0", "John Doe", "johndoe", "password123", "123 Main St", "555-0100", institution);
        employeeService.create(employee2);
        employee.login("johndoe2", "UsuarioCertoEPasswordErrado");
        employee.login("usuarioErradoESenhaCerta", "password123");



        employeeService.create(employee);
        System.out.println("teste getall");
        System.out.println(employeeService.getAll());

    ArrayList<entities.employee.Employee> novaListaEmployees = new ArrayList<>();
    entities.employee.Employee employeeNovo = new entities.employee.Employee("123", "testizinho", "johndoe", "password123", "123 Main St", "555-0100", institution);
    entities.employee.Employee employeeNovo2 = new entities.employee.Employee("321", "teste", "johndoe", "password123", "123 Main St", "555-0100", institution);
        novaListaEmployees.add(employeeNovo);
        novaListaEmployees.add(employeeNovo2);
        employeeService.setEmployees(novaListaEmployees);
        System.out.println("teste employess set por lista");
        System.out.println(employeeService.getAll());



        System.out.println("teste update");
    entities.employee.Employee employeeUpdate = new entities.employee.Employee("123", "nome editado", "userEditado", "editado", "addressEditado", "contactEditado", institution);
    entities.employee.Employee employeeUpdate2 = new entities.employee.Employee("321", "nome editado novamente", "userEditado", "editado", "addressEditado", "contactEditado", institution);
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