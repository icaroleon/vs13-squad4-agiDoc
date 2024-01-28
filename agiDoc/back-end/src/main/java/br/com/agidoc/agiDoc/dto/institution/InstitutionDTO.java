package br.com.agidoc.agiDoc.dto.institution;

import br.com.agidoc.agiDoc.model.address.Address;
import br.com.agidoc.agiDoc.model.contact.Contact;
import br.com.agidoc.agiDoc.model.institution.Institution;
import br.com.agidoc.agiDoc.model.process.Process;
import br.com.agidoc.agiDoc.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InstitutionDTO {
    private Integer idInstitution;
    private String companyName;
    private String cnpj;
    private Address address;
    private Contact contact;
    private ArrayList<User> users;
    private ArrayList<Process> processes;

    @Override
    public String toString() {
        return """ 
                {
                    CNPJ: %s
                    Company Name: %s
                    Address: %s
                    Contact: %s
                }
                """.formatted(
                this.cnpj,
                this.companyName,
                this.address,
                this.contact
        ).replace("Null", "não informado.");
    }
}