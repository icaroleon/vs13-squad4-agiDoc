package br.com.agidoc.agiDoc.repository;

import br.com.agidoc.agiDoc.model.log.Log;
import br.com.agidoc.agiDoc.model.log.LogCounter;
import br.com.agidoc.agiDoc.model.log.LogType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogRepository extends MongoRepository<Log, String> {
    List<Log> findAllByLogType(LogType logType);

    Page<Log> findAll(Pageable pageable);

    @Aggregation (pipeline = {
            "{$unwind: '$logType'}",
            "{$group: { _id: '$logType', quantidade: { $sum: 1 } }}"
    })
    List<LogCounter> groupByLogTypeAndCount();

    List<Log> findAllByDateContains(String date);

    @Aggregation(pipeline = {
            "{$match: { date: { $regex: ?0 } }}",
            "{$group: { _id: '$logType', quantidade: { $sum: 1 } }}"
    })
    List<LogCounter> findAllByDateAndCountLogType(String date);

    Integer countAllByDateContains(String date);

    Integer countByLogType(LogType logType);

    @Query("{'date': { $gte: ?0 } }")
    List<Log> findAllAfterDate(String date);
}
