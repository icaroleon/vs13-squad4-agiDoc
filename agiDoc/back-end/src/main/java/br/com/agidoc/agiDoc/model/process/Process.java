package br.com.agidoc.agiDoc.model.process;

import java.io.Serializable;
import java.util.*;

import br.com.agidoc.agiDoc.model.competitor.Competitor;
import br.com.agidoc.agiDoc.model.document.Document;
import br.com.agidoc.agiDoc.model.pk.DocumentAssociation;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "PROCESSES")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "processId")
public class Process implements Serializable {

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
    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JoinTable(name = "documents_associations", joinColumns = {@JoinColumn(name = "id_process", referencedColumnName = "id_process")},
            inverseJoinColumns = {@JoinColumn(name = "id_document", referencedColumnName = "ID_DOCUMENT")})
    private Set<Document> documents;

    @Column(name = "id_institution")
    private Integer institutionId = 9;

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

    @Override
    public boolean equals(Object o) {
        if(o == null) System.out.println("aqui");
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Process process = (Process) o;
        return Objects.equals(processId, process.processId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(processId);
    }

}