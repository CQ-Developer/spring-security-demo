package org.huhu.spring.security.demo.repository;

import org.huhu.spring.security.demo.entity.Document;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class DocumentRepository {

    private final Map<String, Document> documents = Map.of(
            "abc123", new Document("chen"),
            "qwe123", new Document("chen"),
            "asd555", new Document("huhu"));

    public Document findDocument(String code) {
        return documents.get(code);
    }

}
