package br.com.agidoc.agiDoc.dto.contact;

import br.com.agidoc.agiDoc.model.contact.pk.ContactAssociationPk;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactAssociationCreateDTO {
    @NotNull
    private ContactAssociationPk contactAssociationPk;
}