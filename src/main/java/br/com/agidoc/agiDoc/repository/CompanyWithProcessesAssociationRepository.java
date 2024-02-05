package br.com.agidoc.agiDoc.repository;

import br.com.agidoc.agiDoc.model.associations.pk.documentsWithProcesses.documentsWithProcessesAssociation.CompanyWithProcessesAssociation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyWithProcessesAssociationRepository extends JpaRepository<CompanyWithProcessesAssociation, Integer> {
}
