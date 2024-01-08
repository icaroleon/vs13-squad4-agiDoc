package controller;

import data.Data;
import entities.competitor.Competitor;
import entities.process.Process;
import service.CompetitorService;
import service.ProcessService;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class ProcessController {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ProcessService processService = new ProcessService(Data.institution.getProcesses());
    private static final CompetitorService competitorService = new CompetitorService();

    public static void createProcess() {
        System.out.println("Digite o titulo do processo: ");
        String title = scanner.nextLine();

        System.out.println("Digite a descrição do processo: ");
        String description = scanner.nextLine();

        Process process = new Process(title, description);

        processService.create(process);
        Data.institution.setProcesses(processService.getAll());

        System.out.println("Processo criado");
        System.out.println(process);
    }

    public static Process get(String id) {
        Process process = null;
        try {
            process = processService.get(id);
            System.out.println(process.toString());
        } catch (Exception e) {
            System.out.println("Processo nao encontrado!");
        }
        return process;
    }

    public static void getAll() {
        for (Process process : processService.getAll()) {
            System.out.println(process.toString());
        }
    }

    public static void update(String processId) {
        System.out.println("Digite o novo titulo do processo: ");
        String title = scanner.nextLine();

        System.out.println("Digite a nova descrição do processo: ");
        String description = scanner.nextLine();

        Process newProcess = new Process(title, description);

        try {
            processService.update(processId, newProcess);
            Data.institution.setProcesses(processService.getAll());

            System.out.println("Processo " + processId + "atualizado com sucesso.");
            System.out.println(newProcess);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public static void delete(String processId) {
        try {
            processService.delete(processId);
            Data.institution.setProcesses(processService.getAll());

            System.out.println("Processo n°: " + processId + " excluído com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public static void closeProcess(String processId) {
        try {
            Process process = processService.get(processId);

            if (process.getContracted() == null) {
                System.out.println("ERRO: Processo não tem concorrente contratado!");
                return;
            }

            process.setStatus("Fechado");

            Data.institution.setProcesses(processService.getAll());

            System.out.println("Processo n°: " + processId + " fechado.");
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }

    public static void chooseCompetitor(String processId) {
        try {
            Process process = processService.get(processId);
            ArrayList<Competitor> competitors = process.getCompetitors();

            competitors.forEach(competitor -> System.out.println(competitor.toString()));

            System.out.println("Selecione o id concorrente a ser escolhido: ");
            String competitorId = scanner.nextLine();

            Competitor competitor = competitorService.get(competitorId);

            process.chooseContractor(competitor);

            System.out.println("Concorrente escolhido com sucesso!");
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        }

    }
}
