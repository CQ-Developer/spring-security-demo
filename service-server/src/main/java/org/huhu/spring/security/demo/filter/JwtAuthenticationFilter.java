package org.huhu.spring.security.demo.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.huhu.spring.security.demo.authentication.UsernamePasswordAuthentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final String signingKey = "ymLTU8rq83j4fmJZj60wh4OrMNuntIj4fmJ";

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return request.getServletPath().equals("/login");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = request.getHeader("Authorization");

        SecretKey secretKey = Keys.hmacShaKeyFor(signingKey.getBytes(UTF_8));
        Claims claims = Jwts.parserBuilder()
                            .setSigningKey(secretKey)
                            .build()
                            .parseClaimsJws(jwt)
                            .getBody();
        String username = String.valueOf(claims.get("username"));

        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("user");
        UsernamePasswordAuthentication authentication = new UsernamePasswordAuthentication(
                username, null, List.of(grantedAuthority));
        SecurityContextHolder.getContext()
                             .setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }

}
