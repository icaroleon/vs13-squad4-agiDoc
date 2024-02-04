package br.com.agidoc.agiDoc.model.associations.pk.documentsWithProcesses.documentsWithProcessesAssociation;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DocumentsWithProcessesAssociationPk implements Serializable {

    @Column(name = "ID_DOCUMENT")
    private Integer documentId;

    @Column(name = "ID_PROCESS")
    private Integer processId;

//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) return true;
//
//        if (obj == null || getClass() != obj.getClass()) return false;
//
//        DocumentsAssociationsPk that = (DocumentsAssociationsPk) obj;
//        return Objects.equals(documentId, that.documentId) &&
//                Objects.equals(processId, that.processId);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(processId, documentId);
//    }
}
