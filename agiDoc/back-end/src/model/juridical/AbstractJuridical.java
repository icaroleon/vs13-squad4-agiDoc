package model.juridical;

import model.address.Address;
import model.document.Document;
import model.user.User;

import java.util.ArrayList;

// TODO: REVISAR CLASSE, HÁ ALTERAÇÕES PENDENTES
public abstract class AbstractJuridical implements IJuridical {
    protected String cnpj;
    protected String companyName;
    protected Address address;
    protected String contact;
    protected ArrayList<User> users;

    public AbstractJuridical() {
    }

    public AbstractJuridical(String cpnj, String companyName) {
        this.cnpj = cpnj;
        this.companyName = companyName;
    }

    public AbstractJuridical(String cpnj, Address address, String contact, ArrayList<User> users) {
        this.cnpj = cpnj;
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
