package org.huhu.spring.security.demo.config;

import org.huhu.spring.security.demo.entity.Document;
import org.huhu.spring.security.demo.repository.DocumentRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * <p>自定义一个 {@link PermissionEvaluator} 的实现.
 *
 * <p>将该类实例作为 {@code Bean} 注册到 {@link SpringApplication} 中,
 * 会被 {@link GlobalMethodSecurityConfiguration} 自动注册到 {@link DefaultMethodSecurityExpressionHandler} 中.
 *
 * @see GlobalMethodSecurityConfiguration#afterSingletonsInstantiated
 */
@Component
public class DocumentPermissionEvaluator implements PermissionEvaluator {

    private final DocumentRepository documentRepository;

    public DocumentPermissionEvaluator(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        return false;
    }

    /**
     * 使用第二个方法编写授权表达式.
     */
    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        String code = (String) targetId;
        Document document = documentRepository.findDocument(code);

        boolean isAdmin = authentication.getAuthorities()
                .stream()
                .anyMatch(a -> a.getAuthority().equals(permission));
        boolean isOwner = document.getOwner().equals(authentication.getName());

        return isAdmin || isOwner;
    }

}
