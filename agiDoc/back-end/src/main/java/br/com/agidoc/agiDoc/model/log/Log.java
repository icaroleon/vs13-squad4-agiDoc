package br.com.agidoc.agiDoc.model.log;

import lombok.Getter;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@Document(collection = "log")
public class Log {
    @Id
    private String id;
    @Enumerated(EnumType.STRING)
    private LogType logType;
    private String description;
    private String date;
}