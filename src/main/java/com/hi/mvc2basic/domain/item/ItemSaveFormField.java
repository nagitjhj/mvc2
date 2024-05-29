package com.hi.mvc2basic.domain.item;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import java.util.Set;

@Data
public class ItemSaveFormField {
    @Email
    @Min(10)
    @Range(min = 1000, max = 1000000)
    @Max(1)
    private String itemName;
    @NotNull
    @Min(10)
    @Range(min = 1000, max = 1000000)
    private Integer price;
    @NotNull
    @Max(value = 9999)
    private Integer quantity;

    public void validate() {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<ItemSaveFormField>> violations = validator.validate(this);
        if (!violations.isEmpty()) {
            // 에러 메시지 처리
            for (ConstraintViolation<ItemSaveFormField> violation : violations) {
                System.out.println(violation.getMessage());
            }
        }
    }
}
