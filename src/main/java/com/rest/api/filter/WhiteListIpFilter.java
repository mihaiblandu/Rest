package com.rest.api.filter;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.thymeleaf.util.ArrayUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class WhiteListIpFilter extends OncePerRequestFilter {

    private static final String[] ALLOWED_IP = { "0.0.0.0","127.0.0.1" };
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if(!ArrayUtils.contains(ALLOWED_IP, request.getRemoteAddr())){
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(MediaType.TEXT_PLAIN_VALUE);
            response.getWriter().println("Forbidden IP : " + request.getRemoteAddr());
            return;
        }
        filterChain.doFilter(request,response);
    }
}
