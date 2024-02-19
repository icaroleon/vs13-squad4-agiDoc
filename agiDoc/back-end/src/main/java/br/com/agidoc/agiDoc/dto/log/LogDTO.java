package br.com.agidoc.agiDoc.dto.log;

import br.com.agidoc.agiDoc.model.log.LogType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogDTO {
    private String id;
    private LogType logType;
    private String description;
    private String date;
}
