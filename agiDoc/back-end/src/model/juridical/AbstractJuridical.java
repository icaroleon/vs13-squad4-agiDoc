package model.juridical;

import model.address.Address;
import model.contact.Contact;
import model.department.Department;
import model.document.Document;
import model.user.User;

import java.util.ArrayList;

public abstract class AbstractJuridical implements IJuridical {

    protected  String companyName;
    protected String cnpj;
    protected Address address;
    protected Contact contact;
    protected ArrayList<User> users;
    protected ArrayList<Department> departments;

    public AbstractJuridical() {
    }

    public AbstractJuridical(String cpnj, Address address, Contact contact) {
        this.cnpj = cpnj;
        this.address = address;
        this.contact = contact;
    }

    public AbstractJuridical(String companyName,String cpnj, Address address, Contact contact, ArrayList<Department> departments) {
        this.companyName = companyName;
        this.cnpj = cpnj;
        this.address = address;
        this.contact = contact;;
        this.departments = departments;
    }

    public AbstractJuridical(String companyName,String cpnj, Address address, Contact contact, ArrayList<User> users, ArrayList<Department> departments) {
        this.companyName = companyName;
        this.cnpj = cpnj;
        this.address = address;
        this.contact = contact;
        this.users = users;
        this.departments = departments;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
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

    public ArrayList<User> getEmployees() {
        return users;
    }

    public void setEmployees(ArrayList<User> users) {
        this.users = users;
    }

    @Override
    public boolean signDocument(Document document) {
        System.out.println("Documento assinado!");
        return true;
    }

    @Override
    public boolean sendSignedDocument(Process process) {
        System.out.println("Documento enviado com sucesso!");
        return false;
    }
}
