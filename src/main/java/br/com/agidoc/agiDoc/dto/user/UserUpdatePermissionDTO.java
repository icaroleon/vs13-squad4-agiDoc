package br.com.agidoc.agiDoc.dto.user;


import br.com.agidoc.agiDoc.model.permission.Permission;
import br.com.agidoc.agiDoc.model.user.PermissionType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdatePermissionDTO {
    @Schema(description = "Permission type", example = "ADMIN/COMPETITOR/INSTITUTIONAL_USER")
    @NotNull
    private String permission;
}