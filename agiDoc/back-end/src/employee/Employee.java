package employee;

import juridicalEntity.AbstractJuridicalEntity;

public class Employee {

    private String matricula;
    private String name;
    private String user;
    private String password;
    private String address;
    private String contact;
    private AbstractJuridicalEntity company;

    @Override
    public String toString() {
        return "Employee{" +
                "matricula='" + matricula + '\'' +
                ", name='" + name + '\'' +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                ", company=" + company +
                '}';
    }

    public Employee(String matricula, String name, String user, String password, String address, String contact) {
        this.matricula = matricula;
        this.name = name;
        this.user = user;
        this.password = password;
        this.address = address;
        this.contact = contact;
        // TODO abstract juridical entity
    }

    public Employee(){

    }

    public String getMatricula(){
        return matricula;
    }

    public String setMatricula(){
        this.matricula = matricula;
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

    //TODO LOGIN METHOD
}
