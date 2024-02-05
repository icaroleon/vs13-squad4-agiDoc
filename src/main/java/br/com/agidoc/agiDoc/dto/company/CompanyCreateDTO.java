package br.com.agidoc.agiDoc.dto.company;

import br.com.agidoc.agiDoc.model.Status;
import br.com.agidoc.agiDoc.model.company.Type;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyCreateDTO {
    @NotBlank(message = "Não pode estar em branco")
    @Schema(description = "Nome da Empresa", required = true, example = "Empresa LTDA")
    private String companyName;

    @NotBlank(message = "Não pode estar em branco")
    @Schema(description = "Número do CNPJ", required = true, example = "00456789000115")
    @Size(min = 14, max = 14)
    private String cnpj;

    @NotNull(message = "Não pode estar nulo")
    @Schema(required = true, example = "0 = INSTITUTION, 1 = COMPETITOR")
    private Type type;
}
