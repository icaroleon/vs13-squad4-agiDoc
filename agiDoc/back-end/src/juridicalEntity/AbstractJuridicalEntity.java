package juridicalEntity;

import document.Document;

public abstract class AbstractJuridicalEntity implements IJuridicalEntity {
    private String cnpj;
    private String address;
    private String contact;

    public AbstractJuridicalEntity() {}

    public AbstractJuridicalEntity(String cpnj, String address, String contact) {
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

    public String getEndereco() {
        return address;
    }

    public void setEndereco(String endereco) {
        this.address = endereco;
    }

    public String getContato() {
        return contact;
    }

    public void setContato(String contato) {
        this.contact = contato;
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
