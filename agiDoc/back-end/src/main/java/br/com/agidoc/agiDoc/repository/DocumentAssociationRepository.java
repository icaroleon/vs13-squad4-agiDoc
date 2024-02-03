package br.com.agidoc.agiDoc.repository;

import br.com.agidoc.agiDoc.model.pk.DocumentAssociation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentAssociationRepository extends JpaRepository<DocumentAssociation, Integer> {
}
