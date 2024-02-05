package br.com.agidoc.agiDoc.repository;


import br.com.agidoc.agiDoc.model.Status;
import br.com.agidoc.agiDoc.model.company.Company;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {
    List<Company> findAllByStatus(Status status);
}
