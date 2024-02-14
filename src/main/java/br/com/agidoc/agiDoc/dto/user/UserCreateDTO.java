package br.com.agidoc.agiDoc.dto.user;

import br.com.agidoc.agiDoc.model.permission.Permission;
import br.com.agidoc.agiDoc.model.user.Department;
import br.com.agidoc.agiDoc.model.user.PermissionType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateDTO {
    @Schema(required = true, example = "joaosilva123", deprecated = false)
    @NotBlank
    @Size(max = 100)
    private String name;

    @Schema(required = true, example = "senhajoao3352", deprecated = false)
    @NotBlank
    @Size(max = 100)
    private String user;

    @Schema(required = true, example = "senhajoao3352")
    @NotBlank
    @Size(max = 100)
    private String password;

    @NotNull
    private List<String> permission;

//    @Schema(required = true, example = "0 = ADMIN, 1 = COMPETITOR, 2 = INSTITUTIONAL_USER")
//    @NotNull
//    private PermissionType permissionType;

    @Schema(required = true, example = "Analista de Software")
    @NotBlank
    @Size(max = 100)
    private String position;

    @Schema(required = true, example = "fulano@email.com")
    @NotBlank
    private String email;

    @Schema(required = true, example = "SECRETARIA_EDUCACAO = 0, SECRETARIA_FAZENDA = 1, SECRETARIA_SAUDE = 2, SECRETARIA_ACAO_SOCIAL = 3")
    @NotNull
    private Department department;
}
