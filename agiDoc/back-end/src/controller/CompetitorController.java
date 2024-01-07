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

        System.out.println(competitor.getAll().toString());
    }

    public static void getAll(){
        System.out.println(competitor.getAll().toString());
    }

    public static void update() throws Exception {

        System.out.println("Digite o id da empresa que quer fazer as alterações: ");
        id = scanner.nextLine();

        System.out.println("Digite o novo CNPJ da empresa: ");
        cnpj = scanner.nextLine();

        System.out.println("Digite o novo endereço da empresa: ");
        address = scanner.nextLine();

        System.out.println("Digite o novo contato da empresa: ");
        contact = scanner.nextLine();

        System.out.println("Digite o novo nome da empresa: ");
        companyName = scanner.nextLine();

        Competitor newCompetitor = new Competitor(cnpj,address, contact, companyName);

        competitor.update(id, newCompetitor);
    }

    public static void delete(){
        System.out.println("Digite o id da empresa que quer deletar: ");
        id = scanner.nextLine();

        competitor.delete(id);
    }
}
