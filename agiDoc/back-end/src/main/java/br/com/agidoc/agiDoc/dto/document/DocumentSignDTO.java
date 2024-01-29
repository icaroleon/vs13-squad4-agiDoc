package br.com.agidoc.agiDoc.dto.document;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;

public class DocumentSignDTO {

    @NotNull
    @Schema(description = "Flag indicating whether the document is signed. True if signed, false otherwise.", required = true, example = "false | true")
    private boolean signed;
}
