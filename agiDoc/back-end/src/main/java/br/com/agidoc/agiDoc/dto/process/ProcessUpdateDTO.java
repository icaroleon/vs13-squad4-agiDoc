package br.com.agidoc.agiDoc.dto.process;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcessUpdateDTO {

    @NotBlank
    private String title;
    private String description;
}
