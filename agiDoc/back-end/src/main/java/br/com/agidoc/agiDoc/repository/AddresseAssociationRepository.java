package br.com.agidoc.agiDoc.repository;

import br.com.agidoc.agiDoc.model.address.entity.AddressAssociationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddresseAssociationRepository extends JpaRepository<AddressAssociationEntity, Integer> {
}
