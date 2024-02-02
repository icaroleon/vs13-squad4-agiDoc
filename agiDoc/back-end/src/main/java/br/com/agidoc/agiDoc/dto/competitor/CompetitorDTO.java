package br.com.agidoc.agiDoc.dto.competitor;

import br.com.agidoc.agiDoc.dto.juridical.JuridicalDTO;
import br.com.agidoc.agiDoc.model.document.Document;
import br.com.agidoc.agiDoc.model.process.Process;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompetitorDTO {
    private Integer idCompetitor;

    private String companyName;

    private String cnpj;
}
