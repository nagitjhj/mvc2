package com.hi.mvc2basic.servlet.web.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import java.io.IOException;
import java.util.UUID;

@Slf4j
//@WebFilter(filterName = "logFilter", urlPatterns = "/*")
public class LogFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("log filter init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        String uuid = UUID.randomUUID().toString();
        MDC.put("request_id", uuid);

        try {
            log.info("REQUEST [{}][{}][{}]", uuid, requestURI, request.getDispatcherType());
            chain.doFilter(request, response);
        } catch (Exception e) {
            throw e;
        } finally {
            log.info("RESPONSE [{}][{}][{}]", uuid, requestURI, request.getDispatcherType());
            MDC.clear();
        }
    }

    @Override
    public void destroy() {
        log.info("log filter destroy");
    }
}
