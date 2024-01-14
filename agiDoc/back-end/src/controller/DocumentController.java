package controller;

import model.document.Document;
import model.document.DocumentType;
import model.process.Process;
import service.DocumentService;
import service.ProcessService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
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

    public void createDocument(int processId) {
        System.out.print("Digite o protocolo do documento: ");
        protocol = sc.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        boolean isValidDate = false;
        LocalDate localDate = null;
        LocalDate today = LocalDate.now();

        do {
            try {
                System.out.print("Digite a data de expiração do documento (formato(dd/mm/aaaa)): ");
                expirationDate = sc.nextLine();

                localDate = LocalDate.parse(expirationDate, formatter);

                if (localDate.isAfter(today)) {
                    isValidDate = true;
                } else {
                    System.out.println("A data precisa ser no futuro!");
                }
            } catch (DateTimeParseException e) {
                System.out.println("A data está em um formato inválido");
            }
        } while (!isValidDate);

        System.out.print("Digite o conteúdo do documento: ");
        content = sc.nextLine();

        boolean isValidOption = false;
        String typeInput;
        int intTypeInput;

        do {
            System.out.println("""
                    1 - Edital
                    2 - Portaria
                    3 - Certidão
                    4 - Termo de Referência
                    5 - Delegação de Competência
                    6 - Manifestação
                    7 - Anexos
                    8 - Justificativa de Necessidade
                    9 - Parecer Jurídico
                    """);
            System.out.println("Digite o tipo do documento: ");
            typeInput = sc.nextLine();

            try {
                intTypeInput = Integer.parseInt(typeInput);

                type = DocumentType.values()[intTypeInput - 1];

                if (type != null) {
                    isValidOption = true;
                }
            } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
                System.out.println("Opção inválida!");
            }
        } while (!isValidOption);

        Document document = new Document(protocol, localDate, content);

        try {
            Process process = processService.get(processId);

            Document createdDocument = documentService.create(document);
            process.setDocuments(documentService.list());

            System.out.println("Documento criado com o protocolo: " + createdDocument.getProtocol() + "\n");
            System.out.println(createdDocument);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void listAll(int processId) {
        try {
            ArrayList<Document> allDocuments = documentService.list();

            if (allDocuments.isEmpty()) {
                System.out.println("Nenhum documento encontrado.");
                return;
            }

            for (Document document : allDocuments) {
                System.out.println(document.toString());
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    public void update() {
        /*
         * Document existingDocument = null;
         * 
         * try {
         * System.out.print("Digite o id do documento que você deseja atualizar: ");
         * id = sc.nextInt();
         * 
         * existingDocument = documentService.get(id);
         * } catch (RuntimeException e) {
         * System.out.println("Error: " + e.getMessage());
         * return;
         * }
         * 
         * DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
         * boolean isValidDate = false;
         * LocalDate newExpirationDate = null;
         * LocalDate today = LocalDate.now();
         * 
         * do {
         * try {
         * System.out.print(
         * "Digite a nova data de expiração do documento (formato(dd/mm/aaaa), deixe em branco para manter o valor atual): "
         * );
         * String expirationDateInput = sc.nextLine();
         * 
         * LocalDate localDate = LocalDate.parse(expirationDateInput, formatter);
         * 
         * newExpirationDate = expirationDateInput.isEmpty()
         * ? existingDocument.getExpirationDate()
         * : localDate;
         * 
         * if (newExpirationDate.isAfter(today)) {
         * isValidDate = true;
         * } else {
         * System.out.println("A data precisa ser no futuro!");
         * }
         * } catch (DateTimeParseException e) {
         * System.out.println("A data está em um formato inválido");
         * }
         * } while (!isValidDate);
         * 
         * boolean isValidOption = false;
         * String typeInput;
         * int intTypeInput;
         * 
         * do {
         * System.out.println("""
         * 0 - Manter o tipo atual
         * 1 - Edital
         * 2 - Portaria
         * 3 - Certidão
         * 4 - Termo de Referência
         * 5 - Delegação de Competência
         * 6 - Manifestação
         * 7 - Anexos
         * 8 - Justificativa de Necessidade
         * 9 - Parecer Jurídico
         * """);
         * System.out.
         * print("Digite o novo tipo do documento (digite 0 para manter o valor atual): "
         * );
         * typeInput = sc.nextLine();
         * 
         * try {
         * intTypeInput = Integer.parseInt(typeInput);
         * 
         * type = intTypeInput == 0
         * ? type = existingDocument.getType()
         * : DocumentType.values()[intTypeInput - 1];
         * 
         * if (type != null) {
         * isValidOption = true;
         * }
         * } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
         * System.out.println("Opção inválida!");
         * }
         * } while (!isValidOption);
         * 
         * System.out.
         * print("Digite o novo conteúdo do documento (deixe em branco para manter o valor atual): "
         * );
         * String newContent = sc.nextLine();
         * newContent = (newContent.isEmpty()) ? existingDocument.getContent() :
         * newContent;
         * 
         * Document newDocument = new Document(protocol, newExpirationDate, origin,
         * processId, type, newContent);
         * 
         * try {
         * documentService.update(1, newDocument);
         * 
         * process.setDocuments(documentService.list());
         * 
         * System.out.println("Documento com protocolo " + protocol +
         * " atualizado com sucesso.");
         * } catch (RuntimeException e) {
         * System.out.println("Erro: " + e.getMessage());
         * } catch (DatabaseException e) {
         * // Handle exception
         * }
         */}

    public void delete() {
        System.out.println("Tem certeza que deseja excluir um documento? (S/N)");
        boolean isNotSure = !sc.nextLine().equalsIgnoreCase("S");

        if (isNotSure)
            return;

        System.out.print("Digite o protocolo do documento que deseja excluir: ");
        protocol = sc.nextLine();

        try {
            System.out.println("Digite o id do processo que o documento pertence");
            Process process = processService.get(sc.nextInt());

            documentService.delete(1);
            process.setDocuments(documentService.list());
            System.out.println("Documento com o protocolo " + protocol + " foi excluído com sucesso");
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (DatabaseException e) {
            // Handle exception
        } catch (Exception e) {
            // Handle exception
        }
    }
}
