package employee;

import java.util.ArrayList;

public class EmployeeService {

    private static ArrayList<Employee> listaEmployees = new ArrayList<>();

    public EmployeeService() {
    }

    public Employee get(String employeeSearch) {

        for(Employee employee : listaEmployees){
            if (employee.getRegistration().equals(employeeSearch)){
                System.out.println(employee.toString());
                return employee;
            }
        }
        System.out.println("Empregado não encontrado!");
        return null;
    }

    public void setEmployees(ArrayList<Employee> listaEmployees) {
        EmployeeService.listaEmployees = listaEmployees;
    }

    public static ArrayList<Employee> getAll() {
        return listaEmployees;
    }

    public void create(Employee employee){
        listaEmployees.add(employee);
    }

    public void update(String registration, Employee newEmployeeDetails){

        for(Employee employee : listaEmployees){
            if (employee.getRegistration().equals(newEmployeeDetails.getRegistration())){
                employee.setName(newEmployeeDetails.getName());
                employee.setUser(newEmployeeDetails.getUser());
                employee.setPassword(newEmployeeDetails.getPassword());
                employee.setAddress(newEmployeeDetails.getAddress());
                employee.setContact(newEmployeeDetails.getContact());
                System.out.println("Update realizado!");
                System.out.println(getAll());
                return;
            }
        }
        System.out.println("Empregado não encontrado!");
    }

    public boolean delete(String idEmployeeToDelete){
        for(Employee employee : listaEmployees){
            if (employee.getRegistration().equals(idEmployeeToDelete)){
                listaEmployees.remove(employee);
                System.out.println("Employee successfully deleted.");
                System.out.println(getAll());
                return true;
            }
        }
        System.out.println("Empregado não encontrado!");
        return false;
    }
}
