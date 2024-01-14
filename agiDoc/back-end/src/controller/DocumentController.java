package controller;

import model.Associated;
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

            System.out.print("Digite o conteúdo do documento: ");
            String content = scanner.nextLine();
            document.setFile(content);

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

    public boolean update(Integer id) {
        try {
            Document document = new Document();

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

            System.out.print("Digite o novo conteúdo do documento: ");
            String content = scanner.nextLine();
            document.setFile(content);

            return documentService.update(id, document);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(Integer id) {
        try {
            return documentService.delete(id);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return false;
    }
}
