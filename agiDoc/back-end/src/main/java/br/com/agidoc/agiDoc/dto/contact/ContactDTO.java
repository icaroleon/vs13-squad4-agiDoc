package br.com.agidoc.agiDoc.dto.contact;

import br.com.agidoc.agiDoc.model.contact.ContactPhoneType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactDTO {
    private Integer idContact;
    private String name;
    private String email;
    private String phone;
    private ContactPhoneType phoneType;
}