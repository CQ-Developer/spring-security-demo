package org.huhu.spring.security.demo.conf.security.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 自定义个 {@link Filter} 的实现,
 * 在成功认证之后记录 {@code Request-Id} 信息.
 */
public class CustomizedAuthenticationLoggingFilter implements Filter {

    private final Logger logger = LoggerFactory.getLogger(CustomizedAuthenticationLoggingFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestId = httpRequest.getHeader("Request-Id");
        logger.info("Successfully authenticated request with id {}", requestId);
        chain.doFilter(request, response);
    }

}
