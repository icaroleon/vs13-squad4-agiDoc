package br.com.agidoc.agiDoc.dto.institution;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InstitutionCreateDTO {
    @NotBlank
    @Size(max = 100)
    @Schema(required = true, description = "Nome da instituição", example = "JuisBrasil")
    private String companyName;
    private Integer idInstitution;
    @NotBlank
    @Size(min = 14, max = 14)
    @Schema(required = true, description = "Cadastro Nacional da Pessoa Jurídica", example = "11111111111111")
    private String cnpj;
}