package br.com.agidoc.agiDoc.model.process;

import java.util.*;

import br.com.agidoc.agiDoc.model.competitor.Competitor;
import br.com.agidoc.agiDoc.model.document.Document;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "PROCESSES")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "processId")
public class Process {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PROCESSES")
    @SequenceGenerator(name = "SEQ_PROCESSES", sequenceName = "seq_processes", allocationSize = 1)
    @Column(name = "id_process")
    private Integer processId;

    @Column(name = "PROCESS_NUMBER")
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
    @JsonIgnore
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch=FetchType.LAZY)
    @JoinTable(name = "DOCUMENTS_ASSOCIATIONS", joinColumns = {@JoinColumn(name = "ID_PROCESS",
            referencedColumnName = "ID_PROCESS")},
            inverseJoinColumns = {@JoinColumn(name = "ID_DOCUMENT",
            referencedColumnName = "ID_DOCUMENT")})
    private Set<Document> documents = new HashSet<>();

    @Column(name = "ID_COMPANY")
    private Integer companyId = 1;

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
//
    @Override
    public boolean equals(Object o) {
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