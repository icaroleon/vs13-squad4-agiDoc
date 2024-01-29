package br.com.agidoc.agiDoc.dto.document;

import br.com.agidoc.agiDoc.model.Associated;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentCreateDTO {

    @FutureOrPresent(message = "Expiration date must be in the future or present")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Schema(example = "30-12-2024")
    private LocalDate expirationDate;

    @NotBlank(message = "Title must not be blank")
    @Schema(example = "Edital de Abertura de Processo Licitatório")
    private String title;

    @Schema(description = "Path or name of the file", example = "document.pdf")
    private String file;
}
