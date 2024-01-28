package br.com.agidoc.agiDoc.model.juridical;

import br.com.agidoc.agiDoc.model.document.Document;

public interface IJuridical {
    public boolean signDocument(Document document);
    public boolean sendSignedDocument(Process process);
}
