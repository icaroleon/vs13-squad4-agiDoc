package br.com.agidoc.agiDoc.dto.process;

import br.com.agidoc.agiDoc.model.competitor.Competitor;
import br.com.agidoc.agiDoc.model.document.Document;
import br.com.agidoc.agiDoc.model.process.ProcessStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProcessDTO{

    private Integer processId;
    private String processNumber = UUID.randomUUID().toString().substring(0, 6);
    private String title, description;
    private ProcessStatus processStatus = ProcessStatus.IN_PROGRESS;
    private Competitor contracted;
    private ArrayList<Competitor> competitors;
    private ArrayList<Document> documents;
    private Integer institutionId = 1;
}
