package controller;

import entities.document.Document;
import entities.document.DocumentType;
import entities.process.Process;
import service.DocumentService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class DocumentController {
    static String protocol;
    static  String expirationDate;
    static  String origin = "Processo";
    static boolean signed;
    static DocumentType type;
    static String content;
    private static final Scanner sc = new Scanner(System.in);
    private static DocumentService documentService;

    public DocumentController(ArrayList<Document> documents) {
        documentService = new DocumentService(documents);
    }

    public void createDocument(Process process, String processId) {
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

        Document document = new Document(protocol, localDate, origin, processId, type, content);

        try {
            Document createdDocument = documentService.create(document);
            process.setDocuments(documentService.getAll());

            System.out.println("Documento criado com o protocolo: " + createdDocument.getProtocol() + "\n");
            System.out.println(createdDocument);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }


    public void getDocument() {
        System.out.print("Digite o protocolo do documento que deseja pesquisar: ");
        protocol = sc.nextLine();

        try {
            Document document = documentService.get(protocol);
            System.out.println(document.toString());
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void getAllDocuments() {
        ArrayList<Document> allDocuments = documentService.getAll();

        if (allDocuments.isEmpty()) {
            System.out.println("Nenhum documento encontrado.");
            return;
        }

        for (Document document : allDocuments) {
            System.out.println(document.toString());
        }
    }

    public void updateDocument(Process process, String processId) {
        Document existingDocument = null;

        try {
            System.out.print("Digite o protocolo do documento que você deseja atualizar: ");
            protocol = sc.nextLine();

            existingDocument = documentService.get(protocol);
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
            return;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        boolean isValidDate = false;
        LocalDate newExpirationDate = null;
        LocalDate today = LocalDate.now();

        do {
            try {
                System.out.print("Digite a nova data de expiração do documento (formato(dd/mm/aaaa), deixe em branco para manter o valor atual): ");
                String expirationDateInput = sc.nextLine();

                LocalDate localDate = LocalDate.parse(expirationDateInput, formatter);

                newExpirationDate = expirationDateInput.isEmpty()
                        ? existingDocument.getExpirationDate()
                        : localDate;

                if (newExpirationDate.isAfter(today)) {
                    isValidDate = true;
                } else {
                    System.out.println("A data precisa ser no futuro!");
                }
            } catch (DateTimeParseException e) {
                System.out.println("A data está em um formato inválido");
            }
        } while (!isValidDate);

        boolean isValidOption = false;
        String typeInput;
        int intTypeInput;

        do {
            System.out.println("""
                0 - Manter o tipo atual
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
            System.out.print("Digite o novo tipo do documento (digite 0 para manter o valor atual): ");
            typeInput = sc.nextLine();

            try {
                intTypeInput = Integer.parseInt(typeInput);

                type = intTypeInput == 0
                        ? type = existingDocument.getType()
                        : DocumentType.values()[intTypeInput - 1];

                if (type != null) {
                    isValidOption = true;
                }
            } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
                System.out.println("Opção inválida!");
            }
        } while (!isValidOption);

        System.out.print("Digite o novo conteúdo do documento (deixe em branco para manter o valor atual): ");
        String newContent = sc.nextLine();
        newContent = (newContent.isEmpty()) ? existingDocument.getContent() : newContent;

        Document newDocument = new Document(protocol, newExpirationDate, origin, processId, type, newContent);

        try {
            documentService.update(protocol, newDocument);
            process.setDocuments(documentService.getAll());

            System.out.println("Documento com protocolo " + protocol + " atualizado com sucesso.");
        } catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void deleteDocument(Process process) {
        System.out.println("Tem certeza que deseja excluir um documento? (S/N)");
        boolean isNotSure = !sc.nextLine().equalsIgnoreCase("S");

        if (isNotSure) return;

        System.out.print("Digite o protocolo do documento que deseja excluir: ");
        protocol = sc.nextLine();

        try {
            documentService.delete(protocol);
            process.setDocuments(documentService.getAll());
            System.out.println("Documento com o protocolo " + protocol + " foi excluído com sucesso");
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void signDocument(Process process) {
        System.out.print("Digite o protocolo do documento que você deseja assinar: ");
        protocol = sc.nextLine();

        try {
            signed = documentService.signDocument(protocol);

            if (signed) {
                process.setDocuments(documentService.getAll());
                System.out.println("Documento com o protocolo: " + protocol + " assinado com sucesso");
            } else {
                System.out.println("Erro ao assinar o documento com o protocolo: " + protocol);
            }

        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
