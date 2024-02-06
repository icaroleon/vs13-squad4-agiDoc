package br.com.agidoc.agiDoc.repository;

import br.com.agidoc.agiDoc.model.contact.Entity.ContactAssociationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ContactAssociationRepository extends JpaRepository<ContactAssociationEntity, Integer> {
}