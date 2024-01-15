package model.juridical;

import model.document.Document;

public interface IJuridical {
    public boolean signDocument(Document document);
    public boolean sendSignedDocument(Process process);
}
