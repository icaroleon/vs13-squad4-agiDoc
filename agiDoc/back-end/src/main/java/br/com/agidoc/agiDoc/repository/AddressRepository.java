package br.com.agidoc.agiDoc.repository;

import br.com.agidoc.agiDoc.model.address.Entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Integer> {
}