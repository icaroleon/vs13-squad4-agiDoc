import document.Document;

import java.util.ArrayList;

public class Competitor {

    private boolean competitor;
    private ArrayList<Document> documents;

    public Competitor(boolean competitor, ArrayList documents) {
        this.competitor = competitor;
        this.documents = documents;
    }

    public boolean isCompetitor() {
        return competitor;
    }

    public void setCompetitor(boolean competitor) {
        this.competitor = competitor;
    }

    public ArrayList getDocuments() {
        return documents;
    }

    public void setDocuments(ArrayList documents) {
        this.documents = documents;
    }
}
