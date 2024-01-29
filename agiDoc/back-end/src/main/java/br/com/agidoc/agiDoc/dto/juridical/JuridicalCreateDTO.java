package br.com.agidoc.agiDoc.dto.juridical;

import br.com.agidoc.agiDoc.model.address.Address;
import br.com.agidoc.agiDoc.model.contact.Contact;
import br.com.agidoc.agiDoc.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JuridicalCreateDTO {
    private String cnpj;
    private String companyName;
    private Integer idAddress;
    private Integer idContact;
    private ArrayList<Integer> idUsers;
}
