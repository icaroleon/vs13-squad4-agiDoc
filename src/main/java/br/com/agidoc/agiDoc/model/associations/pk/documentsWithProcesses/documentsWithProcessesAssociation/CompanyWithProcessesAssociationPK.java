package br.com.agidoc.agiDoc.model.associations.pk.documentsWithProcesses.documentsWithProcessesAssociation;

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
public class CompanyWithProcessesAssociationPK implements Serializable {

    @Column(name = "ID_PROCESS")
    private Integer processId;

    @Column(name = "ID_COMPANY")
    private Integer companyId;
}
