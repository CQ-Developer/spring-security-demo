package org.huhu.spring.security.demo.authentication.entry.point;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.ExceptionTranslationFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.AccessDeniedException;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

/**
 * 通过 {@link AuthenticationEntryPoint} 自定义失败的响应.
 *
 * 在SpringSecurity中, {@link AuthenticationEntryPoint} 直接由 {@link ExceptionTranslationFilter} 使用.
 * {@link ExceptionTranslationFilter} 会处理过滤链中抛出的 {@link AccessDeniedException} 和 {@link AuthenticationException}.
 */
public class CustomizedAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.addHeader("message", "this is an customized http header from AuthenticationEntryPoint!");
        response.sendError(UNAUTHORIZED.value());
    }

}
