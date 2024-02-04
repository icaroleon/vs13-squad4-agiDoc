package br.com.agidoc.agiDoc.model.pk;

import br.com.agidoc.agiDoc.model.document.Document;
import br.com.agidoc.agiDoc.model.process.Process;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "DOCUMENTS_ASSOCIATIONS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DocumentAssociation {

    @EmbeddedId
    private DocumentsAssociationsPk documentsAssociationsPk;

    @ManyToOne
    @MapsId("processId")
    @JoinColumn(name = "id_process")
    private Process process;

    @ManyToOne
    @MapsId("documentId")
    @JoinColumn(name = "id_document")
    private Document document;

    //    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PROCESSES")
//    @SequenceGenerator(name = "SEQ_PROCESSES", sequenceName = "seq_processes", allocationSize = 1)
//    @Column(name = "ID_DOCUMENT_ASSOCIATION")
//    private Integer documentsAssociationId;
//    @JoinColumn(name = "ID_DOCUMENT_ASSOCIATION")
//    private DocumentsAssociationsPk documentsAssociationsPk;


//
//    @Override
//    public boolean equals(Object obj)  {
//        if (this == obj) return true;
//
//        if (obj == null || getClass() != obj.getClass()) return false;
//
//        DocumentAssociation that = (DocumentAssociation) obj;
//        return Objects.equals(process.getProcessId(), that.process.getProcessId()) &&
//                Objects.equals(document.getDocumentId(), that.document.getDocumentId());
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(process.getProcessId(), document.getDocumentId());
//    }
}