package controller;

import entities.document.Document;
import entities.document.DocumentType;
import entities.process.Process;
import service.DocumentService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class DocumentController {
    static String protocol;
    static  String expirationDate;
    static  String origin = "Processo";
    static  String originId;
    static boolean signed;
    static DocumentType type;
    static String content;
    private static final Scanner sc = new Scanner(System.in);
    private static DocumentService documentService;

    public DocumentController(ArrayList<Document> documents) {
        documentService = new DocumentService(documents);
    }

    public void createDocument(Process process) {
        System.out.print("Digite o protocolo do documento: ");
        protocol = sc.nextLine();

        System.out.print("Digite a data de expiração do documento (formato(dd/mm/aaaa)): ");
        expirationDate = sc.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(expirationDate, formatter);

        System.out.print("Digite o índice de identificação da origem: ");
        originId = sc.nextLine();
        System.out.print("Digite o conteúdo do documento: ");
        content = sc.nextLine();

        System.out.println("Digite o tipo do documento: ");
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
        int typeInput = sc.nextInt();
        sc.nextLine();

        try {
            type = DocumentType.values()[typeInput - 1];
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());;
        }

        Document document = new Document(protocol, localDate, origin, originId, type, content);

        try {
            Document createdDocument = documentService.create(document);
            process.setDocuments(documentService.getAll());

            System.out.println("Documento criado com o protocolo: " + createdDocument.getProtocol());
            System.out.println(createdDocument.toString());
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
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

    public void updateDocument(Process process) {
        System.out.print("Digite o protocolo do documento que você deseja atualizar: ");
        protocol = sc.nextLine();

        Document existingDocument = documentService.get(protocol);

        System.out.print("Digite a nova data de expiração do documento (formato(dd/mm/aaaa), deixe em branco para manter o valor atual): ");
        String expirationDateInput = sc.nextLine();
        LocalDate newExpirationDate = expirationDateInput.isEmpty()
                ? existingDocument.getExpirationDate()
                : LocalDate.parse(expirationDateInput, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        System.out.print("Digite a nova origem do documento (deixe em branco para manter o valor atual): ");
        String newOrigin = sc.nextLine();
        newOrigin = (newOrigin.isEmpty()) ? existingDocument.getOrigin() : newOrigin;

        System.out.print("Digite o novo índice de identificação da origem (deixe em branco para manter o valor atual): ");
        String newOriginId = sc.nextLine();
        newOriginId = (newOriginId.isEmpty()) ? existingDocument.getOriginId() : newOriginId;

        System.out.print("Digite o novo tipo do documento (digite 0 para manter o valor atual): ");
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
        int typeInput = sc.nextInt();
        sc.nextLine();

        try {
            if (typeInput == 0) {
                type = existingDocument.getType();
            } else {
                type = DocumentType.values()[typeInput - 1];
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());;
        }

        System.out.print("Digite o novo conteúdo do documento (deixe em branco para manter o valor atual): ");
        String newContent = sc.nextLine();
        newContent = (newContent.isEmpty()) ? existingDocument.getContent() : newContent;

        Document newDocument = new Document(protocol, newExpirationDate, newOrigin, newOriginId, type, newContent);

        try {
            Document updatedDocument = documentService.update(protocol, newDocument);
            process.setDocuments(documentService.getAll());

            System.out.println("Documento com protocolo " + protocol + " atualizado com sucesso.");
        } catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void deleteDocument(Process process) {
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
