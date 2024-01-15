package model.document;

import java.time.LocalDate;

import model.Associated;

public class Document {
    private String protocol;
    private Integer id;
    private LocalDate expirationDate;
    private boolean signed;
    private String file;
    private Associated associated;
    private Integer associatedId;

    public Document() {
    }

    public Document(String protocol, LocalDate expirationDate,
            String file) {
        this.protocol = protocol;
        this.expirationDate = expirationDate;
        this.signed = false;
        this.file = file;
    }

    public boolean isSigned() {
        return signed;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public boolean getSigned() {
        return signed;
    }

    public void setSigned(boolean signed) {
        this.signed = signed;
    }

    public String getFile() {
        return this.file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setAssociated(Associated associated) {
        this.associated = associated;
    }

    public Associated getAssociated() {
        return associated;
    }

    public void setAssociatedId(Integer associatedId) {
        this.associatedId = associatedId;
    }

    public Integer getAssociatedId() {
        return associatedId;
    }

    @Override
    public String toString() {
        return "\n\nDocumento encontrado com o protocolo " + protocol + ":\n" +
                "Protocolo: " + this.getProtocol() + "\n" +
                "Data de expiração: " + this.getExpirationDate() + "\n" +
                "Assinatura: " + this.getSigned() + "\n" +
                "Conteúdo: " + this.getFile();
    }
}
