package com.hi.mvc2basic.controller.validation;

import com.hi.mvc2basic.domain.item.ItemSaveForm;
import com.hi.mvc2basic.domain.item.ItemSaveFormField;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ItemValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return ItemSaveFormField.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ItemSaveFormField item = (ItemSaveFormField) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "itemName", "required");

        if(item.getPrice() == null) {
            errors.rejectValue("price", "range", new Object[]{1000, 10000}, null);
        }

    }
}
