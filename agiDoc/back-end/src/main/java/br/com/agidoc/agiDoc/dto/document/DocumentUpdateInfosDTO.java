package br.com.agidoc.agiDoc.dto.document;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class DocumentUpdateInfosDTO {

    @Schema(example = "Edital de Abertura de Processo Licitatório")
    private String title;

    @Size(max = 500, message = "Description must not be longer than 500 characters")
    private String description;

    @Schema(description = "Path or name of the file", example = "document.pdf")
    private String file;

    @FutureOrPresent(message = "Expiration date must be in the future or present")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Schema(example = "30-12-2024")
    private LocalDate expirationDate;
}
