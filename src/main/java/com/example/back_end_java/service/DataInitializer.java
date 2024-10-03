package com.example.back_end_java.service;
import com.example.back_end_java.model.Document;
import com.example.back_end_java.model.DocumentType;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class DataInitializer {

    private final DocumentService documentService;

    public DataInitializer(DocumentService documentService) {
        this.documentService = documentService;
    }

//    @PostConstruct
//    public void init() {
//        Document document = new Document();
//        document.setTitle("Заява на відпустку");
//        document.setType(DocumentType.VACATION_REQUEST);
//        document.setBody("Прошу надати мені відпустку.");
//        document.setCreationDate(LocalDate.now());
//        document.setSignedDate(LocalDate.now().plusDays(5));
//        document.setUserLogin("ivanov");
//
//        documentService.createDocument(document);
//    }

}
