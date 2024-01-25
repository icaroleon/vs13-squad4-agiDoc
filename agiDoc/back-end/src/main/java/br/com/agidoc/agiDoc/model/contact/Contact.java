package br.com.agidoc.agiDoc.model.contact;

import br.com.agidoc.agiDoc.model.Associated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contact {
    private Integer id;
    private String name;
    private String email;
    private String phone;
    private ContactPhoneType phoneType;
    private Associated associated;
    private Integer associatedId;
}
