package controller;

import exception.DatabaseException;
import model.address.Address;
import model.Associated;
import model.competitor.Competitor;
import model.contact.Contact;
import service.CompetitorService;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class CompetitorController {
    private final Scanner scanner = new Scanner(System.in);
    private final CompetitorService competitorService = new CompetitorService();
    private final AddressController addressController = new AddressController();
    private final ContactController contactController = new ContactController();

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

        Address createdAddress = addressController.create(Associated.COMPETITOR, newCompetitor.getId());
        newCompetitor.setAddress(createdAddress);

        Contact createdContact = contactController.create(Associated.COMPETITOR, newCompetitor.getId());
        newCompetitor.setContact(createdContact);

        try {
            competitorService.addCompetitorToProcess(newCompetitor.getId(), processId);
            System.out.println(newCompetitor);
        } catch (DatabaseException e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }

    public void listAll(int processId) {
        ArrayList<Competitor> competitorList = new ArrayList<>();

        try {
            competitorList = competitorService.list();
        } catch (SQLException e) {
            System.out.println("ERRO: " + e.getMessage());
            e.printStackTrace();
        }

        for (Competitor competitor : competitorList) {
            if (processId == competitor.getProcessId()) System.out.println(competitor);
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

            boolean isAddressUpdated = addressController.update(existCompetitor.getAddress().getId());

            if (!isAddressUpdated) {
                System.out.println("Erro ao atualizar dados do endereço do concorrente de id: " + validId);
                return;
            }

            boolean isContactUpdated = contactController.update(existCompetitor.getContact().getId());

            if (!isContactUpdated) {
                System.out.println("Erro ao atualizar dados do contato do concorrente de id: " + validId);
            }

        } catch (DatabaseException e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }

    public void delete(Integer processId) {
        try {
            System.out.print("Digite o id do concorrente que deseja remover: ");
            String id = scanner.nextLine();
            int validId = Integer.parseInt(id);

            System.out.println("Tem certeza que deseja excluir um concorrente? (S/N)");
            boolean isNotSure = !scanner.nextLine().equalsIgnoreCase("S");

            if (isNotSure) return;

            Competitor competitor = competitorService.get(validId);

            addressController.delete(competitor.getAddress().getId());
            contactController.delete(competitor.getContact().getId());
            competitorService.removeCompetitorToProcess(competitor.getId(), processId);
            competitorService.delete(validId);
        } catch (DatabaseException e) {
            System.out.println("ERRO: " + e.getMessage());
        }

    }
}
