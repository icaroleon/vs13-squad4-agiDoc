package entities.juridical;

import entities.document.Document;

public abstract class AbstractJuridical implements IJuridical {
    private String cnpj;
    private String address;
    private String contact;

    public AbstractJuridical() {
    }

    public AbstractJuridical(String cpnj, String address, String contact) {
        this.cnpj = cpnj;
        this.address = address;
        this.contact = contact;
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
