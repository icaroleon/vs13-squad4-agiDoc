package br.com.agidoc.agiDoc.model.pk;

import br.com.agidoc.agiDoc.model.document.Document;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DocumentsAssociationsPk implements Serializable {

    @Column(name = "id_document")
    private Integer documentId;

    @Column(name = "id_process")
    private Integer processId;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        DocumentsAssociationsPk that = (DocumentsAssociationsPk) obj;
        return Objects.equals(documentId, that.documentId) &&
                Objects.equals(processId, that.processId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(processId, documentId);
    }
}
