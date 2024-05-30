package com.hi.mvc2basic.domain.item;

import com.hi.mvc2basic.controller.validation.sequence.ValidationGroups;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import java.util.Set;

import static com.hi.mvc2basic.controller.validation.sequence.ValidationGroups.*;

@Data
public class ItemSaveFormField {
    @Email(groups = ItemNameGroup.class)
//    @Min(value = 10, groups = ItemNameGroup.class)
//    @Range(min = 1000, max = 1000000, groups = ItemNameGroup.class)
//    @Max(value = 1, groups = ItemNameGroup.class)
    @NotBlank(groups = ItemNameGroup.class)
    private String itemName;
    @NotNull(groups = PriceGroup.class)
//    @Min(value = 10, groups = PriceGroup.class)
    @Range(min = 1000, max = 1000000, groups = PriceGroup.class)
    private Integer price;
    @NotNull(groups = QuantityGroup.class)
    @Max(value = 9999, groups = QuantityGroup.class)
    private Integer quantity;

//    public void validate() {
//        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
//        Set<ConstraintViolation<ItemSaveFormField>> violations = validator.validate(this);
//        if (!violations.isEmpty()) {
//            // 에러 메시지 처리
//            for (ConstraintViolation<ItemSaveFormField> violation : violations) {
//                System.out.println(violation.getMessage());
//            }
//        }
//    }
}
