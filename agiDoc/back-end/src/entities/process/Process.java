package entities.process;

import java.util.ArrayList;
import java.util.UUID;

import entities.competitor.Competitor;
import entities.document.Document;

// TODO: Adicionar atributos que faltam conforme diagrama.
public class Process implements IProcess {
    private String id, title, status, description;
    private Competitor contracted;
    private ArrayList<Competitor> competitors;
    private ArrayList<Document> documents;

    public Process() {
    }

    public Process(String title, String description) {
        UUID uuid = UUID.randomUUID();

        this.id = uuid.toString();
        this.title = title;
        this.description = description;
        this.status = "Aberto";
        this.competitors = new ArrayList<Competitor>();
        this.documents = new ArrayList<Document>();
    }

    public Process(String title, String description, ArrayList<Competitor> competitors, ArrayList<Document> documents) {
        UUID uuid = UUID.randomUUID();

        this.id = uuid.toString();
        this.title = title;
        this.description = description;
        this.status = "Aberto";
        this.competitors = competitors;
        this.documents = documents;
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

    public void setId(String id) {
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

    public String getId() {
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
}
