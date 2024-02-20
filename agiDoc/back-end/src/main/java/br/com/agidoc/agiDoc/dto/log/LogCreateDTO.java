package br.com.agidoc.agiDoc.dto.log;

import br.com.agidoc.agiDoc.model.log.LogType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogCreateDTO {
    @Enumerated(EnumType.STRING)
    private LogType logType;
    private String description;
    private String date;
}
