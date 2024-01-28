package br.com.agidoc.agiDoc.model.process;

import java.util.ArrayList;
import java.util.UUID;

import br.com.agidoc.agiDoc.model.competitor.Competitor;
import br.com.agidoc.agiDoc.model.document.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class Process implements IProcess {
    private Integer processId;
    private String processNumber = UUID.randomUUID().toString().substring(0, 6);
    private String title, description;
    private ProcessStatus processStatus = ProcessStatus.IN_PROGRESS;
    private Competitor contracted;
    private ArrayList<Competitor> competitors;
    private ArrayList<Document> documents;
    private Integer institutionId = 1;

    public boolean chooseContractor(Competitor competitor) {
        this.contracted = competitor;

        return true;
    }

    public boolean subscribe(Competitor competitor) {
        if (competitor == null)
            return false;

        this.competitors.add(competitor);
        return true;
    }

}