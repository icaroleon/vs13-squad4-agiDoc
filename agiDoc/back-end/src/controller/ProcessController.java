package controller;

import data.Data;
import entities.process.Process;
import service.ProcessService;

import java.util.Scanner;

public class ProcessController {
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
        System.out.println(process.toString());
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

    public void update() {
        scanner.nextLine();

        System.out.println("Insira o id do processo a ser buscado: ");
        String id = scanner.nextLine();

        System.out.println("Digite o novo titulo do processo: ");
        String title = scanner.nextLine();

        System.out.println("Digite a nova descrição do processo: ");
        String description = scanner.nextLine();

        Process process = new Process(title, description);

        try {
            service.update(id, process);
            Data.institution.setProcesses(service.getAll());

            System.out.println("Processo " + id + "atualizado com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }

    }

    public void delete() {
        scanner.nextLine();

        System.out.println("Insira o id do processo a ser deletado: ");
        String id = scanner.nextLine();

        try {
            this.service.delete(id);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
