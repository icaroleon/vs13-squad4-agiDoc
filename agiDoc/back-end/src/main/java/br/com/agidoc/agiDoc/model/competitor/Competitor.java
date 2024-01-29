package br.com.agidoc.agiDoc.model.competitor;

import br.com.agidoc.agiDoc.model.address.Address;
import br.com.agidoc.agiDoc.model.contact.Contact;
import br.com.agidoc.agiDoc.model.document.Document;
import br.com.agidoc.agiDoc.model.juridical.AbstractJuridical;
import br.com.agidoc.agiDoc.model.process.Process;
import br.com.agidoc.agiDoc.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Competitor extends AbstractJuridical {
    private int id;
    private int isContracted;
    private ArrayList<Process> processes;
    private ArrayList<Document> documents;
    private int processId;
}
