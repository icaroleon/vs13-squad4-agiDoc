package service;

import java.util.ArrayList;

import data.Data;
import entities.employee.Employee;

public class EmployeeService {

    private static ArrayList<Employee> listEmployees = new ArrayList<>();

    public EmployeeService(ArrayList<Employee> employees) {
        listEmployees = employees;
    }

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

    public Employee update(String registration, Employee newEmployeeDetails) throws Exception{

        Employee employeeToEdit = get(registration);

        if (employeeToEdit != null){
            employeeToEdit.setName(newEmployeeDetails.getName());
            employeeToEdit.setUser(newEmployeeDetails.getUser());
            employeeToEdit.setPassword(newEmployeeDetails.getPassword());
            employeeToEdit.setAddress(newEmployeeDetails.getAddress());
            employeeToEdit.setContact(newEmployeeDetails.getContact());
            return get(employeeToEdit.getRegistration());
        }
        throw new Exception("Colaborador não encontrado!");
    }

    public String delete(String idEmployeeToDelete) throws Exception{
        try{
            Employee employeeToDelete = get(idEmployeeToDelete);
            listEmployees.remove(employeeToDelete);
            System.out.println(getAll());
            return "Documento com o protocolo " + idEmployeeToDelete + " foi excluído com sucesso";
        } catch (RuntimeException e) {
            return "Error: Empregado não encontrado!";
        }
    }
}
