package br.com.agidoc.agiDoc.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDTO {
    @Schema(required = true, example = "joaosilva123")
    @NotBlank
    @Size(max = 100)
    String username;

    @Schema(required = true, example = "senhajoao3352")
    @NotBlank
    @Size(max = 100)
    String password;
}
