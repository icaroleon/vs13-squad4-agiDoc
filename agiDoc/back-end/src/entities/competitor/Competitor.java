package entities.competitor;

import entities.document.Document;
import entities.employee.Employee;
import entities.juridical.AbstractJuridical;

import java.util.ArrayList;

public class Competitor extends AbstractJuridical {

    private String companyName;
    private boolean isContracted = false;
    private Process process;
    private ArrayList<Document> documents;


    public Competitor(){
    }

    public Competitor(String cpnj, String address, String contact, String companyName) {
        super(cpnj, address, contact);
        this.companyName = companyName;
    }

    public Competitor(String cpnj, String address, String contact, ArrayList<Employee> employees, String companyName, Process process, ArrayList<Document> documents) {
        super(cpnj, address, contact, employees);
        this.companyName = companyName;
        this.process = process;
        this.documents = documents;
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
