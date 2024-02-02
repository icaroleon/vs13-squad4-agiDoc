package br.com.agidoc.agiDoc.repository;


import br.com.agidoc.agiDoc.model.competitor.Competitor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetitorRepository extends JpaRepository<Competitor, Integer> {

}
