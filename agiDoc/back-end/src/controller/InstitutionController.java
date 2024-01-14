package controller;

import exception.DatabaseException;
import model.institution.Institution;
import service.InstitutionService;

import java.util.Scanner;

public class InstitutionController {

    private final Scanner scanner = new Scanner(System.in);
    private static InstitutionService institutionService = new InstitutionService();

    public void addInstitution() {
        try {
            Institution institution = new Institution();

            System.out.print("Digite o CNPJ da empresa: ");
            institution.setCnpj(scanner.nextLine());

            System.out.print("Digite o nome da empresa: ");
            institution.setCompanyName(scanner.nextLine());

            institutionService.create(institution);
            System.out.println("Institution added successfully! ");
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }
}
