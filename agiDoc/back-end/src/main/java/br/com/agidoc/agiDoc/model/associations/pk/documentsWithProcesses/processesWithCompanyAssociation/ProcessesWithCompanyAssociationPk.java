package br.com.agidoc.agiDoc.model.associations.pk.documentsWithProcesses.processesWithCompanyAssociation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProcessesWithCompanyAssociationPk implements Serializable {

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
