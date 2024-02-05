package br.com.agidoc.agiDoc.dto.contact;

import br.com.agidoc.agiDoc.model.contact.pk.ContactAssociationPk;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactAssociationDTO {
    private ContactAssociationPk contactAssociationPk;
}