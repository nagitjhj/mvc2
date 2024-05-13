package com.hi.mvc2basic.controller;

import com.hi.mvc2basic.domain.Item;
import com.hi.mvc2basic.domain.ItemRepository;
import com.hi.mvc2basic.domain.Language;
import com.hi.mvc2basic.domain.LocaleDTO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Locale;

@Slf4j
@Controller
@RequestMapping("/message/items")
@RequiredArgsConstructor
public class MessageItemController {
    private final ItemRepository itemRepository;
    private final LocaleResolver localeResolver;

    @GetMapping
    public String items(Model model, HttpSession session) {
        List<Item> items = itemRepository.findAll();
        Object locale = session.getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME);
        model.addAttribute("items", items);
        model.addAttribute("languages", Language.values());
        model.addAttribute("lang", new LocaleDTO((Locale) locale));
//        model.addAttribute("lang", new LocaleDTO());
        model.addAttribute("currentLocale", session.getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME));
        return "message/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "message/item";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("item", new Item());
        return "message/addForm";
    }

    @PostMapping("/add")
    public String addItem(@ModelAttribute Item item, RedirectAttributes redirectAttributes) {
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/message/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "message/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);
        return "redirect:/message/items/{itemId}";
    }

    @ResponseBody
    @PostMapping("/lang")
    public ResponseEntity<String> lang(@RequestBody LocaleDTO locale, HttpSession session){
        try{
            session.setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, locale.getLocale());
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
