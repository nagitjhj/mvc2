package com.hi.mvc2basic.api.resolver;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Slf4j
public class MyHandlerExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        //Object handler -> 핸들러(컨트롤러) 정보
        //Exception ex -> 핸들러(컨트롤러)에서 발생한 예외
        try{
            if(ex instanceof IllegalArgumentException){
                log.info("IllegalArgumentException resolver to 400");
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                //빈 모델앤뷰 리턴하기 -> 뷰를 렌더링 하지 않고, 정상 흐름으로 서블릿이 리턴된다.
                return new ModelAndView();
            }
        }catch (IOException e) {
            log.error("resolver ex", e);
        }
        return null;
    }
}
