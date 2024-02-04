package br.com.agidoc.agiDoc.dto.company;

import br.com.agidoc.agiDoc.model.Status;
import br.com.agidoc.agiDoc.model.company.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDTO {
    private Integer idCompany;

    private String cnpj;

    private String companyName;

    private Type type;

    private Status status;
}
