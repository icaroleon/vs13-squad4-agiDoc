package br.com.agidoc.agiDoc.repository;

import br.com.agidoc.agiDoc.model.contact.entity.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ContactRepository extends JpaRepository<ContactEntity, Integer> {

}