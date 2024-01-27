package br.com.agidoc.agiDoc.dto.document;

import br.com.agidoc.agiDoc.model.Associated;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class DocumentCreateDTO {

    private Integer id;
    private String protocol;

    @FutureOrPresent
    @Schema(required = true, example = "30-12-2024")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate expirationDate;

    private boolean signed;


    private String file;

    private Associated associated;

    private String processNumber;

    private Integer processId;
    private String titleProcess, descriptionProcess;
}
