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
public class ContactUpdateDTO {

    @Schema(required = true, example = "Lopes Lima")
    @NotBlank
    @Size(max = 100)
    private String name;

    @Schema(required = true, example = "lopes.ls@cmail.com")
    @Email
    @Size(max = 100)
    private String email;

    @Schema(required = true, example = "5511442348549")
    @NotBlank
    @Size(max = 50)
    private String phone;

    @Schema(required = true, example = "1")
    @NotNull
    private ContactPhoneType phoneType;
}