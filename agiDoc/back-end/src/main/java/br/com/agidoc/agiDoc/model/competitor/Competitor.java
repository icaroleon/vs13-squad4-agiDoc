package br.com.agidoc.agiDoc.model.competitor;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "COMPETITORS")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Competitor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_COMPETITORS")
    @SequenceGenerator(name = "SEQ_COMPETITORS", sequenceName = "seq_competitors", allocationSize = 1)
    @Column(name = "id_competitors")
    private Integer idCompetitor;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "cnpj")
    private String cnpj;
}
