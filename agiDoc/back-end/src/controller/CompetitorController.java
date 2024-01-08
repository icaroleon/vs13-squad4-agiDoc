package controller;

import entities.competitor.Competitor;
import service.CompetitorService;
import entities.process.Process;

import java.util.ArrayList;
import java.util.Scanner;

public class CompetitorController {
    private static String id;
    private static String cnpj;
    private static String address;
    private static String contact;
    private static String companyName;
    private final Scanner scanner = new Scanner(System.in);
    private final CompetitorService competitorService;

    public CompetitorController(ArrayList<Competitor> competitors) { competitorService = new CompetitorService(competitors); }

    public  void createCompetitor(Process process) {
        System.out.print("Digite o CNPJ da empresa: ");
        cnpj = scanner.nextLine();

        System.out.print("Digite o endereço da empresa: ");
        address = scanner.nextLine();

        System.out.print("Digite o contato da empresa: ");
        contact = scanner.nextLine();

        System.out.print("Digite o nome da empresa: ");
        companyName = scanner.nextLine();


        Competitor newCompetitor = new Competitor(cnpj, address, contact, companyName);

        competitorService.create(newCompetitor);
        process.setCompetitors(competitorService.getAll());

        System.out.println(newCompetitor);
    }

    public void getAll(){
        for (Competitor newCompetitor : competitorService.getAll())
            System.out.println(newCompetitor.toString());
    }

    public void update(Process process) {

        System.out.print("Digite o id da empresa que quer fazer as alterações: ");
        id = scanner.nextLine();

        try {
            Competitor existCompetitor = competitorService.get(id);

            System.out.print("Digite o novo CNPJ da empresa(deixe em branco para manter o valor atual): ");
            cnpj = scanner.nextLine();
            cnpj = (cnpj.isEmpty()) ? existCompetitor.getCnpj() : cnpj;

            System.out.print("Digite o novo endereço da empresa(deixe em branco para manter o valor atual): ");
            address = scanner.nextLine();
            address = (address.isEmpty()) ? existCompetitor.getAddress() : address;

            System.out.print("Digite o novo contato da empresa(deixe em branco para manter o valor atual): ");
            contact = scanner.nextLine();
            contact = (contact.isEmpty()) ? existCompetitor.getContact() : contact;

            System.out.print("Digite o novo nome da empresa(deixe em branco para manter o valor atual): ");
            companyName = scanner.nextLine();
            companyName = (companyName.isEmpty()) ? existCompetitor.getCompanyName() : companyName;

            Competitor newCompetitor = new Competitor(cnpj,address, contact, companyName);

            competitorService.update(id, newCompetitor);
            process.setCompetitors(competitorService.getAll());

        } catch (Exception e){
            System.err.println("Erro: " + e.getMessage());
        }

    }

    public void delete(Process process) {
        System.out.println("Tem certeza que deseja excluir um concorrente? (S/N)");
        boolean isNotSure = !scanner.nextLine().equalsIgnoreCase("S");

        if (isNotSure) return;

        try {
            System.out.print("Digite o id da empresa que quer deletar: ");
            id = scanner.nextLine();

            competitorService.delete(id);
            process.setCompetitors(competitorService.getAll());
        } catch (Exception e) {
            System.out.println("Competidor não encontrado!");
        }

    }
}
