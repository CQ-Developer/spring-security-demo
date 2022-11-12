package org.huhu.spring.security.demo.config.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.csrf.CsrfToken;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class CsrfLoggingFilter implements Filter {

    private final Logger logger = LoggerFactory.getLogger(CsrfLoggingFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Object attribute = request.getAttribute("_csrf");
        CsrfToken csrfToken = (CsrfToken) attribute;
        logger.info("csrf token: {}", csrfToken.getToken());
        chain.doFilter(request, response);
    }

}
