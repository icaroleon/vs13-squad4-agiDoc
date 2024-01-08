package service;

import entities.document.Document;

import java.time.LocalDate;
import java.util.ArrayList;

public class DocumentService {
    private ArrayList<Document> documents;

    public DocumentService() {}

    public DocumentService(ArrayList<Document> documents) {
        this.documents = documents;
    }

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
        } else {
            throw new RuntimeException("Document with protocol " + protocol + " not found");
        }
    }

    public boolean signDocument(String protocol) {
        Document document = this.get(protocol);

        if (document != null) {
            document.setSigned(true);
            return true;
        } else {
            return false;
        }
    }
}
