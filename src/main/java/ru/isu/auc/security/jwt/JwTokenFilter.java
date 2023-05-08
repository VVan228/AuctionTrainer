package ru.isu.auc.security.jwt;

import jakarta.servlet.GenericFilter;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import jakarta.servlet.*;
import java.io.IOException;

@Component
public class JwTokenFilter extends GenericFilter {
    JwTokenProvider jwTokenProvider;

    @Autowired
    public JwTokenFilter(JwTokenProvider jwTokenProvider) {
        this.jwTokenProvider = jwTokenProvider;
    }

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain)
    throws IOException, ServletException {
        String token = jwTokenProvider.resolveToken((HttpServletRequest) servletRequest);
        try {
            if (token != null && jwTokenProvider.validateToken(token)) {
                Authentication authentication = jwTokenProvider.getAuthentication(token);
                if (authentication != null) {
                    System.out.println("authenticated");
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch (JwtAuthException e) {
            SecurityContextHolder.clearContext();
            //((HttpServletResponse) servletResponse).sendError(e.getHttpStatus().value());
            //throw new JwtAuthException("JWT token is expired or invalid");
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
