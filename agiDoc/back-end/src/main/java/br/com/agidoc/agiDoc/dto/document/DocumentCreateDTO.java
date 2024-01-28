package br.com.agidoc.agiDoc.dto.document;

import br.com.agidoc.agiDoc.model.Associated;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class DocumentCreateDTO {

    @FutureOrPresent(message = "Expiration date must be in the future or present")
    @Schema(required = true, example = "30-12-2024", description = "The date on which the process expires. Must be either today or a future date.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate expirationDate;

    @NotBlank(message = "Title must not be blank")
    private String title;

    @NotBlank(message = "Description must not be blank")
    @Size(max = 500, message = "Description must not be longer than 500 characters")
    private String descriptionProcess;

    private boolean signed;
}