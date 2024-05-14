package com.hi.mvc2basic.domain;

import com.hi.mvc2basic.domain.item.SaveCheck;
import com.hi.mvc2basic.domain.item.UpdateCheck;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.ScriptAssert;

import java.util.List;

@Data
//@ScriptAssert(lang = "javascript", script = "_this.price * _this.quantity >= 10000") //안씀
public class Item {
//    @NotNull(groups = UpdateCheck.class)
    private Long id;
//    @NotBlank(message = "공백이랍니다", groups = {SaveCheck.class, UpdateCheck.class})
    private String itemName;
//    @NotBlank
//    @NotNull(groups = {SaveCheck.class, UpdateCheck.class})
//    @Range(min = 1000, max=1000000, groups = {SaveCheck.class, UpdateCheck.class})
    private Integer price;
//    @NotBlank
//    @NotNull(groups = {SaveCheck.class, UpdateCheck.class})
//    @Max(value = 9999, groups = SaveCheck.class)
    private Integer quantity;

    private Boolean open;
    private List<String> regions;
    private ItemType itemType;
    private String deliveryCode;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
