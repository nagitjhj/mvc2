package com.hi.mvc2basic.websocket.v3;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class MatchmakingService {
    private static final Map<String, String> chatMap = new ConcurrentHashMap<>();

    public String findRandomRecipient(String sender) {
        //대기중인 사용자 목록에서 랜덤하게 선택
        String recipient = chatMap.get("waitingUser");
        if(recipient == null) {
            chatMap.put("waitingUser", sender);
            return null;
        } else {
            chatMap.remove("waitingUser");
            return recipient;
        }
    }
}
