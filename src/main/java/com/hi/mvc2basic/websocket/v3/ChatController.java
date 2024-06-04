package com.hi.mvc2basic.websocket.v3;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;
    private final MatchmakingService matchmakingService;

    @MessageMapping("/chat2")
    public void processMessage(ChatMessage message, SimpMessageHeaderAccessor accessor) {
//        if(chatMessage.getRecipient() == null) {
//            chatMessage.setRecipient(matchmakingService.findRandomRecipient(chatMessage.getSender()));
//        }
//        if (message.getRecipient() != null) {
        matchmakingService.setRecipient(message.getSender(), message.getRecipient());
        String destination = "/queue/" + message.getSender();
//            messagingTemplate.convertAndSend(destination, chatMessage); //여기서 처리함
//        }
        //특정 구독자에게 발송 convertAndSendToUser
        messagingTemplate.convertAndSend(destination, message); //여기서 처리함
        //@SendTo나 messagingTemplated으로 메시지를 클라이언트로 전달
    }

//    @MessageMapping("/userToSend")
//    public void userToSend(String message, ){
//
//    }

    @GetMapping("/chatting")
    public String chatting(Model model){
//        Long roomId = matchmakingService.getRoomId();
//        if(roomId != null){
//            model.addAttribute("roomId", roomId);
//        }else{
//            model.addAttribute("roomId", null);
//        }
        return "socket/v3/chat3";
    }

}
