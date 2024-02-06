package br.com.agidoc.agiDoc.dto.user;

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
public class UserLoginDTO {
    @Schema(required = true, example = "joaosilva123",  deprecated = false)
    @NotNull
    @Size(max = 100)
    private String user;

    @Schema(required = true, example = "senhajoao3352",  deprecated = false)
    @NotNull
    @Size(max = 100)
    private String password;
}
