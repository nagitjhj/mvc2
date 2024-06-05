package com.hi.mvc2basic.api.resolver;

import com.hi.mvc2basic.exception.UserException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Slf4j
public class UserHandlerExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
//        try {
//
//            if(ex instanceof UserException){
//                log.info("UserException resolver to 400");
////                request.
//            }
//
//        } catch (IOException e) {
//            log.error("resolver ex", e);
//        }

        return null;
    }
}
