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
}
