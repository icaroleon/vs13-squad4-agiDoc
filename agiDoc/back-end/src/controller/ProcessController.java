package controller;

import entities.process.Process;
import service.ProcessService;

import java.util.Scanner;

public class ProcessController {
    Scanner scanner = new Scanner(System.in);
    ProcessService service = new ProcessService();

    public void createProcess() {
        scanner.nextLine();

        System.out.println("Digite o titulo do processo: ");
        String title = scanner.nextLine();

        System.out.println("Digite a descricao do processo: ");
        String description = scanner.nextLine();

        Process process = new Process(title, description);

        service.create(process);

        System.out.println("Processo criado");
        System.out.println(process.toString());
    }

    public void get() {
        scanner.nextLine();
        System.out.println("Insira o id do processo a ser buscado: ");
        String id = scanner.nextLine();

        try {
            Process process = this.service.get(id);
            System.out.println(process.toString());
        } catch (Exception e) {
            System.out.println("Processo nao encontrado!");
        }
    }

    public void getAll() {
        System.out.println(service.getAll().toString());
    }

    public void update() {
        scanner.nextLine();

        System.out.println("Insira o id do processo a ser buscado: ");
        String id = scanner.nextLine();

        System.out.println("Digite o novo titulo do processo: ");
        String title = scanner.nextLine();

        System.out.println("Digite a nova descricao do processo: ");
        String description = scanner.nextLine();

        Process process = new Process(title, description);

        try {
            service.update(id, process);

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
