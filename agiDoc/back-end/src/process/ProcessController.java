package process;

import java.util.ArrayList;

public class ProcessController {
    private static ArrayList<Process> processes = new ArrayList<>();

    public ProcessController() {
    }

    public Process create(String id, ArrayList<Competitor> competitors, ArrayList<Document> documents) {
        Process createdProcess = new Process(id, competitors, documents);
        return createdProcess;
    }

    public Process findOne(String id) throws Exception {
        for (Process process : processes) {
            String processId = process.getId();

            if (processId.equals(id))
                return process;
        }

        throw new Exception("Process not found!");
    }

    public ArrayList<Process> findAll() {
        return processes;
    }

    public Process update(String id, Process newProcess) {
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

            System.out.println("Process deleted successfully!");

            return;
        } catch (Exception e) {
            if (e.getMessage().contains("not found")) {
                System.out.println("Process not found!");
            }
        }
    }
}
