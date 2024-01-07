package entities.employee;

import entities.juridical.AbstractJuridical;
import service.EmployeeService;

import java.util.ArrayList;
import java.util.UUID;

public class Employee {

    private String registration;
    private String name;
    private String user;
    private String password;
    private String address;
    private String contact;
    private AbstractJuridical company;

    @Override
    public String toString() {
        return "entities.employee.Employee {" +
                "\n\tRegistration: '" + registration + '\'' +
                ",\n\tName: '" + name + '\'' +
                ",\n\tUser: '" + user + '\'' +
                ",\n\tPassword: '" + password + '\'' +
                ",\n\tAddress: '" + address + '\'' +
                ",\n\tContact: '" + contact + '\'' +
                ",\n\tCompany: " + (company != null ? company.toString() : "null") +
                "\n}";
    }

    public Employee(){}

    public Employee(String name, String user, String password, String address, String contact) {
        UUID uuid = UUID.randomUUID();

        this.registration = uuid.toString();
        this.name = name;
        this.user = user;
        this.password = password;
        this.address = address;
        this.contact = contact;
    }

//  TODO: Adicionar construtor sem company e gerar registration ou novo atributo id dinamicamente
    public Employee(String name, String user, String password, String address, String contact, AbstractJuridical company) {
        UUID uuid = UUID.randomUUID();

        this.registration = uuid.toString();
        this.name = name;
        this.user = user;
        this.password = password;
        this.address = address;
        this.contact = contact;
        this.company = company;
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

    public AbstractJuridical getCompany() {
        return company;
    }

    public void setCompany(AbstractJuridical company) {
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
