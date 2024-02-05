package br.com.agidoc.agiDoc.model.associations.pk.documentsWithProcesses.documentsWithProcessesAssociation;

import br.com.agidoc.agiDoc.model.document.Document;
import br.com.agidoc.agiDoc.model.process.Process;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "DOCUMENTS_ASSOCIATIONS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DocumentsWithProcessesAssociation {

    @EmbeddedId
    private DocumentsWithProcessesAssociationPk documentsAssociationsPk;
}