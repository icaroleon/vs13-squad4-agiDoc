package br.com.agidoc.agiDoc.model.process;

import java.util.*;

import br.com.agidoc.agiDoc.model.company.Company;
import br.com.agidoc.agiDoc.model.competitor.Competitor;
import br.com.agidoc.agiDoc.model.document.Document;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_COMPANY", referencedColumnName = "ID_COMPANY")
    private Company company;


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


}