package model.juridical;

import model.document.Document;
import model.employee.Employee;
import model.address.Address;
import model.contact.Contact;

import java.util.ArrayList;

public abstract class AbstractJuridical implements IJuridical {
    protected  String companyName;
    protected String cnpj;
    protected Address address;
    protected Contact contact;
    protected ArrayList<Departament> departaments;

    public AbstractJuridical() {
    }

    public AbstractJuridical(String cpnj, String companyName) {
        this.cnpj = cpnj;
        this.companyName = companyName;
    }

    public AbstractJuridical(String cpnj, Address address, Contact contact) {
        this.cnpj = cpnj;
        this.address = address;
        this.contact = contact;
    }

    public AbstractJuridical(String companyName, String cnpj, Address address, Contact contact, ArrayList<Departament> departaments) {
        this.companyName = companyName;
        this.cnpj = cnpj;
        this.address = address;
        this.contact = contact;
        this.departaments = departaments;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public ArrayList<Departament> getDepartaments() {
        return departaments;
    }

    public void setDepartaments(ArrayList<Departament> departaments) {
        this.departaments = departaments;
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
