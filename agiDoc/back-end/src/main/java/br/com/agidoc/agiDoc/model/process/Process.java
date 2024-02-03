package br.com.agidoc.agiDoc.model.process;

import java.util.ArrayList;
import java.util.UUID;

import br.com.agidoc.agiDoc.model.competitor.Competitor;
import br.com.agidoc.agiDoc.model.document.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "processes")
public class Process implements IProcess {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PROCESSES")
    @SequenceGenerator(name = "SEQ_PROCESSES", sequenceName = "seq_processes", allocationSize = 1)
    @Column(name = "id_process")
    private Integer processId;

    @Column(name = "process_number")
    private String processNumber = UUID.randomUUID().toString().substring(0, 6);

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private ProcessStatus processStatus = ProcessStatus.IN_PROGRESS;

//    @Column(name = "id_contracted")
//    private Competitor contracted;


//    private ArrayList<Competitor> competitors;
//
//    private ArrayList<Document> documents;

    @Column(name = "id_institution")
    private Integer institutionId;

    public boolean chooseContractor(Competitor competitor) {
        //this.contracted = competitor;

        return true;
    }

    public boolean subscribe(Competitor competitor) {
        if (competitor == null)
            return false;

//        this.competitors.add(competitor);
        return true;
    }

}