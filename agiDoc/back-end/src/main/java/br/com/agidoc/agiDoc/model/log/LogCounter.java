package br.com.agidoc.agiDoc.model.log;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LogCounter {
    @Id
    private LogType logType;
    private Integer quantity;
}
