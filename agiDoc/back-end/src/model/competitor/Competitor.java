package model.competitor;

import model.document.Document;
import model.employee.Employee;
import model.juridical.AbstractJuridical;
import model.process.Process;

import java.util.ArrayList;
import java.util.UUID;

public class Competitor extends AbstractJuridical {

    private String id;
    private String companyName;
    private boolean isContracted = false;
    private Process process;
    private ArrayList<Document> documents;


    public Competitor(){}

    public Competitor(String cpnj, String address, String contact, String companyName) {
        super(cpnj, address, contact);

        UUID uuid = UUID.randomUUID();

        this.id = uuid.toString();
        this.companyName = companyName;
    }

    public Competitor(String cpnj, String address, String contact, ArrayList<Employee> employees, String companyName, Process process, ArrayList<Document> documents) {
        super(cpnj, address, contact, employees);

        UUID uuid = UUID.randomUUID();

        this.id = uuid.toString();
        this.companyName = companyName;
        this.process = process;
        this.documents = documents;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString(){
        return """
                Id: %s
                Nome da Empresa: %s
                CNPJ: %s
                Contratado: %s
                Endereço: %s
                Contato: %s
                """.formatted(
                this.id,
                this.companyName,
                this.cnpj,
                this.isContracted,
                this.address,
                this.contact
        );
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
