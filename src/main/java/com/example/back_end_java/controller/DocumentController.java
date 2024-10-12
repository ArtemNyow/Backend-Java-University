package com.example.back_end_java.controller;

import com.example.back_end_java.model.Document;
import com.example.back_end_java.service.DocumentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/documents")
@Tag(name = "Document API", description = "API для управління документами")
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping
    @Operation(summary = "Створити новий документ", description = "Додає новий документ в базу даних")
    public ResponseEntity<Document> createDocument(
            @RequestBody @Parameter(description = "Дані для створення нового документа") Document document) {
        return ResponseEntity.ok(documentService.createDocument(document));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Оновити документ", description = "Оновлює існуючий документ за його ID")
    public ResponseEntity<Document> updateDocument(
            @PathVariable @Parameter(description = "ID документа") Long id,
            @RequestBody @Parameter(description = "Оновлені дані документа") Document updatedDocument) {
        return ResponseEntity.ok(documentService.updateDocument(id, updatedDocument));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Видалити документ", description = "Видаляє документ за його ID")
    public ResponseEntity<Void> deleteDocument(
            @PathVariable @Parameter(description = "ID документа") Long id) {
        documentService.deleteDocument(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userLogin}")
    @Operation(summary = "Отримати документи користувача", description = "Отримує всі документи користувача за логіном")
    public ResponseEntity<List<Document>> getDocumentsByUser(
            @PathVariable @Parameter(description = "Логін користувача") String userLogin) {
        return ResponseEntity.ok(documentService.getDocumentsByUser(userLogin));
    }

    @GetMapping("/signed/user/{userLogin}")
    @Operation(summary = "Отримати підписані документи користувача", description = "Отримує всі підписані документи користувача за логіном")
    public ResponseEntity<List<Document>> getSignedDocumentsByUser(
            @PathVariable @Parameter(description = "Логін користувача") String userLogin) {
        return ResponseEntity.ok(documentService.getSignedDocumentsByUser(userLogin));
    }

    @GetMapping("/unsigned/user/{userLogin}")
    @Operation(summary = "Отримати непідписані документи користувача", description = "Отримує всі непідписані документи користувача за логіном")
    public ResponseEntity<List<Document>> getUnsignedDocumentsByUser(
            @PathVariable @Parameter(description = "Логін користувача") String userLogin) {
        return ResponseEntity.ok(documentService.getUnsignedDocumentsByUser(userLogin));
    }

    @GetMapping("/dates")
    @Operation(summary = "Отримати документи між датами", description = "Отримує документи, створені між двома датами")
    public ResponseEntity<List<Document>> getDocumentsBetweenDates(
            @RequestParam @Parameter(description = "Початкова дата") LocalDate startDate,
            @RequestParam @Parameter(description = "Кінцева дата") LocalDate endDate) {
        return ResponseEntity.ok(documentService.getDocumentsBetweenDates(startDate, endDate));
    }
}
