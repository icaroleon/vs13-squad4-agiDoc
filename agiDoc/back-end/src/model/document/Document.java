package model.document;

import java.time.LocalDate;

public class Document {
    private String protocol;
    private LocalDate expirationDate;
    private boolean signed;
    private String file;

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

    @Override
    public String toString() {
        return "\n\nDocumento encontrado com o protocolo " + protocol + ":\n" +
                "Protocolo: " + this.getProtocol() + "\n" +
                "Data de expiração: " + this.getExpirationDate() + "\n" +
                "Assinatura: " + this.getSigned() + "\n" +
                "Conteúdo: " + this.getFile();
    }
}
