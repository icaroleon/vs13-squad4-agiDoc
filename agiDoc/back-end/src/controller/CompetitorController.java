package controller;

import data.Data;
import entities.competitor.Competitor;
import service.CompetitorService;
import entities.process.Process;

import java.util.ArrayList;
import java.util.Scanner;

public class CompetitorController {
    static String id;
    static String cnpj;
    static String address;
    static String contact;
    static String companyName;
    private static final Scanner scanner = new Scanner(System.in);
    private static CompetitorService competitorService;

    public CompetitorController(ArrayList<Competitor> competitors) { competitorService = new CompetitorService(competitors); }

    public static  void createCompetitor(Process process) {
        System.out.println("Digite o CNPJ da empresa: ");
        cnpj = scanner.nextLine();

        System.out.println("Digite o endereço da empresa: ");
        address = scanner.nextLine();

        System.out.println("Digite o contato da empresa: ");
        contact = scanner.nextLine();

        System.out.println("Digite o nome da empresa: ");
        companyName = scanner.nextLine();


        Competitor newCompetitor = new Competitor(cnpj, address, contact, companyName);

        competitorService.create(newCompetitor);
        process.setCompetitors(competitorService.getAll());

        System.out.println(newCompetitor.toString());
    }

    public static void getAll(){
        for (Competitor newCompetitor : competitorService.getAll())
            System.out.println(newCompetitor.toString());
    }

    public static void update(Process process) {

        System.out.println("Digite o id da empresa que quer fazer as alterações: ");
        id = scanner.nextLine();

        try {
            Competitor existCompetitor = competitorService.get(id);

            System.out.println("Digite o novo CNPJ da empresa(deixe em branco para manter o valor atual): ");
            cnpj = scanner.nextLine();
            cnpj = (cnpj.isEmpty()) ? existCompetitor.getCnpj() : cnpj;

            System.out.println("Digite o novo endereço da empresa(deixe em branco para manter o valor atual): ");
            address = scanner.nextLine();
            address = (address.isEmpty()) ? existCompetitor.getAddress() : address;

            System.out.println("Digite o novo contato da empresa(deixe em branco para manter o valor atual): ");
            contact = scanner.nextLine();
            contact = (contact.isEmpty()) ? existCompetitor.getContact() : contact;

            System.out.println("Digite o novo nome da empresa(deixe em branco para manter o valor atual): ");
            companyName = scanner.nextLine();
            companyName = (companyName.isEmpty()) ? existCompetitor.getCompanyName() : companyName;

            Competitor newCompetitor = new Competitor(cnpj,address, contact, companyName);

            competitorService.update(id, newCompetitor);
            process.setCompetitors(competitorService.getAll());

        }catch (Exception e){
            System.err.println("Erro: " + e.getMessage());
        }

    }

    public static void delete(Process process){
        System.out.println("Digite o id da empresa que quer deletar: ");
        id = scanner.nextLine();

        competitorService.delete(id);
        process.setCompetitors(competitorService.getAll());

    }
}
