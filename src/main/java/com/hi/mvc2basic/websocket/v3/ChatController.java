package com.hi.mvc2basic.websocket.v3;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;
    private final MatchmakingService matchmakingService;

    @MessageMapping("/chat")
    public void processMessage(ChatMessage chatMessage) {
        if(chatMessage.getRecipient() == null) {
            chatMessage.setRecipient(matchmakingService.findRandomRecipient(chatMessage.getSender()));
        }
        if (chatMessage.getRecipient() != null) {
            String destination = "/queue/" + chatMessage.getRecipient();
//            messagingTemplate.convertAndSend(destination, chatMessage); //여기서 처리함
        }
        messagingTemplate.convertAndSend("/queue/1", chatMessage); //여기서 처리함
        //@SendTo나 messagingTemplated으로 메시지를 클라이언트로 전달
    }

    @GetMapping("/chatting")
    public String chatting(){
        return "socket/v3/chat3";
    }

}
