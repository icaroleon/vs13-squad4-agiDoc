package br.com.agidoc.agiDoc.model.institution;

import br.com.agidoc.agiDoc.model.process.Process;
import br.com.agidoc.agiDoc.model.juridical.AbstractJuridical;
import br.com.agidoc.agiDoc.model.address.Address;
import br.com.agidoc.agiDoc.model.contact.Contact;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.ArrayList;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Institution extends AbstractJuridical {

    private int id;
    private ArrayList<Process> processes;
}
