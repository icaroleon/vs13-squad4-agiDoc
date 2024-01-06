package employee;

import juridicalEntity.AbstractJuridicalEntity;

import java.util.ArrayList;

public class Employee {

    private String registration;
    private String name;
    private String user;
    private String password;
    private String address;
    private String contact;
    private AbstractJuridicalEntity company;

    @Override
    public String toString() {
        return "Employee {" +
                "\n\tRegistration: '" + registration + '\'' +
                ",\n\tName: '" + name + '\'' +
                ",\n\tUser: '" + user + '\'' +
                ",\n\tPassword: '" + password + '\'' +
                ",\n\tAddress: '" + address + '\'' +
                ",\n\tContact: '" + contact + '\'' +
                ",\n\tCompany: " + (company != null ? company.toString() : "null") +
                "\n}";
    }


    public Employee(String registration, String name, String user, String password, String address, String contact, AbstractJuridicalEntity company) {
        this.registration = registration;
        this.name = name;
        this.user = user;
        this.password = password;
        this.address = address;
        this.contact = contact;
        this.company = company;
    }

    public Employee(){
    }

    public String getRegistration(){
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public AbstractJuridicalEntity getCompany() {
        return company;
    }

    public void setCompany(AbstractJuridicalEntity company) {
        this.company = company;
    }

    public boolean login(String user, String password){
        ArrayList<Employee> listaEmployees = EmployeeService.getAll();
        for(Employee employee : listaEmployees){
           if(employee.getUser().equals(user) && employee.getPassword().equals(password)) {
               System.out.println("Login efetuado!");
               return true;
           }
        }
        System.out.println("Usuário ou senha errados");
        return false;
    }
}
