package com.hi.mvc2basic.controller.validation;

import com.hi.mvc2basic.domain.Item;
import com.hi.mvc2basic.domain.ItemRepository;
import com.hi.mvc2basic.domain.item.ItemSaveForm;
import com.hi.mvc2basic.domain.item.ItemUpdateForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/validation/v5/items")
@RequiredArgsConstructor
public class ValidationItemControllerV5 {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(@RequestParam(defaultValue = "1", required = false) int page, Model model) {
        List<Item> items = itemRepository.findPaging(page);
        model.addAttribute("items", items);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages",3);
        return "validation/v5/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "validation/v5/item";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("item", new Item());
        return "validation/v5/addForm";
    }

    @GetMapping("/addJson")
    public String addFormJson(Model model) {
        model.addAttribute("item", new Item());
        return "validation/v5/addFormJson";
    }

    @PostMapping("/add")
    public String addItem(@Validated @ModelAttribute("item") ItemSaveForm form
            , BindingResult bindingResult
            , RedirectAttributes redirectAttributes
            , Model model) {

        //글로벌 오류는 이렇게 자바 코드로 사용하자
        if(form.getPrice() != null && form.getQuantity() != null){
            int resultPrice = form.getPrice() * form.getQuantity();
            if(resultPrice<10000){
                bindingResult.reject("totalPriceMin", new Object[]{10000, resultPrice}, null);
            }
        }

        if(bindingResult.hasErrors()){
            return "validation/v5/addForm";
        }

        //성공 로직
        Item item = new Item();
        item.setItemName(form.getItemName());
        item.setPrice(form.getPrice());
        item.setQuantity(form.getQuantity());

        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v5/items/{itemId}";
    }

    @PostMapping("/addJson")
    public String addItemJson(@Validated @RequestBody ItemSaveForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        //글로벌 오류는 이렇게 자바 코드로 사용하자
        if(form.getPrice() != null && form.getQuantity() != null){
            int resultPrice = form.getPrice() * form.getQuantity();
            if(resultPrice<10000){
                bindingResult.reject("totalPriceMin", new Object[]{10000, resultPrice}, null);
            }
        }

        if(bindingResult.hasErrors()){
            return "validation/v5/addFormJson";
        }

        //성공 로직
        Item item = new Item();
        item.setItemName(form.getItemName());
        item.setPrice(form.getPrice());
        item.setQuantity(form.getQuantity());

        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v5/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "validation/v5/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@RequestParam int page
            , @PathVariable Long itemId
            , @ModelAttribute("item") @Validated ItemUpdateForm form
            , BindingResult bindingResult
            , Model model) {

        if(form.getPrice() != null && form.getQuantity() != null){
            int resultPrice = form.getPrice() * form.getQuantity();
            if(resultPrice<10000){
                bindingResult.reject("totalPriceMin", new Object[]{10000, resultPrice}, null);
            }
        }

        model.addAttribute("page", page);
        if(bindingResult.hasErrors()){
            return "validation/v5/editForm";
        }

        //성공 로직
        Item itemParam = new Item();
        itemParam.setItemName(form.getItemName());
        itemParam.setPrice(form.getPrice());
        itemParam.setQuantity(form.getQuantity());

        itemRepository.update(itemId, itemParam);
        return "redirect:/validation/v5/items/{itemId}?page="+page;
    }
}
