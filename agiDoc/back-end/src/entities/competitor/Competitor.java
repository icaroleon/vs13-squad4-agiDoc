package entities.competitor;

import entities.document.Document;
import entities.employee.Employee;
import entities.juridical.AbstractJuridical;
import entities.process.Process;

import java.util.ArrayList;
import java.util.UUID;

public class Competitor extends AbstractJuridical {

    private String id;
    private String companyName;
    private boolean isContracted = false;
    private Process process;
    private ArrayList<Document> documents;


    public Competitor(){
    }

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
