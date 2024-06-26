package com.hi.mvc2basic.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("basic")
public class IndexController {
    @GetMapping("text")
    public String text(Model model){
        model.addAttribute("data", "[(${dataU})]");
        model.addAttribute("dataU", "<b>내가최고</b>");
        return "thymeleaf/text";
    }

    @GetMapping("variable")
    public String variable(Model model){
        User userA = new User("userA", 10);
        User userB = new User("userB", 20);

        List<User> list = new ArrayList<>();
        list.add(userA);
        list.add(userB);

        Map<String, User> map = new HashMap<>();
        map.put("userA", userA);
        map.put("userB", userB);

        model.addAttribute("user", userA);
        model.addAttribute("users", list);
        model.addAttribute("userMap", map);

        return "thymeleaf/variable";
    }

    @Data
    @AllArgsConstructor
    static class User{
        private String username;
        private int age;
    }

    @GetMapping("/basic-objects")
    public String basicObjects(){
        return "thymeleaf/basic-objects";
    }

    @Component("helloBean")
    static class HelloBean{
        public String hello(String data){
            return "hello " + data;
        }
    }

    @GetMapping("/link")
    public String link(Model model){
        model.addAttribute("param1", "내가최고");
        model.addAttribute("param2", "내가짱");
        return "thymeleaf/link";
    }

    @GetMapping("/link/{id}")
    public String linkPath(@PathVariable("id") int id, Model model){
        model.addAttribute("param1", "내가최고");
        model.addAttribute("param2", "내가짱");
        return "thymeleaf/link";
    }

    @GetMapping("/literal")
    public String literal(Model model){
        model.addAttribute("data", "나는최고");
        return "thymeleaf/literal";
    }

    @GetMapping("/operation")
    public String operation(Model model){
        model.addAttribute("nullData", null);
        model.addAttribute("data", "내가짱!!!!!");
        return "thymeleaf/operation";
    }

    @GetMapping("/attribute")
    public String attribute(Model model){
        model.addAttribute("nullData", null);
        model.addAttribute("data", "내가짱!!!!!");
        return "thymeleaf/attribute";
    }

    @GetMapping("/each")
    public String each(Model model){
        addUsers(model);
        Map<String, User> map = new HashMap();
        map.put("userB", new User("userB", 10));
        map.put("userA", new User("userA", 10));
        map.put("userC", new User("userC", 10));
        model.addAttribute("maps", map);
        return "thymeleaf/each";
    }

    private void addUsers (Model model){
        List<User> list = new ArrayList<>();
        list.add(new User("userA", 10));
        list.add(new User("userB", 20));
        list.add(new User("userC", 30));
        model.addAttribute("users", list);
    }

    @GetMapping("/condition")
    public String condition(Model model){
        addUsers(model);
        return "thymeleaf/condition";
    }

    @GetMapping("/block")
    public String block(Model model){
        addUsers(model);
        return "thymeleaf/block";
    }

    @GetMapping("/javascript")
    public String javascript(Model model){
        model.addAttribute("user", new User("userA", 10));
        addUsers(model);
        return "thymeleaf/javascript";
    }

    @GetMapping("/fragment")
    public String fragment(){
        return "thymeleaf/template/fragment/fragmentMain";
    }

    @GetMapping("/layout")
    public String layout(){
        return "thymeleaf/template/layout/layoutMain";
    }

    @GetMapping("/layoutExtend")
    public String layoutExtend(){
        return "thymeleaf/template/layoutExtend/layoutExtendMain";
    }


}
