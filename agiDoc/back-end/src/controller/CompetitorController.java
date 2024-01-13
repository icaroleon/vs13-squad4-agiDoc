package controller;

import exception.DatabaseException;
import model.address.Address;
import model.address.AddressAssociated;
import model.competitor.Competitor;
import service.CompetitorService;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class CompetitorController {
    private final Scanner scanner = new Scanner(System.in);
    private final CompetitorService competitorService = new CompetitorService();
    private final AddressController addressController = new AddressController();
    // TODO: Aguardando service de contact
//    private final ContactController contactController = new ContactController();

    public CompetitorController() {}

    public  void createCompetitor(int processId) {
        Competitor newCompetitor = new Competitor();

        System.out.print("Digite o CNPJ da empresa: ");
        newCompetitor.setCnpj(scanner.nextLine());

        System.out.print("Digite o nome da empresa: ");
        newCompetitor.setCompanyName(scanner.nextLine());

        try {
            newCompetitor = competitorService.create(newCompetitor);
        } catch (DatabaseException e) {
            System.out.println("ERRO:" + e.getMessage());
            return;
        }

        Address createdAddress = addressController.create(AddressAssociated.COMPETITOR, newCompetitor.getId());

        newCompetitor.setAddress(createdAddress);

        // TODO: Aguardando model Contact
//        System.out.print("[Contato] Nome do contato: ");
//        contact.setName(scanner.nextLine());
//
//        System.out.print("[Contato] Email (opcional): ");
//        contact.setEmail(scanner.nextLine());
//
//        System.out.print("[Contato] Telefone: ");
//        contact.setPhone(scanner.nextLine());

        // TODO: Implementar seleção de enum
//        System.out.println("""
//                0 - Fixo
//                1 - Móvel
//                """);
//        System.out.print("[Contato] Tipo de telefone: ");
//        contact.setPhoneType(scanner.nextLine());

        // TODO : Aguardando service de contact
//        try {
//            contactService.create(contact);
//        } catch (DatabaseException e) {
//            System.out.println("ERRO:" + e.getMessage());
//            return;
//        }
        System.out.println(newCompetitor);
    }

    public void listAll(int processId) {
        ArrayList<Competitor> completitorList = new ArrayList<>();

        try {
            completitorList = competitorService.list();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (Competitor competitor : completitorList) {
            System.out.println(competitor.toString());
        }
    }

    public void update() {

        System.out.print("Digite o id da empresa que quer fazer as alterações: ");
        String id = scanner.nextLine();

        try {
            int validId = Integer.parseInt(id);
            Competitor existCompetitor = competitorService.get(validId);

            System.out.print("Digite o novo CNPJ da empresa(deixe em branco para manter o valor atual): ");
            String cnpj = scanner.nextLine();
            existCompetitor.setCnpj((cnpj.isEmpty()) ? existCompetitor.getCnpj() : cnpj);

            System.out.print("Digite o novo nome da empresa(deixe em branco para manter o valor atual): ");
            String companyName = scanner.nextLine();
            existCompetitor.setCompanyName((companyName.isEmpty()) ? existCompetitor.getCompanyName() : companyName);


            boolean isCompetitorUpdated = competitorService.update(validId, existCompetitor);

            if (!isCompetitorUpdated) {
                System.out.println("Erro ao atualizar dados do concorrente de id: " + validId);
                return;
            }

            boolean isAddressUpdated = addressController.update(existCompetitor.getId());

            if (!isAddressUpdated) {
                System.out.println("Erro ao atualizar dados do endereço do concorrente de id: " + validId);
                return;
            }

            // TODO: Aguardando contact
//            boolean isContactUpdated = contactController.update(existCompetitor.getId());

//            if (!isContactUpdated) {
//                System.out.println("Erro ao atualizar dados do contato do concorrente de id: " + validId);
//            }

        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }

    public void delete() {
        System.out.println("Tem certeza que deseja excluir um concorrente? (S/N)");
        boolean isNotSure = !scanner.nextLine().equalsIgnoreCase("S");

        if (isNotSure) return;

        try {
            System.out.print("Digite o id do concorrente que deseja remover: ");
            String id = scanner.nextLine();
            int validId = Integer.parseInt(id);

            competitorService.delete(validId);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }

    }
}
