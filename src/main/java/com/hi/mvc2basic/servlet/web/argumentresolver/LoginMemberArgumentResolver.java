package com.hi.mvc2basic.servlet.web.argumentresolver;

import com.hi.mvc2basic.login.domain.member.Member;
import com.hi.mvc2basic.login.web.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Slf4j
public class LoginMemberArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        //@Login이면서 Member타입인 것을 확인하는 것 -> 해당하면 ArgumentResolver 사용됨.
        log.info("supportsParameter 실행");

        boolean hasParameterAnnotation = parameter.hasParameterAnnotation(Login.class);
        boolean hasMemberType = Member.class.isAssignableFrom(parameter.getParameterType());

        return hasParameterAnnotation && hasMemberType;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        //컨트롤러 호출 직전에 호출 되어서 필요한 파라미터 정보를 생성
        //여기서 우리가 하려는 것
        //  1. 세션에 있는 로그인 회원 정보인 member객체를 찾아서 반환해준다
        //  2. 이후 스프링mvc는 컨트롤러의 메서드를 호출하면서 여기서 반환된 member 객체를 파라미터에 전달해줌
        log.info("resolveArgument 실행");

        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        HttpSession session = request.getSession(false);
        if(session == null) {
            return null;
        }

        return session.getAttribute(SessionConst.LOGIN_MEMBER);
    }
}
