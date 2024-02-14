package br.com.agidoc.agiDoc.dto.user;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserUpdatePasswordDTO {

    @Schema(required = true, example = "old password")
    @NotBlank
    @Size(max = 100)
    private String oldPassword;

    @Schema(required = true, example = "new password")
    @NotBlank
    @Size(max = 100)
    private String newPassword;
}