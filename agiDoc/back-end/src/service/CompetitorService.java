package service;

import entities.competitor.Competitor;
import entities.process.Process;

import java.util.ArrayList;

public class CompetitorService {

    private ArrayList<Competitor> competitors = new ArrayList<>();

    public CompetitorService(){
    }

    public CompetitorService(ArrayList<Competitor> competitors){
        this.competitors = competitors;
    }

    public Competitor create(Competitor competitor){
        competitors.add(competitor);
        return competitor;
    }

    public Competitor get(String id) throws Exception {
        for (Competitor competitor : competitors) {
            String competitorId = competitor.getId();

            if (competitorId.equals(id))
                return competitor;
        }

        throw new Exception("Competidor não encontrado!");
    }

    public ArrayList<Competitor> getAll() {
        return competitors;
    }

    public Competitor update(String id, Competitor newCompetitor) throws Exception {

        Competitor competitor = this.get(id);
        competitor.setCompanyName(newCompetitor.getCompanyName());
        competitor.setCnpj(newCompetitor.getCnpj());
        competitor.setAddress(newCompetitor.getAddress());
        competitor.setContact(newCompetitor.getContact());

        return competitor;

    }

    //TODO: verificar necessidade de uma refatoração no try catch
    public void delete(String id){
        try {
            Competitor competitor = this.get(id);

            competitors.remove(competitor);

            System.out.println("Competidor deletado com sucesso");

        } catch (Exception e) {
            if (e.getMessage().contains("not found")) {
                System.out.println("Competidor não encontrado!");
            }
        }
    }

    public void setCompetitors(ArrayList<Competitor> competitors) {
        this.competitors = competitors;
    }
}
