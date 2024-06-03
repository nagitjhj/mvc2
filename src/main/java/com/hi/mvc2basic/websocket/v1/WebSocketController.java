package com.hi.mvc2basic.websocket.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class WebSocketController {
    private final WebSocketChatting socketChatting;

    //왜 이렇게??
//    @RequestMapping("/myChat")
//    public ModelAndView chat() {
//        ModelAndView mv = new ModelAndView();
////        mv.
//        mv.setViewName("socket/chat/chatting");
//        return mv;
//    }

    @GetMapping("myChat")
    public String chat(Model model){
        String roomId = socketChatting.getRoomId();
        if(roomId != null){
            model.addAttribute("roomId", roomId);
        }else{
            model.addAttribute("roomId", System.currentTimeMillis());
        }
        return "socket/chat/chatting";
    }
}
