package org.huhu.spring.security.demo.conf.security.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;

public class CustomizedStaticKeyAuthenticationFilter implements Filter {

    private static final String AUTHORIZATION_KEY = "abcd1234";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String authorization = httpRequest.getHeader("Authorization");
        if (!AUTHORIZATION_KEY.equals(authorization)) {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.setStatus(SC_UNAUTHORIZED);
            return;
        }
        chain.doFilter(request, response);
    }

}
