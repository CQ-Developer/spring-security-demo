package org.huhu.spring.security.demo.conf.security.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义个 {@link Filter} 的实现,
 * 在成功认证之后记录 {@code Request-Id} 信息.
 *
 * 通过 {@link OncePerRequestFilter} 保证该过滤器每个请求只会执行一次
 *
 * @see OncePerRequestFilter
 */
public class CustomizedAuthenticationLoggingFilter extends OncePerRequestFilter {

    private final Logger logger = LoggerFactory.getLogger(CustomizedAuthenticationLoggingFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestId = request.getHeader("Request-Id");
        logger.info("Successfully authenticated request with id {}", requestId);
        filterChain.doFilter(request, response);
    }

}
