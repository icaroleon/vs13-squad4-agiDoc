import document.Document;

import java.util.ArrayList;

public class Competitor {

    private boolean competitor;
    private ArrayList<Document> documents;

    public Competitor(boolean competitor, ArrayList<Document> documents) {
        this.competitor = competitor;
        this.documents = documents;
    }

    public boolean isCompetitor() {
        return competitor;
    }

    public void setCompetitor(boolean competitor) {
        this.competitor = competitor;
    }

    public ArrayList<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(ArrayList<Document> documents) {
        this.documents = documents;
    }
}
