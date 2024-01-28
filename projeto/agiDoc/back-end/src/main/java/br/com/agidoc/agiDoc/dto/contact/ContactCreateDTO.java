package br.com.agidoc.agiDoc.dto.contact;

import br.com.agidoc.agiDoc.model.contact.ContactPhoneType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactCreateDTO {
    @Schema(required = true, example = "Joao Silva")
    @NotBlank
    @Size(max = 255)
    private String name;

    @Schema(required = true, example = "joao.silva@email.com")
    @Email
    @Size(max = 100)
    private String email;

    @Schema(required = true, example = "5511992348549")
    @NotBlank
    @Size(max = 50)
    private String phone;

    @Schema(required = true, example = "0")
    @NotNull
    private ContactPhoneType phoneType;
}
