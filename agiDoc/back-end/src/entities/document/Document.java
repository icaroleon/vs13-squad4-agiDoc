package entities.document;

import java.time.LocalDate;

public class Document {
    private String protocol;
    private LocalDate expirationDate;
    private String origin;
    private String originId;
    private boolean signed;
    private String content;
    private DocumentType type;

    public Document() {}

    public Document(String protocol, LocalDate expirationDate, String origin, String originId, DocumentType type, String content) {
        this.protocol = protocol;
        this.expirationDate = expirationDate;
        this.origin = origin;
        this.originId = originId;
        this.signed = false;
        this.content = content;
        this.type = type;
    }

    public boolean isSigned() {
        return signed;
    }

    public DocumentType getType() {
        return type;
    }

    public void setType(DocumentType type) {
        this.type = type;
    }

    public String getTypeName() {
        return type.name();
    }

    public int getTypeInt() {
        return type.ordinal();
    }

    public String getTypeName() {
        return type.name();
    }

    public int getTypeInt() {
        return type.ordinal();
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

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getOriginId() {
        return originId;
    }

    public void setOriginId(String originId) {
        this.originId = originId;
    }

    public boolean getSigned() {
        return signed;
    }

    public void setSigned(boolean signed) {
        this.signed = signed;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
