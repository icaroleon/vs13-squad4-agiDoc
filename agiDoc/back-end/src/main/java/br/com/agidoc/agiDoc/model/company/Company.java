package br.com.agidoc.agiDoc.model.company;

import br.com.agidoc.agiDoc.model.Status;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "COMPANY")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_COMPANY")
    @SequenceGenerator(name = "SEQ_COMPANY", sequenceName = "seq_company", allocationSize = 1)
    @Column(name = "id_company")
    private Integer idCompany;

    @Column(name = "cnpj")
    private String cnpj;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "type")
    private Type type;

    @Column(name = "status")
    private Status status;
}
