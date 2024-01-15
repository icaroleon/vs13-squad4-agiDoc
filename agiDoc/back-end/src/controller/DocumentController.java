package controller;

import model.Associated;
import model.competitor.Competitor;
import model.document.Document;
import service.DocumentService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import exception.DatabaseException;

public class DocumentController {
    private final DocumentService documentService = new DocumentService();
    private final Scanner scanner = new Scanner(System.in);

    public Document create(Associated associated, Integer associatedId) {
        try {
            Document document = new Document();

            System.out.print("Digite o protocolo: ");
            document.setProtocol(scanner.nextLine().trim());

            boolean isValidDate = false;
            LocalDate today = LocalDate.now();

            do {
                try {
                    System.out.print("Digite a data de expiração do documento (formato(dd/mm/aaaa)): ");
                    String expirationDateStr = scanner.nextLine();

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate localDate = LocalDate.parse(expirationDateStr, formatter);

                    if (localDate.isAfter(today)) {
                        isValidDate = true;
                        document.setExpirationDate(localDate);
                    } else {
                        System.out.println("A data precisa ser no futuro!");
                    }
                } catch (DateTimeParseException e) {
                    System.out.println("A data está em um formato inválido");
                }
            } while (!isValidDate);

            document.setSigned(false);
            document.setAssociated(associated);
            document.setAssociatedId(associatedId);

            return documentService.create(document);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void list(Associated associated, Integer associatedId) {
        try {
            documentService.list()
                    .stream()
                    .filter(document -> document.getAssociated().equals(associated)
                            && document.getAssociatedId().equals(associatedId))
                    .forEach(System.out::println);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }

    }

    public boolean update() {
        try {
            System.out.print("Digite o id do documento que quer atualizar: ");
            String id = scanner.nextLine();

            int validId = Integer.parseInt(id);
            Document document = documentService.get(validId);

            if (document == null) {
                System.out.println("Id do documento não encontrado!");
                return false;
            }

            System.out.print("Digite o novo protocolo: ");
            document.setProtocol(scanner.nextLine().trim());

            boolean isValidDate = false;
            LocalDate today = LocalDate.now();

            do {
                try {
                    System.out.print("Digite a nova data de expiração do documento (formato(dd/mm/aaaa)): ");
                    String expirationDateStr = scanner.nextLine();

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate localDate = LocalDate.parse(expirationDateStr, formatter);

                    if (localDate.isAfter(today)) {
                        isValidDate = true;
                        document.setExpirationDate(localDate);
                    } else {
                        System.out.println("A nova data precisa ser no futuro!");
                    }
                } catch (DateTimeParseException e) {
                    System.out.println("A nova data está em um formato inválido");
                }
            } while (!isValidDate);

            return documentService.update(validId, document);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete() {
        try {
            System.out.print("Digite o id do documento que quer assinar: ");
            String id = scanner.nextLine();

            int validId = Integer.parseInt(id);
            Document document = documentService.get(validId);

            return documentService.delete(validId);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean signDocument(Integer signatureId) {
        try {
            System.out.print("Digite o id do documento que quer assinar: ");
            String id = scanner.nextLine();

            int validId = Integer.parseInt(id);
            Document document = documentService.get(validId);

            if (document.getSigned()) {
                System.out.println("Esse documento já foi assinado");
                return false;
            }

            System.out.println("Documento assinado com sucesso!");
            return documentService.sign(validId, signatureId);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return false;
    }
}