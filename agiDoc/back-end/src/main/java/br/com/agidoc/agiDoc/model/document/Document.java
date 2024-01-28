package br.com.agidoc.agiDoc.model.document;

import java.time.LocalDate;
import java.util.UUID;

import br.com.agidoc.agiDoc.model.Associated;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Document {
    private Integer id;

    @NotBlank
    private String protocol = UUID.randomUUID().toString().substring(0, 6);

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate expirationDate;

    private boolean signed;
    private String file;
    private Associated associated;
    private Integer processId;
}