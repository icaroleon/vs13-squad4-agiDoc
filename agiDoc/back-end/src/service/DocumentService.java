package service;

import entities.document.Document;

import java.time.LocalDate;
import java.util.ArrayList;

public class DocumentService {
    private final ArrayList<Document> documents = new ArrayList<>();

    public DocumentService() {}

    public Document create(Document document) {
        documents.add(document);
        return document;
    }

    public Document get(String protocol) {
        for (Document document : documents) {
            if (document.getProtocol().equals(protocol)) {
                return document;
            }
        }
        throw new RuntimeException("Document with protocol " + protocol + " not found");
    }

    public ArrayList<Document> getAll() {
        return new ArrayList<>(documents);
    }

    public Document update(String protocol, Document newDocument) {
        Document document = this.get(protocol);

        if (document != null) {
            document.setOrigin(newDocument.getOrigin());
            document.setSigned(newDocument.getSigned());
            document.setOriginId(newDocument.getOriginId());
            document.setExpirationDate(newDocument.getExpirationDate());
            document.setContent(newDocument.getContent());
            return document;
        }

        throw new RuntimeException("Document with protocol " + protocol + " not found");
    }

    public void delete(String protocol) {
        Document document = this.get(protocol);

        if (document != null) {
            documents.remove(document);
            System.out.println("Documento deletado com sucesso!");
        }
        throw new RuntimeException("Document with protocol " + protocol + " not found");
    }

    public ArrayList<Document> getDocuments() {
        return new ArrayList<>(documents);
    }
}
