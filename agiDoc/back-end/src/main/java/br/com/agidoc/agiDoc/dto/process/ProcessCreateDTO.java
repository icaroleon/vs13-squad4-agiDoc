package br.com.agidoc.agiDoc.dto.process;


import br.com.agidoc.agiDoc.model.competitor.Competitor;
import br.com.agidoc.agiDoc.model.document.Document;
import br.com.agidoc.agiDoc.model.process.ProcessStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.UUID;

@NoArgsConstructor
@Data
public class ProcessCreateDTO {

    @NotBlank(message = "Title must not be blank")
    private String title;

    @NotBlank(message = "Description must not be blank")
    @Size(max = 500, message = "Description must not be longer than 500 characters")
    private String description;
}
