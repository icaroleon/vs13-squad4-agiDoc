package controller;

import database.Data;
import model.competitor.Competitor;
import model.process.Process;
import service.CompetitorService;
import service.ProcessService;
import service.exceptions.DatabaseException;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class ProcessController {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ProcessService processService = new ProcessService(Data.institution.getProcesses());

    public static void createProcess() throws DatabaseException {
        System.out.print("Digite o titulo do processo: ");
        String title = scanner.nextLine();

        System.out.print("Digite a descrição do processo: ");
        String description = scanner.nextLine();

        Process process = new Process(title, description);

        processService.create(process);
        Data.institution.setProcesses(processService.getAll());

        System.out.println("Processo criado!\n");
        System.out.println(process);
    }

    public static Process get(Integer id) {
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

    public static void update(Integer processId) {
        try {
            Process process = processService.get(processId);

            System.out.print("Digite o novo titulo do processo (deixe em branco para manter o valor atual): ");
            String title = scanner.nextLine();
            title = (title.isEmpty()) ? process.getTitle() : title;

            System.out.print("Digite a nova descrição do processo (deixe em branco para manter o valor atual): ");
            String description = scanner.nextLine();
            description = (description.isEmpty()) ? process.getTitle() : description;

            Process newProcess = new Process(title, description);

            processService.update(processId, newProcess);
            Data.institution.setProcesses(processService.getAll());

            System.out.println("Processo n° " + processId + " atualizado com sucesso.");
            System.out.println(newProcess);
        } catch (Exception | DatabaseException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public static boolean delete(Integer processId) {
        System.out.println("Tem certeza que deseja excluir este processo? (S/N)");
        boolean isNotSure = !scanner.nextLine().equalsIgnoreCase("S");

        if (isNotSure) return false;

        try {
            processService.delete(processId);
            Data.institution.setProcesses(processService.getAll());

            System.out.println("Processo n°: " + processId + " excluído com sucesso!");

            return true;
        } catch (Exception | DatabaseException e) {
            System.out.println("Erro: " + e.getMessage());

            return false;
        }
    }

    public static void closeProcess(Integer processId) {
        System.out.println("Tem certeza que deseja fechar este processo? (S/N)");
        boolean isNotSure = !scanner.nextLine().equalsIgnoreCase("S");

        if (isNotSure) return;

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

    public static void chooseCompetitor(Integer processId) {
        try {
            Process process = processService.get(processId);
            ArrayList<Competitor> competitors = process.getCompetitors();

            competitors.forEach(competitor -> System.out.println(competitor.toString()));

            System.out.print("Selecione o id concorrente a ser escolhido: ");
            String competitorId = scanner.nextLine();

            CompetitorService competitorService = new CompetitorService(competitors);
            process.chooseContractor(competitorService.get(competitorId));

            System.out.println("Concorrente escolhido com sucesso!");
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }
}
