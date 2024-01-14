package model.juridical;

import model.address.Address;
import model.contact.Contact;
import model.document.Document;
import model.user.User;

import java.util.ArrayList;

public abstract class AbstractJuridical implements IJuridical {
    protected String cnpj;
    protected String companyName;
    protected Address address;
    protected Contact contact;
    protected ArrayList<User> users;

    public AbstractJuridical() {
    }

    public AbstractJuridical(String cpnj, String companyName) {
        this.cnpj = cpnj;
        this.companyName = companyName;
    }

    public AbstractJuridical(String cpnj, String companyName, Address address, Contact contact) {
        this.cnpj = cpnj;
        this.companyName = companyName;
        this.address = address;
        this.contact = contact;
    }

    public AbstractJuridical(String cpnj, String companyName, Address address, Contact contact, ArrayList<User> users) {
        this.cnpj = cpnj;
        this.companyName = companyName;
        this.address = address;
        this.contact = contact;
        this.users = users;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
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

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
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
