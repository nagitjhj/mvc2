package com.hi.mvc2basic.validation;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.MessageCodesResolver;

public class ValidationTest {
    MessageCodesResolver codesResolver = new DefaultMessageCodesResolver();

    @Test
    void 뭐하는거(){
        String[] strings = codesResolver.resolveMessageCodes("required", "item");
        for (String string : strings) {
            System.out.println("string = " + string);
        }
        Assertions.assertThat(strings).containsExactly("required.item", "required");
    }

    @Test
    void 필드오류(){
        String[] strings = codesResolver.resolveMessageCodes("required", "item", "itemName", String.class);
        for (String string : strings) {
            System.out.println("string = " + string);
        }
        Assertions.assertThat(strings).containsExactly(
                "required.item.itemName",
                "required.itemName",
                "required.java.lang.String",
                "required"
        );
    }
}
