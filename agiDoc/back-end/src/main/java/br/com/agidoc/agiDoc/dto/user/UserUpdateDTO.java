package br.com.agidoc.agiDoc.dto.user;

import br.com.agidoc.agiDoc.model.Status;
import br.com.agidoc.agiDoc.model.user.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDTO {
    @Schema(required = true, example = "Joao Silva")
    @NotBlank
    @Size(max = 100)
    private String name;

    @Schema(required = true, example = "senhajoao3352")
    @NotBlank
    @Size(max = 100)
    private String password;

    @Schema(required = true, example = "0 = ADMIN, 1 = COMPETITOR, 2 = INSTITUTIONAL_USER")
    @NotNull
    private Role role;

    @Schema(required = true, example = "Analista de Software")
    @NotBlank
    @Size(max = 100)
    private String position;

    @Schema(required = true, example = "0 = INATIVO, 1 = ATIVO")
    @NotNull
    private Status status;

    @Schema(required = true, example = "fulano@email.com")
    @NotNull
    private String email;
}
