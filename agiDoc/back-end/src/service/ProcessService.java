package service;

import java.util.ArrayList;
import entities.competitor.Competitor;
import entities.document.Document;
import entities.process.Process;


public class ProcessService {
    private static final ArrayList<Process> processes = new ArrayList<>();

    public ProcessService() {
    }

    public Process create(String id, ArrayList<Competitor> competitors, ArrayList<Document> documents) {
        return new Process(id, competitors, documents);
    }

    public Process findOne(String id) throws Exception {
        for (Process process : processes) {
            String processId = process.getId();

            if (processId.equals(id))
                return process;
        }

        throw new Exception("entities.process.Process not found!");
    }

    public ArrayList<Process> findAll() {
        return processes;
    }

    public Process update(String id, Process newProcess) throws Exception {
        Process process = this.findOne(id);

        process.setCompetitors(newProcess.getCompetitors());
        process.setDocuments(newProcess.getDocuments());
        process.setStatus(newProcess.getStatus());

        return process;
    }

    public void delete(String id) {
        try {
            Process process = this.findOne(id);

            processes.remove(process);

            System.out.println("entities.process.Process deleted successfully!");

            return;
        } catch (Exception e) {
            if (e.getMessage().contains("not found")) {
                System.out.println("entities.process.Process not found!");
            }
        }
    }
}
