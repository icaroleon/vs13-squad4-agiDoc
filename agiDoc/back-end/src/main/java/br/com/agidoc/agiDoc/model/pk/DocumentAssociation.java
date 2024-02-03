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

//    @EmbeddedId
      //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_DOCUMENTS_ASSOCIATIONS")
//    @JoinColumn(name = "ID_DOCUMENT_ASSOCIATION")
//    DocumentsAssociationsPk documentsAssociationsPk;

//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_DOCUMENTS_ASSOCIATIONS")
//    @SequenceGenerator(name = "SEQ_DOCUMENTS_ASSOCIATIONS", sequenceName = "SEQ_DOCUMENTS_ASSOCIATIONS", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PROCESSES")
    @SequenceGenerator(name = "SEQ_PROCESSES", sequenceName = "seq_processes", allocationSize = 1)
    @Column(name = "ID_DOCUMENT_ASSOCIATION")
    private Integer documentsAssociationId;

    @Column(name = "id_document")
    private Integer documentId;

    @Column(name = "id_process")
    private Integer processId;

//
//    @JoinColumn(name = "ID_DOCUMENT_ASSOCIATION")
//    private DocumentsAssociationsPk documentsAssociationsPk;

//    @ManyToOne
//    @MapsId("processId")
//    @JoinColumn(name = "id_process")
//    private Process process;
//
//    @ManyToOne
//    @MapsId("documentId")
//    @JoinColumn(name = "id_document")
//    private Document document;
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