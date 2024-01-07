package juridicalEntity;


import document.Document;

public interface IJuridicalEntity {
    public boolean signDocument(Document document);
    public boolean sendSignedDocument(Process process);
}
