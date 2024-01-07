package controller;

import entities.document.Document;
import entities.document.DocumentType;
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

    private static final DocumentService documentService = new DocumentService();

    public String createDocument() {
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
        System.out.println("1 - Edital");
        System.out.println("2 - Portaria");
        System.out.println("3 - Certidao");
        System.out.println("4 - Termo de Referência");
        System.out.println("5 - Delegação de Competência");
        System.out.println("6 - Manifestação");
        System.out.println("7 - Anexos");
        System.out.println("8 - Justificativa de Necessidade");
        System.out.println("9 - Parecer Jurídico");
        int typeInput = sc.nextInt();
        sc.nextLine();

        try {
            type = DocumentType.values()[typeInput - 1];
        } catch (IllegalArgumentException e) {
            return "Error: Tipo de documento inválido.";
        }

        Document document = new Document(protocol, localDate, origin, originId, type, content);

        try {
            Document createdDocument = documentService.create(document);
            return "Document created successfully with protocol: " + createdDocument.getProtocol();
        } catch (RuntimeException e) {
            return "Error: " + e.getMessage();
        }
    }


    public String getDocument() {
        System.out.print("Digite o protocolo do documento que deseja pesquisar: ");
        protocol = sc.nextLine();

        try {
            Document document = documentService.get(protocol);
            return "Documento encontrado com o protocolo " + protocol + ":\n" +
                    "Protocolo: " + document.getProtocol() + "\n" +
                    "Data de expiração: " + document.getExpirationDate() + "\n" +
                    "Origem: " + document.getOrigin() + "\n" +
                    "Índice da Origem: " + document.getOriginId() + "\n" +
                    "Assinatura: " + document.getSigned() + "\n" +
                    "Tipo do documento: " + document.getTypeName() + "\n" +
                    "Conteúdo: " + document.getContent();
        } catch (RuntimeException e) {
            return "Error: " + e.getMessage();
        }
    }

    public String getAllDocuments() {
        ArrayList<Document> allDocuments = documentService.getAll();

        if (allDocuments.isEmpty()) {
            return "Nenhum documento encontrado.";
        }

        StringBuilder result = new StringBuilder("Lista de documentos:\n");

        for (Document document : allDocuments) {
            result.append("Documento encontrado com o protocolo ").append(document.getProtocol()).append(":\n")
                    .append("Protocolo: ").append(document.getProtocol()).append("\n")
                    .append("Data de expiração: ").append(document.getExpirationDate()).append("\n")
                    .append("Origem: ").append(document.getOrigin()).append("\n")
                    .append("Índice da Origem: ").append(document.getOriginId()).append("\n")
                    .append("Assinatura: ").append(document.getSigned()).append("\n")
                    .append("Tipo do documento: ").append(document.getTypeName()).append("\n")
                    .append("Conteúdo: ").append(document.getContent()).append("\n\n");
        }

        return result.toString();
    }

    public String updateDocument() {
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

        System.out.print("Digite o novo tipo do documento (deixe em branco para manter o valor atual): ");
        System.out.println("1 - Edital");
        System.out.println("2 - Portaria");
        System.out.println("3 - Certidao");
        System.out.println("4 - Termo de Referência");
        System.out.println("5 - Delegação de Competência");
        System.out.println("6 - Manifestação");
        System.out.println("7 - Anexos");
        System.out.println("8 - Justificativa de Necessidade");
        System.out.println("9 - Parecer Jurídico");
        int typeInput = sc.nextInt();
        sc.nextLine();

        try {
            type = DocumentType.values()[typeInput - 1];
        } catch (IllegalArgumentException e) {
            return "Error: Tipo de documento inválido.";
        }

        System.out.print("Digite o novo conteúdo do documento (deixe em branco para manter o valor atual): ");
        String newContent = sc.nextLine();
        newContent = (newContent.isEmpty()) ? existingDocument.getContent() : newContent;

        Document newDocument = new Document(protocol, newExpirationDate, newOrigin, newOriginId, type, newContent);

        try {
            Document updatedDocument = documentService.update(protocol, newDocument);
            return "Documento atualizado com sucesso:\n" +
                    "Protocolo: " + updatedDocument.getProtocol() + "\n" +
                    "Data de expiração: " + updatedDocument.getExpirationDate() + "\n" +
                    "Origem: " + updatedDocument.getOrigin() + "\n" +
                    "Índice da origem: " + updatedDocument.getOriginId() + "\n" +
                    "Tipo do documento " + updatedDocument.getTypeName() + "\n" +
                    "Assinatura (true para assinado, false para não assinado): " + updatedDocument.getSigned() + "\n" +
                    "Conteúdo: " + updatedDocument.getContent();
        } catch (RuntimeException e) {
            return "Erro: " + e.getMessage();
        }
    }

    public String deleteDocument() {
        System.out.print("Digite o protocolo do documento que deseja excluir: ");
        protocol = sc.nextLine();

        try {
            documentService.delete(protocol);
            return "Documento com o protocolo " + protocol + " foi excluído com sucesso";
        } catch (RuntimeException e) {
            return "Error: " + e.getMessage();
        }
    }

    public String signDocument() {
        System.out.print("Digite o protocolo do documento que você deseja assinar: ");
        protocol = sc.nextLine();

        try {
            signed = documentService.signDocument(protocol);

            if (signed) {
               return "Documento com o protocolo: " + protocol + " assinado com sucesso";
            } else {
               return "Erro ao assinar o documento com o protocolo: " + protocol;
            }

        } catch (RuntimeException e) {
           return "Error: " + e.getMessage();
        }
    }
}
