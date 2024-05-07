package com.hi.mvc2basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/message/items")
public class MessagesController {
    @GetMapping("/")
    public String messages(){
        return "message/item";
    }
}
