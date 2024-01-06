package employee;

import juridicalEntity.AbstractJuridicalEntity;

public class Employee {

    private String id;
    private String name;
    private String user;
    private String password;
    private String address;
    private String contact;
    private AbstractJuridicalEntity company;

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                ", company=" + company +
                '}';
    }

    public Employee(String id, String name, String user, String password, String address, String contact) {
        this.id = id;
        this.name = name;
        this.user = user;
        this.password = password;
        this.address = address;
        this.contact = contact;
        this.contact = contact;
    }

    public Employee(){

    }

    public void setId(String id) {
        this.id = id;
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

    public String getAdress() {
        return address;
    }

    public void setAdress(String adress) {
        this.address = adress;
    }

    public String getId(){
        return id;
    }
    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AbstractJuridicalEntity getCompany() {
        return company;
    }

    public void setCompany(AbstractJuridicalEntity company) {
        this.company = company;
    }
}
