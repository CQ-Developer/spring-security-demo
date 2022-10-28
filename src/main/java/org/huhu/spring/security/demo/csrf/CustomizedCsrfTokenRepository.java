package org.huhu.spring.security.demo.csrf;

import org.huhu.spring.security.demo.entity.Token;
import org.huhu.spring.security.demo.repository.TokenJpaRepository;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.DefaultCsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.UUID;

/**
 * 自定义实现 {@link CsrfTokenRepository}, 管理 {@link CsrfToken}.
 *
 * @see HttpSessionCsrfTokenRepository
 */
public class CustomizedCsrfTokenRepository implements CsrfTokenRepository {

    private final TokenJpaRepository tokenJpaRepository;

    public CustomizedCsrfTokenRepository(TokenJpaRepository tokenJpaRepository) {
        this.tokenJpaRepository = tokenJpaRepository;
    }

    @Override
    public CsrfToken generateToken(HttpServletRequest request) {
        String uuidToken = UUID.randomUUID().toString();
        return new DefaultCsrfToken("X-CSRF-TOKEN", "_csrf", uuidToken);
    }

    @Override
    public void saveToken(CsrfToken csrfToken, HttpServletRequest request, HttpServletResponse response) {
        String identifier = request.getHeader("X-IDENTIFIER");
        Optional<Token> optionalToken = tokenJpaRepository.findTokenByIdentifier(identifier);

        // 如果ID存在则使用新生的token更新数据库
        if (optionalToken.isPresent()) {
            Token token = optionalToken.get();
            token.setToken(csrfToken.getToken());
        }
        // 如果ID不存在则保存新生成的token
        else {
            Token token = new Token();
            token.setIdentifier(identifier);
            token.setToken(csrfToken.getToken());
            tokenJpaRepository.save(token);
        }
    }

    @Override
    public CsrfToken loadToken(HttpServletRequest request) {
        String identifier = request.getHeader("X-IDENTIFIER");
        Optional<Token> optionalToken = tokenJpaRepository.findTokenByIdentifier(identifier);

        if (optionalToken.isPresent()) {
            Token token = optionalToken.get();
            return new DefaultCsrfToken("X-CSRF-TOKEN", "_csrf", token.getToken());
        }

        return null;
    }

}
