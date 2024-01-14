package controller;

import exception.DatabaseException;
import model.Associated;
import model.institution.Institution;
import service.InstitutionService;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class InstitutionController {

    private final Scanner scanner = new Scanner(System.in);
    private final InstitutionService institutionService = new InstitutionService();
    private final AddressController addressController = new AddressController();
    private final ContactController contactController = new ContactController();

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

        addressController.create(Associated.INSTITUTION, institution.getId());

        contactController.create(Associated.INSTITUTION, institution.getId());
    }

    public void update() {
        System.out.print("Digite o id da empresa que quer fazer as alterações: ");
        String id = scanner.nextLine();

        try {
            int validId = Integer.parseInt(id);
            Institution existInstitution = institutionService.get(validId);

            System.out.print("Digite o novo CNPJ da Instituição (deixe em branco para manter o valor atual): ");
            String cnpj = scanner.nextLine();
            existInstitution.setCnpj((cnpj.isEmpty()) ? existInstitution.getCnpj() : cnpj);

            System.out.print("Digite o novo nome da Instituição (deixe em branco para manter o valor atual): ");
            String companyName = scanner.nextLine();
            existInstitution.setCompanyName((companyName.isEmpty()) ? existInstitution.getCompanyName() : companyName);


            boolean isInstitutionUpdated = institutionService.update(validId, existInstitution);

            if (!isInstitutionUpdated) {
                System.out.println("Erro ao atualizar dados da instituição de id: " + validId);
                return;
            }
            //Opção para alterar ou não o endereço

            System.out.println("""
                    1 - Deseja alterar o endereço da instituição?
                    2 - Voltar ao menu anterior
                    """);

            String option = scanner.nextLine();
            int validOption = Integer.parseInt(option);
            if (validOption == 1)
            {
                boolean isAddressUpdated = addressController.update(existInstitution.getAddress().getId());

                if (!isAddressUpdated) {
                    System.out.println("Erro ao atualizar dados do endereço da instituição de id: " + validId);
                    return;
                }

                System.out.println("""
                    1 - Deseja alterar o contato da instituição?
                    2 - Voltar ao menu anterior
                    """);
                option = scanner.nextLine();
                validOption = Integer.parseInt(option);
                if (validOption == 1){
                    boolean isContactUpdated = contactController.update(existInstitution.getContact().getId());

                    if (!isContactUpdated) {
                        System.out.println("Erro ao atualizar dados do contato da instituição de id: " + validId);
                    }
                }else if (validOption == 2) {
                    System.out.println("Voltando ao menu anterior...");
                    return;
                }
            } else if (validOption == 2) {
                System.out.println("Voltando ao menu anterior...");
                return;
            }

        } catch (DatabaseException e) {
            System.out.println("ERRO: " + e.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
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

    public void listAll()
    {
        ArrayList<Institution> list = new ArrayList<>();

        try {

            list = institutionService.list();

            for (Institution institution : list) {
                System.out.println(institution);
            }
        } catch (DatabaseException e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }



}
