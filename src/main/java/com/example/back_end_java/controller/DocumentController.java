package com.example.back_end_java.controller;

import com.example.back_end_java.model.Document;
import com.example.back_end_java.service.DocumentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping
    public ResponseEntity<Document> createDocument(@RequestBody Document document) {
        Document existingDocument = documentService.findByTitle(document.getTitle());
        if (existingDocument != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(existingDocument);
        }
        Document createdDocument = documentService.createDocument(document);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDocument); // Статус 201 (Created)
    }

    @PutMapping("/{id}")
    public ResponseEntity<Document> updateDocument(@PathVariable Long id, @RequestBody Document updatedDocument) {
        return ResponseEntity.ok(documentService.updateDocument(id, updatedDocument));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocument(@PathVariable Long id) {
        documentService.deleteDocument(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userLogin}")
    public ResponseEntity<List<Document>> getDocumentsByUser(@PathVariable String userLogin) {
        return ResponseEntity.ok(documentService.getDocumentsByUser(userLogin));
    }

    @GetMapping("/signed/user/{userLogin}")
    public ResponseEntity<List<Document>> getSignedDocumentsByUser(@PathVariable String userLogin) {
        return ResponseEntity.ok(documentService.getSignedDocumentsByUser(userLogin));
    }

    @GetMapping("/unsigned/user/{userLogin}")
    public ResponseEntity<List<Document>> getUnsignedDocumentsByUser(@PathVariable String userLogin) {
        return ResponseEntity.ok(documentService.getUnsignedDocumentsByUser(userLogin));
    }

    @GetMapping("/dates")
    public ResponseEntity<List<Document>> getDocumentsBetweenDates(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        return ResponseEntity.ok(documentService.getDocumentsBetweenDates(startDate, endDate));
    }
}
