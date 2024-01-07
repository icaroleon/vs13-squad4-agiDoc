package service;

import java.util.ArrayList;

import entities.employee.Employee;

public class EmployeeService {

    private static ArrayList<Employee> listEmployees = new ArrayList<>();

    public EmployeeService() {
    }

    public Employee get(String employeeSearch) throws Exception {
        for(Employee employee : listEmployees){
            if (employee.getRegistration().equals(employeeSearch)){
                return employee;
            }

        }
        throw new Exception("Colaborador não encontrado!");
    }

    public void setEmployees(ArrayList<Employee> listaEmployees) {
        EmployeeService.listEmployees = listaEmployees;
    }

    public static ArrayList<Employee> getAll() {
        return listEmployees;
    }

    public Employee create(Employee employee){
        listEmployees.add(employee);
        return employee;
    }

    public void update(String registration, Employee newEmployeeDetails) throws Exception{

        for(Employee employee : listEmployees){
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
        throw new Exception("Colaborador não encontrado!");
    }

    public boolean delete(String idEmployeeToDelete) throws Exception{
        for(Employee employee : listEmployees){
            if (employee.getRegistration().equals(idEmployeeToDelete)){
                listEmployees.remove(employee);
                System.out.println("entities.employee.Employee successfully deleted.");
                System.out.println(getAll());
                return true;
            }
        }
        throw new Exception("Empregado não encontrado!");
    }
}
