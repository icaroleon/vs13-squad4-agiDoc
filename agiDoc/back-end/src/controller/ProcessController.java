package controller;

import data.Data;
import entities.process.Process;
import service.ProcessService;

import java.util.Scanner;

public abstract class ProcessController {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ProcessService service = new ProcessService(Data.institution.getProcesses());

    public static void createProcess() {
        System.out.println("Digite o titulo do processo: ");
        String title = scanner.nextLine();

        System.out.println("Digite a descrição do processo: ");
        String description = scanner.nextLine();

        Process process = new Process(title, description);

        service.create(process);
        Data.institution.setProcesses(service.getAll());

        System.out.println("Processo criado");
        System.out.println(process);
    }

    public static Process get(String id) {
        Process process = null;
        try {
            process = service.get(id);
            System.out.println(process.toString());
        } catch (Exception e) {
            System.out.println("Processo nao encontrado!");
        }
        return process;
    }

    public static void getAll() {
        for (Process process : service.getAll()) {
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
            service.update(processId, newProcess);
            Data.institution.setProcesses(service.getAll());

            System.out.println("Processo " + processId + "atualizado com sucesso.");
            System.out.println(newProcess);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public static void delete(String processId) {
        try {
            service.delete(processId);
            Data.institution.setProcesses(service.getAll());

            System.out.println("Processo n°: " + processId + " excluído com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public static void closeProcess(String processId, Process process) {
        try {
//          TODO: Validar se processo tem concorrente escolhido.
            process.setStatus("Fechado");

            service.update(processId, process);
            Data.institution.setProcesses(service.getAll());

            System.out.println("Processo n°: " + processId + " fechado.");
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
    // TODO: criar método para escolher concorrente no processo
    public static void chooseCompetitor(String processId, Process process) {
    }
}
