package controller;

import exception.DatabaseException;
import model.address.Address;
import model.address.AddressAssociated;
import model.institution.Institution;
import service.InstitutionService;

import java.util.Scanner;

public class InstitutionController {

    private final Scanner scanner = new Scanner(System.in);
    private final InstitutionService institutionService = new InstitutionService();
    private final AddressController addressController = new AddressController();

    //TODO: importar a classe.
    //private final ContactController contactController = new ContactController();

    public void addInstitution() {
        Institution institution = new Institution();
        try {

            System.out.print("Digite o CNPJ da empresa: ");
            institution.setCnpj(scanner.nextLine());

            System.out.print("Digite o nome da empresa: ");
            institution.setCompanyName(scanner.nextLine());

            institutionService.create(institution);
            System.out.println("Institution added successfully! ");
        } catch (DatabaseException e) {
            e.printStackTrace();
        }

        addressController.create(AddressAssociated.INSTITUTION, institution.getId());


        //TODO: colocar os paramêtros
        //contactController.create();
    }

    public void update() {
        System.out.print("Digite o id da empresa que quer fazer as alterações: ");
        String id = scanner.nextLine();

        try {
            int validId = Integer.parseInt(id);
            //Competitor existCompetitor = competitorService.get(validId);
            Institution institution = institutionService.get(validId);

            System.out.print("Digite o novo CNPJ da Instituição (deixe em branco para manter o valor atual): ");
            String cnpj = scanner.nextLine();
            institution.setCnpj((cnpj.isEmpty()) ? institution.getCnpj() : cnpj);

            System.out.print("Digite o novo nome da Instituição (deixe em branco para manter o valor atual): ");
            String companyName = scanner.nextLine();
            institution.setCompanyName((companyName.isEmpty()) ? institution.getCompanyName() : companyName);


            boolean isCompetitorUpdated = institutionService.update(validId, institution);

            if (!isCompetitorUpdated) {
                System.out.println("Erro ao atualizar dados do concorrente de id: " + validId);
                return;
            }

        } catch (DatabaseException e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }

    public void delete() {
        try {
            System.out.print("Digite o id da instituição que deseja remover: ");
            String id = scanner.nextLine();
            int validId = Integer.parseInt(id);

            System.out.println("Tem certeza que deseja excluir uma instituição? (S/N)");
            boolean isNotSure = !scanner.nextLine().equalsIgnoreCase("S");

            if (isNotSure) return;

            institutionService.delete(validId);
            System.out.println("Instituição excluida com sucesso!");
        } catch (DatabaseException e) {
            System.out.println("ERRO: " + e.getMessage());
        }

    }

}
