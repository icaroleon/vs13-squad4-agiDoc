package model.competitor;

import model.address.Address;
import model.contact.Contact;
import model.document.Document;
import model.juridical.AbstractJuridical;
import model.process.Process;
import model.user.User;

import java.util.ArrayList;

public class Competitor extends AbstractJuridical {
    private int id;
    private int isContracted;
    private ArrayList<Process> processes;
    private ArrayList<Document> documents;
    private int processId;


    public Competitor(){}

    public Competitor(String cpnj, String companyName) {
        super(cpnj, companyName);
    }

    public Competitor(String cpnj, Address address, Contact contact, String companyName) {
        super(cpnj, companyName, address, contact);
    }

    public Competitor(String cpnj, Address address, Contact contact, ArrayList<User> employees, String companyName, ArrayList<Process> processes, ArrayList<Document> documents) {
        super(cpnj, companyName, address, contact, employees);
        this.processes = processes;
        this.documents = documents;
    }

    @Override
    public String toString(){
        return """
                Id: %s
                Nome da Empresa: %s
                CNPJ: %s
                Contratado: %s
                Endereço: %s n° %d - %s. %s - %s / CEP %s
                Contato: %s - %s / %s
                email: %s
                """.formatted(
                this.id,
                this.companyName,
                this.cnpj,
                this.isContracted,
                this.address.getStreet(),
                this.address.getNumber(),
                this.address.getDistrict(),
                this.address.getCity(),
                this.address.getState(),
                this.address.getZipCode(),
                this.contact.getName(),
                this.contact.getPhone(),
                this.contact.getPhoneType(),
                this.contact.getEmail()
        );
    }

    public int getId() { return id; };

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getIsContracted() {
        return isContracted;
    }

    public void setIsContracted(int isContracted) {
        this.isContracted = isContracted;
    }

    public ArrayList<Process> getProcess() {
        return processes;
    }

    public void setProcess(ArrayList<Process> processes) {
        this.processes = processes;
    }

    public ArrayList<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(ArrayList<Document> documents) {
        this.documents = documents;
    }

    public int getProcessId() {
        return processId;
    }

    public void setProcessId(int processId) {
        this.processId = processId;
    }
}
