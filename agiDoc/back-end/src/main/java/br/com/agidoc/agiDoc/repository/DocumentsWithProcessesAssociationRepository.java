package br.com.agidoc.agiDoc.repository;
import br.com.agidoc.agiDoc.model.associations.pk.documentsWithProcesses.documentsWithProcessesAssociation.DocumentsWithProcessesAssociation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentsWithProcessesAssociationRepository extends JpaRepository<DocumentsWithProcessesAssociation, Integer> {
}
