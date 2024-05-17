package com.hi.mvc2basic;

import com.hi.mvc2basic.domain.Item;
import com.hi.mvc2basic.domain.ItemRepository;
import com.hi.mvc2basic.login.domain.member.Member;
import com.hi.mvc2basic.login.domain.member.MemberRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));

        Member member = new Member();
        member.setLoginId("aaa");
        member.setPassword("aaa");
        member.setName("aaa");
        memberRepository.save(member);
    }

}
