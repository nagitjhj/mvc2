package com.hi.mvc2basic.messages;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class MessagesTest {
    @Autowired
    MessageSource ms;

    @Test
    void 되나(){
        String hello = ms.getMessage("hello", null, null);
        System.out.println("hello = " + hello);
        assertThat(hello).isEqualTo("안녕");
    }

    @Test
    void 메시지_없을때(){
//        String helloooo = ms.getMessage("helloooo", null, null);
        assertThatThrownBy(()->ms.getMessage("helloooo", null, null)).isInstanceOf(NoSuchMessageException.class);
    }

    @Test
    void 디폴트메시지(){
        assertThat(ms.getMessage("hell", null, "나는기본", null)).isEqualTo("나는기본");
    }

    @Test
    void 매개변수사용(){
        String message = ms.getMessage("hello.spring", new Object[]{"최고", "신인왕타"}, null);
        System.out.println("message = " + message);
    }

    @Test
    void 국제화(){
        String hello = ms.getMessage("hello", null, Locale.ENGLISH);
        assertThat(hello).isEqualTo("hi");
    }

    @Test
    void 디폴트국제화(){
        String hello = ms.getMessage("hello", null, Locale.getDefault());
        assertThat(hello).isEqualTo("한국이다");
    }
}
