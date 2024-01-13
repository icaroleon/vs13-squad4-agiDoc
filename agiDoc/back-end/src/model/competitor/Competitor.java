package model.competitor;

//import model.contact.Contact;
import model.document.Document;
import model.juridical.AbstractJuridical;
import model.process.Process;

import java.util.ArrayList;

public class Competitor extends AbstractJuridical {

    private int id;
    private String companyName;
    private boolean isContracted = false;
    private Process process;
    private ArrayList<Document> documents;


    public Competitor(){}

    public Competitor(String cpnj, String companyName) {
        super(cpnj, companyName);
    }

//    TODO: Precisa contact
//    public Competitor(String cpnj, Address address, Contact contact, String companyName) {
//        super(cpnj, address, contact);
//        this.companyName = companyName;
//    }

//    TODO: Precisa contact
//    public Competitor(String cpnj, Address address, Contact contact, ArrayList<Employee> employees, String companyName, Process process, ArrayList<Document> documents) {
//        super(cpnj, address, contact, employees);
//        this.companyName = companyName;
//        this.process = process;
//        this.documents = documents;
//    }

//    TODO: Falta adicionar o contato
    @Override
    public String toString(){
        return """
                Id: %s
                Nome da Empresa: %s
                CNPJ: %s
                Contratado: %s
                Endereço: %s n° %d - %s. %s - %s / CEP %s
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
                this.address.getZipCode()
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

    public boolean isContracted() {
        return isContracted;
    }

    public void setContracted(boolean contracted) {
        isContracted = contracted;
    }

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }

    public ArrayList<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(ArrayList<Document> documents) {
        this.documents = documents;
    }
}
