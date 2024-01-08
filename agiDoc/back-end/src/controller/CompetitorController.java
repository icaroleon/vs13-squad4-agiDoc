package controller;

import entities.competitor.Competitor;
import service.CompetitorService;

import java.util.Scanner;

public abstract class CompetitorController {

    static String id;
    static String cnpj;
    static String address;
    static String contact;
    static String companyName;
    private static final Scanner scanner = new Scanner(System.in);
    private static final CompetitorService competitor = new CompetitorService();
    public static  void createCompetitor() {
        System.out.println("Digite o CNPJ da empresa: ");
        cnpj = scanner.nextLine();

        System.out.println("Digite o endereço da empresa: ");
        address = scanner.nextLine();

        System.out.println("Digite o contato da empresa: ");
        contact = scanner.nextLine();

        System.out.println("Digite o nome da empresa: ");
        companyName = scanner.nextLine();


        Competitor newCompetitor = new Competitor(cnpj, address, contact, companyName);

        competitor.create(newCompetitor);

        System.out.println(newCompetitor.toString());
    }

    public static void getAll(){
        for (Competitor newCompetitor : competitor.getAll())
            System.out.println(newCompetitor.toString());
    }

    public static void update() {

        System.out.println("Digite o id da empresa que quer fazer as alterações: ");
        id = scanner.nextLine();

        try {
            Competitor existCompetitor = competitor.get(id);

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

            competitor.update(id, newCompetitor);
        }catch (Exception e){
            System.err.println("Erro: " + e.getMessage());
        }

    }

    public static void delete(){
        System.out.println("Digite o id da empresa que quer deletar: ");
        id = scanner.nextLine();

        competitor.delete(id);
    }
}
