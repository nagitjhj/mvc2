package com.hi.mvc2basic.session;

import com.hi.mvc2basic.login.domain.member.Member;
import com.hi.mvc2basic.login.web.session.SessionManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.assertj.core.api.Assertions.*;

public class SessionManagerTest {
    SessionManager manager = new SessionManager();

    @Test
    void sessionTest(){
        //세션 생성
        MockHttpServletResponse response = new MockHttpServletResponse();
        Member member = new Member();
        manager.createSession(member, response);

        //요청에 응답 쿠키 저장
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setCookies(response.getCookies());

        //세션 조회
        Object result = manager.getSession(request);
        assertThat(result).isEqualTo(member);

        //세션 만료
        manager.expire(request);
        Object expired = manager.getSession(request);
        assertThat(expired).isNull();
    }
}
