package org.huhu.spring.security.demo.filter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.huhu.spring.security.demo.authentication.OtpAuthentication;
import org.huhu.spring.security.demo.authentication.UsernamePasswordAuthentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;

public class InitialAuthenticationFilter extends OncePerRequestFilter {

    private final AuthenticationManager authenticationManager;

    private final String signingKey = "ymLTU8rq83j4fmJZj60wh4OrMNuntIj4fmJ";

    public InitialAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getServletPath().equals("/login");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String username = request.getHeader("username");
        String password = request.getHeader("password");
        String code = request.getHeader("code");
        if (code == null) {
            Authentication authentication = new UsernamePasswordAuthentication(username, password);
            authenticationManager.authenticate(authentication);
        } else {
            Authentication authentication = new OtpAuthentication(username, code);
            authenticationManager.authenticate(authentication);
            SecretKey secretKey = Keys.hmacShaKeyFor(signingKey.getBytes(UTF_8));
            String jwt = Jwts.builder()
                             .setClaims(Map.of("username", username))
                             .signWith(secretKey)
                             .compact();
            response.setHeader("Authorization", jwt);
        }
    }

}
