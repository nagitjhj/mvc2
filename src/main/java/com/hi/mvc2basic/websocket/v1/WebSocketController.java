package com.hi.mvc2basic.websocket.v1;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class WebSocketController {
    //왜 이렇게??
    @RequestMapping("/myChat")
    public ModelAndView chat() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("socket/chat/chatting");
        return mv;
    }

//    @GetMapping("myChat")
//    public String chat(){
//        return "html/chat/chatting";
//    }
}
