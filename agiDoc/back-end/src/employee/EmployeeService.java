package employee;

import java.util.ArrayList;


public class EmployeeService {

    private static ArrayList<Employee> listaEmployees = new ArrayList<>();;

    public EmployeeService(){

    }

    public Employee get(String employeeSearch) {
        for(Employee employee : listaEmployees){
            if (employee.getName().equals(employeeSearch)){

                //TODO set pessoa juridica
            }
        }
    }

    public void setEmployees(ArrayList<Employee> listaEmployees) {
        EmployeeService.listaEmployees = listaEmployees;
    }

    public void create(Employee employee){
        listaEmployees.add(employee);

    }

    public ArrayList<Employee> getAll(){
        return listaEmployees;
    }

    public void update(String id, Employee newEmployeeDetails){

        for(Employee employee : listaEmployees){
            if (employee.getId().equals(newEmployeeDetails.getId())){
                employee.setName(newEmployeeDetails.getName());
                employee.setUser(newEmployeeDetails.getUser());
                employee.setPassword(newEmployeeDetails.getPassword());
                employee.setAddress(newEmployeeDetails.getAddress());
                employee.setContact(newEmployeeDetails.getContact());
                //TODO set pessoa juridica
                System.out.println("Update realizado!");
                System.out.println(this.getAll());
                return;
            }
        }
        System.out.println("Empregado não encontrado!");
    }

    public boolean delete(String idEmployeeToDelete){
        for(Employee employee : listaEmployees){
            if (employee.getId().equals(idEmployeeToDelete)){
                listaEmployees.remove(employee);
                System.out.println(this.getAll());
                return true;
            }
        }
        System.out.println("Empregado não encontrado!");
        return false;
    }
}
