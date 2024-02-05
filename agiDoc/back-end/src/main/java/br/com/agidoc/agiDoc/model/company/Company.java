package br.com.agidoc.agiDoc.model.company;

import br.com.agidoc.agiDoc.model.Status;
import br.com.agidoc.agiDoc.model.process.Process;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "COMPANY")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_COMPANY")
    @SequenceGenerator(name = "SEQ_COMPANY", sequenceName = "seq_company", allocationSize = 1)
    @Column(name = "id_company")
    private Integer companyId;

    @Column(name = "cnpj")
    private String cnpj;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "type")
    private Type type;

    @Column(name = "status")
    private Status status;

    @JsonIgnore
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Process> process = new HashSet<>();
}
