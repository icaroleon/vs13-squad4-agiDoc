package br.com.agidoc.agiDoc.model.institution;

import br.com.agidoc.agiDoc.model.process.Process;
import br.com.agidoc.agiDoc.model.juridical.AbstractJuridical;
import br.com.agidoc.agiDoc.model.address.Address;
import br.com.agidoc.agiDoc.model.contact.Contact;


import java.util.ArrayList;

public class Institution extends AbstractJuridical {

    private Integer id;
    private ArrayList<Process> processes;


    public Institution() {
        super();
    }

    public Institution(String companyName, String cnpj, Address address, Contact contact, ArrayList<Process> processes) {
        super(companyName, cnpj, address, contact);
        this.processes = processes;
    }

    public Institution(Integer idInstitution, String companyName,String cnpj){
        setId(idInstitution);
        setCompanyName(companyName);
        setCnpj(cnpj);
    }

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

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Process> getProcesses() { return this.processes; }

    public void setProcesses(ArrayList<Process> processes) {
        this.processes = processes;
    }
}
