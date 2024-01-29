package br.com.agidoc.agiDoc.dto.process;

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
public class ProcessUpdateDTO {

    @Schema(example = "Licitação de Obra Pública")
    private String title;

    @Size(max = 500, message = "Description must not be longer than 500 characters")
    @Schema(example = "Licitação de Obra Pública para pavimentação da estrada X, localizada em Porto Alegre.")
    private String description;
}
