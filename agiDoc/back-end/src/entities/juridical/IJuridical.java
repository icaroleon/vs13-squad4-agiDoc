package entities.juridical;

import entities.document.Document;

public interface IJuridical {
    public boolean signDocument(Document document);
    public boolean sendSignedDocument(Process process);
}
