package com.example.back_end_java.service;

import com.example.back_end_java.model.Document;
import com.example.back_end_java.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DocumentService {

    private final DocumentRepository documentRepository;

    @Autowired
    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }
    public Document findByTitle(String title) {

        List<Document> allDocuments = documentRepository.findAll();

        return allDocuments.stream()
                .filter(doc -> doc.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null); //
    }
    public Document createDocument(Document document) {
        return documentRepository.save(document);
    }
    public Document updateDocument(Long id, Document updatedDocument) {
        Optional<Document> existingDocument = documentRepository.findById(id);

        if (existingDocument.isPresent()) {
            Document documentToUpdate = existingDocument.get();
            // Оновлення полів
            documentToUpdate.setTitle(updatedDocument.getTitle());
            documentToUpdate.setType(updatedDocument.getType());
            documentToUpdate.setBody(updatedDocument.getBody());
            documentToUpdate.setCreationDate(updatedDocument.getCreationDate());
            documentToUpdate.setSignedDate(updatedDocument.getSignedDate());
            documentToUpdate.setUserLogin(updatedDocument.getUserLogin());

            return documentRepository.save(documentToUpdate);
        } else {
            throw new RuntimeException("Document not found with id: " + id);
        }
    }
    public void deleteDocument(Long id) {
        Optional<Document> existingDocument = documentRepository.findById(id);
        if (existingDocument.isPresent()) {
            documentRepository.delete(existingDocument.get());
        } else {
            throw new RuntimeException("Document not found with id: " + id);
        }
    }
    public List<Document> getDocumentsByUser(String userLogin) {
        List<Document> allDocuments = documentRepository.findAll();
        return allDocuments.stream()
                .filter(document -> document.getUserLogin().equals(userLogin))
                .toList();
    }

    public List<Document> getSignedDocumentsByUser(String userLogin) {
        List<Document> allDocuments = documentRepository.findAll();
        return allDocuments.stream()
                .filter(document -> document.getUserLogin().equals(userLogin) && document.getSignedDate() != null)
                .toList();
    }
    public List<Document> getUnsignedDocumentsByUser(String userLogin) {
        List<Document> allDocuments = documentRepository.findAll();
        return allDocuments.stream()
                .filter(document -> document.getUserLogin().equals(userLogin) && document.getSignedDate() == null)
                .toList();
    }
    public List<Document> getDocumentsBetweenDates(LocalDate startDate, LocalDate endDate) {
        List<Document> allDocuments = documentRepository.findAll();
        return allDocuments.stream()
                .filter(document -> !document.getCreationDate().isBefore(startDate) && !document.getCreationDate().isAfter(endDate))
                .toList();
    }
}
