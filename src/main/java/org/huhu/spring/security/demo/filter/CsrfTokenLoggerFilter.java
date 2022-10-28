package org.huhu.spring.security.demo.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.csrf.CsrfToken;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * 自定义 {@link javax.servlet.Filter},
 * 打印 {@link org.springframework.security.web.csrf.CsrfToken}.
 *
 * @see org.springframework.security.web.csrf.CsrfFilter
 * @see org.springframework.security.web.csrf.CsrfToken
 */
public class CsrfTokenLoggerFilter implements Filter {

    private final Logger logger = LoggerFactory.getLogger(CsrfTokenLoggerFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Object csrf = request.getAttribute("_csrf");
        CsrfToken csrfToken = (CsrfToken) csrf;
        logger.info("CSRF token {}", csrfToken.getToken());
        chain.doFilter(request, response);
    }

}
