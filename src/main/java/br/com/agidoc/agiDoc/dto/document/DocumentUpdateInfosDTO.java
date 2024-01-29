package br.com.agidoc.agiDoc.dto.document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class DocumentUpdateInfosDTO {

    @NotBlank(message = "Title must not be blank")
    private String title;

    @NotBlank(message = "Description must not be blank")
    @Size(max = 500, message = "Description must not be longer than 500 characters")
    private String descriptionProcess;
}
