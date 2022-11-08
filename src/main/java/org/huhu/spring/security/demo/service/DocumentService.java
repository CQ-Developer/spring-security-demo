package org.huhu.spring.security.demo.service;

import org.huhu.spring.security.demo.entity.Document;
import org.huhu.spring.security.demo.repository.DocumentRepository;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Service;

@Service
public class DocumentService {

    private final DocumentRepository documentRepository;

    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @PostAuthorize("hasPermission(returnObject, 'ROLE_ADMIN')")
    public Document getDocument(String code) {
        return documentRepository.findDocument(code);
    }

}
