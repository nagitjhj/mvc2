package com.hi.mvc2basic.websocket.v1;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

//WebSocketChatting 클래스를 서비스(WebSocket)로 등록
@Slf4j
//@Service
//@ServerEndpoint(value = "/chat")
public class WebSocketChatting {
    private static Set<Session> CLIENTS = Collections.synchronizedSet(new HashSet<>());

    @OnOpen
    public void onOpen(Session session) {
        log.info("session {}", session.toString());

        if(CLIENTS.contains(session)) {
            log.info("SYSTEM 이미 연결된 세션 -> {}", session);
        }else {
            CLIENTS.add(session);
            log.info("SYSTEM 새로운 세션 -> {}", session);
        }
    }

    @OnClose
    public void onClose(Session session) {
        CLIENTS.remove(session);
        log.info("SYSTEM 세션을 닫음 -> {}", session);
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        log.info("SYSTEM 입력된 메시지 -> {}", message);
        log.info("SYSTEM 입력된 메시지 -> {}", message);

        for(Session client : CLIENTS) {
            log.info("SYSTEM 메시지 전달 -> {} -> {}", message, session);
            client.getBasicRemote().sendText(message);
        }
    }
}
