package br.com.agidoc.agiDoc.dto.institution;

import br.com.agidoc.agiDoc.dto.address.AddressCreateDTO;
import br.com.agidoc.agiDoc.dto.contact.ContactCreateDTO;
import br.com.agidoc.agiDoc.model.address.Address;
import br.com.agidoc.agiDoc.model.contact.Contact;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InstitutionCreateDTO {
    @NotNull
    @Size(max = 100)
    @Schema(required = true, description = "Nome da instituição", example = "JuisBrasil")
    private String companyName;
    @NotNull
    @Size(min = 14, max = 14)
    @Schema(required = true, description = "Cadastro Nacional da Pessoa Jurídica", example = "11111121111111")
    private String cnpj;
    private AddressCreateDTO addressCreateDTO;
    private ContactCreateDTO contactCreateDTO;
}