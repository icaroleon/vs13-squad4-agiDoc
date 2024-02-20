package br.com.agidoc.agiDoc.repository;

import br.com.agidoc.agiDoc.model.report.ProcessStatusReport;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessStatusReportRepository extends MongoRepository<ProcessStatusReport, String> {
}
