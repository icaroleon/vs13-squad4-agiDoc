package br.com.agidoc.agiDoc.repository;

import br.com.agidoc.agiDoc.model.user.entity.UserAssociationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAssociationRepository extends JpaRepository<UserAssociationEntity, Integer> {

}