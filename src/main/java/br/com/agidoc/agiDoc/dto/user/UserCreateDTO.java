package br.com.agidoc.agiDoc.dto.user;

import br.com.agidoc.agiDoc.model.department.Department;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateDTO {
    @Schema(required = true, example = "Joao Silva")
    @NotBlank
    @Size(max = 100)
    private String name;

    @Schema(required = true, example = "joaosilva123")
    @NotBlank
    @Size(max = 100)
    private String user;

    @Schema(required = true, example = "senhajoao3352")
    @NotBlank
    @Size(max = 100)
    private String password;

    @Schema(required = true, example = "admin | employee")
    @NotBlank
    @Size(max = 100)
    private String role;

    @Schema(required = true, example = "Analista de Software")
    @NotBlank
    @Size(max = 100)
    private String position;
}
