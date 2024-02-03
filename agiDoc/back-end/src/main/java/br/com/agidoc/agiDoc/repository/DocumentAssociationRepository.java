package br.com.agidoc.agiDoc.repository;

import br.com.agidoc.agiDoc.model.pk.DocumentAssociation;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentAssociationRepository extends JpaRepository<DocumentAssociation, Integer> {
}
