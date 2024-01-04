import java.util.ArrayList;

public class Process implements IProcess {
    String id, status;
    Competitor contracted;
    ArrayList<Competitor> competitors;
    ArrayList<Document> documents;

    public Process(String id, ArrayList<Competitor> competitors, ArrayList<Document> documents) {
        this.id = id;
        this.competitors = competitors;
        this.documents = documents;
    }

    public boolean chooseContractor() {
        return true;
    }

    public boolean subscribe(Competitor competitor) {
        return true;
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
