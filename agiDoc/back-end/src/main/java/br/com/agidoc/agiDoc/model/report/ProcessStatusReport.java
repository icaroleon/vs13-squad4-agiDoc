package br.com.agidoc.agiDoc.model.report;

import br.com.agidoc.agiDoc.model.log.LogType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@Document(collection = "processStatusReport")
public class ProcessStatusReport {
    @Id
    private String id;
    private String entity = "Process";
    private String processNumber;
    private String oldStatus;
    private String newStatus;
    private String description;
    private String date;
    private String userUsername;
}
