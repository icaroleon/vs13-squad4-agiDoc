package employee;

import juridicalEntity.AbstractJuridicalEntity;

import java.util.ArrayList;


public class EmployeeController {

    private static ArrayList<Employee> listaEmployees = new ArrayList<>();;

    public EmployeeController(){

    }

    public ArrayList<Employee> getEmployees() {
        return listaEmployees;
    }

    public static void setEmployees(ArrayList<Employee> listaEmployees) {
        EmployeeController.listaEmployees = listaEmployees;
    }

    public void create(Employee employee){
        listaEmployees.add(employee);

    }

    public void getAll(){
        for(Employee employee : listaEmployees){
            System.out.print(employee);
        }
    }

    public boolean update(String id, Employee listaEmployee){

        for(Employee employee : listaEmployees){
            String name =  employee.getName();
            String user = employee.getUser();
            String password = employee.getPassword();
            String address = employee.getAdress();
            String contact = employee.getContact();

            if (employee.getId().equals(id)){
                employee.setName(name);
                employee.setContact(contact);
                return true;
            }
            System.out.println("Empregado não encontrado!");
            return false;
        }
        return false;
    }
}
