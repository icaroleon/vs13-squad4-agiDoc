package entities.juridical;

import entities.document.Document;
import entities.employee.Employee;

import java.util.ArrayList;

public abstract class AbstractJuridical implements IJuridical {
    protected String cnpj;
    protected String address;
    protected String contact;
    protected ArrayList<Employee> employees;

    public AbstractJuridical() {
    }

    public AbstractJuridical(String cpnj, String address, String contact) {
        this.cnpj = cpnj;
        this.address = address;
        this.contact = contact;
    }

    public AbstractJuridical(String cpnj, String address, String contact, ArrayList<Employee> employees) {
        this.cnpj = cpnj;
        this.address = address;
        this.contact = contact;
        this.employees = employees;
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

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
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
