package br.com.agidoc.agiDoc.model.associations.pk.documentsWithProcesses.documentsWithProcessesAssociation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "PROCESS_ASSOCIATIONS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CompanyWithProcessesAssociation {

    @EmbeddedId
    private CompanyWithProcessesAssociationPK processesAssociationPK;
}
