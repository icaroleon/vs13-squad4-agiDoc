package model.process;

import java.util.ArrayList;
import java.util.UUID;

import model.competitor.Competitor;
import model.document.Document;
import model.document.DocumentType;

public class Process implements IProcess {
    UUID uuid = UUID.randomUUID();

    private Integer id;
    private String processNumber = uuid.toString().substring(0, 6);
    private String title, description;
    private String status = "Aberto";
    private Competitor contracted;
    private ArrayList<Competitor> competitors;
    private ArrayList<Document> documents;
    private Integer institutionId = 1;

    public Process(Integer id, String processNumber) {
        this.id = id;
    }

    public Process(Integer institutionId, Integer id, String processNumber, String title, String description, ArrayList<Competitor> competitors, ArrayList<Document> documents) {
        this.institutionId = id;
        this.id = id;
        this.processNumber = processNumber;
        this.title = title;
        this.description = description;
        this.status = "Aberto";
        this.competitors = competitors;
        this.documents = documents;
    }

    public Process() {
    }

    public Process(String title, String description) {
    }

    public String getProcessNumber() {
        return processNumber;
    }

    public void setProcessNumber(String processNumber) {
    }

    public void setContracted(Competitor contracted) {
        this.contracted = contracted;
    }

    public Integer getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(Integer institutionId) {
        this.institutionId = institutionId;
    }

    public boolean chooseContractor(Competitor competitor) {
        this.contracted = competitor;

        return true;
    }

    public boolean subscribe(Competitor competitor) {
        if (competitor == null)
            return false;

        this.competitors.add(competitor);
        return true;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Competitor> getCompetitors() {
        return competitors;
    }

    public void setCompetitors(ArrayList<Competitor> competitors) {
        this.competitors = competitors;
    }

    public ArrayList<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(ArrayList<Document> documents) {
        this.documents = documents;
    }

    public Integer getId() {
        return id;
    }

    public Competitor getContracted() {
        return contracted;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return """
                Id: %s
                Titulo: %s
                Status: %s
                Descrição: %s
                Contratado: %s
                N° Documentos: %d
                N° Concorrentes: %d
                """.formatted(
                    this.id,
                    this.title,
                    this.status,
                    this.description,
                    this.contracted == null ? "" : this.contracted.getCompanyName(),
                    this.documents.size(),
                    this.competitors.size()
                );
    }
}
