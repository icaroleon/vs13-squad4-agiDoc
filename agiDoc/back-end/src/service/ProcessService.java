package service;

import java.util.ArrayList;
import entities.process.Process;

public class ProcessService {
    private ArrayList<Process> processes = new ArrayList<>();

    public ProcessService() {
    }

    public ProcessService(ArrayList<Process> processes) {
        this.processes = processes;
    }

    public Process create(Process process) {
        processes.add(process);
        return process;
    }

    public Process get(String id) throws Exception {
        for (Process process : processes) {
            String processId = process.getId();

            if (processId.equals(id))
                return process;
        }

        throw new Exception("Processo nao encontrado!");
    }

    // TODO: trocar nome do método para getAll
    public ArrayList<Process> getAll() {
        return processes;
    }

    public Process update(String id, Process newProcess) throws Exception {
        Process process = this.get(id);

        process.setTitle(newProcess.getTitle());
        process.setDescription(newProcess.getDescription());
        process.setStatus(newProcess.getStatus());
        process.setCompetitors(newProcess.getCompetitors());
        process.setDocuments(newProcess.getDocuments());
        process.setStatus(newProcess.getStatus());

        return process;
    }

    public void delete(String id) throws Exception {
        Process process = this.get(id);

        processes.remove(process);
    }

    public void setProcesses(ArrayList<Process> processes) {
        this.processes = processes;
    }
}
