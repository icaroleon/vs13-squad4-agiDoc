package controller;

import entities.document.Document;
import service.DocumentService;

import java.util.ArrayList;

public class DocumentController {
    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    public String createDocument(Document document) {
        try {
            Document createdDocument = documentService.create(document);
            return "Document created successfully with protocol: " + createdDocument.getProtocol();
        } catch (RuntimeException e) {
            return "Error: " + e.getMessage();
        }
    }

    public String getDocument(String protocol) {
        try {
            Document document = documentService.get(protocol);
            return document.toString();
        } catch (RuntimeException e) {
            return "Error: " + e.getMessage();
        }
    }

    public ArrayList<Document> getAllDocuments() {
        return documentService.getAll();
    }

    public String updateDocument(String protocol, Document newDocument) {
        try {
            Document updatedDocument = documentService.update(protocol, newDocument);
            return "Document updated successfully with protocol: " + updatedDocument.getProtocol();
        } catch (RuntimeException e) {
            return "Error: " + e.getMessage();
        }
    }

    public String deleteDocument(String protocol) {
        try {
            documentService.delete(protocol);
            return "Document with protocol: " + protocol + " deleted successfully";
        } catch (RuntimeException e) {
            return "Error: " + e.getMessage();
        }
    }

    public ArrayList<Document> getAllDocumentsFromService() {
        return documentService.getDocuments();
    }
}
