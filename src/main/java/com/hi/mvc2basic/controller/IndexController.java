package com.hi.mvc2basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("basic")
public class IndexController {
    @GetMapping("text")
    public String text(Model model){
        model.addAttribute("data", "[(${dataU})]");
        model.addAttribute("dataU", "<b>내가최고</b>");
        return "thymeleaf/text";
    }
}
