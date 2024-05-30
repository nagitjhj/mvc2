package com.hi.mvc2basic.login.web;

import com.hi.mvc2basic.domain.Item;
import com.hi.mvc2basic.domain.ItemRepository;
import com.hi.mvc2basic.domain.item.ItemSaveForm;
import com.hi.mvc2basic.domain.item.ItemUpdateForm;
import com.hi.mvc2basic.login.domain.member.Member;
import com.hi.mvc2basic.login.domain.member.MemberRepository;
import com.hi.mvc2basic.login.web.session.SessionManager;
import com.hi.mvc2basic.servlet.web.argumentresolver.Login;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
@RequestMapping("/login/items")
@RequiredArgsConstructor
public class LoginItemController {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final SessionManager sessionManager;

    @GetMapping
    public String home(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "login/item/items";
    }

//    @GetMapping("/home")
    public String items() {
        return "login/home";
    }

//    @GetMapping("/home")
    public String homeLogin(@CookieValue(name = "memberId", required = false) Long memberId, Model model) {
        if(memberId == null)
            return "login/home";

        //로그인
        Member loginMember = memberRepository.findById(memberId);
        if(loginMember == null)
            return "login/home";

        model.addAttribute("member", loginMember);
        return "login/loginHome";
    }

//    @GetMapping("/home")
    public String homeLoginV2(HttpServletRequest request, Model model) {
        //로그인
        Member member = (Member) sessionManager.getSession(request);
        if(member == null)
            return "login/home";

        model.addAttribute("member", member);
        return "login/loginHome";
    }

//    @GetMapping("/home")
    public String homeLoginV3(HttpServletRequest request, Model model) {
        //로그인
        HttpSession session = request.getSession(false);
        if(session == null)
            return "login/home";

        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        if(loginMember == null){
            return "login/home";
        }

        model.addAttribute("member", loginMember);
        return "login/loginHome";
    }

//    @GetMapping("/home")
    public String homeLoginV3Login(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember, Model model) {
        if(loginMember == null){
            return "login/home";
        }

        model.addAttribute("member", loginMember);
        return "login/loginHome";
    }

    @GetMapping("/home")
    public String homeLoginV3ArgumentResolver(@Login Member loginMember, Model model) {
        if(loginMember == null){
            return "login/home";
        }

        model.addAttribute("member", loginMember);
        return "login/loginHome";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "login/item/item";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("item", new Item());
        return "login/item/addForm";
    }

    @PostMapping("/add")
    public String addItem(@Validated @ModelAttribute("item") ItemSaveForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        //특정 필드 예외가 아닌 전체 예외
        if (form.getPrice() != null && form.getQuantity() != null) {
            int resultPrice = form.getPrice() * form.getQuantity();
            if (resultPrice < 10000) {
                bindingResult.reject("totalPriceMin", new Object[]{10000, resultPrice}, null);
            }
        }

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "login/item/addForm";
        }

        //성공 로직
        Item item = new Item();
        item.setItemName(form.getItemName());
        item.setPrice(form.getPrice());
        item.setQuantity(form.getQuantity());

        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/login/item/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "login/item/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @Validated @ModelAttribute("item") ItemUpdateForm form, BindingResult bindingResult) {

        //특정 필드 예외가 아닌 전체 예외
        if (form.getPrice() != null && form.getQuantity() != null) {
            int resultPrice = form.getPrice() * form.getQuantity();
            if (resultPrice < 10000) {
                bindingResult.reject("totalPriceMin", new Object[]{10000, resultPrice}, null);
            }
        }

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "login/item/editForm";
        }

        Item itemParam = new Item();
        itemParam.setItemName(form.getItemName());
        itemParam.setPrice(form.getPrice());
        itemParam.setQuantity(form.getQuantity());

        itemRepository.update(itemId, itemParam);
        return "redirect:/login/item/{itemId}";
    }

}
