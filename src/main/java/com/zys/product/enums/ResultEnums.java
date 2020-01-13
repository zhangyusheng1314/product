package com.zys.product.enums;

import lombok.Getter;

@Getter
public enum ResultEnums {
    PRODUCT_IS_EXISTS(1,"商品不存在"),
    PRODUCT_IS_ENOUGH(2,"商品不足")
    ;
    private Integer code;

    private String message;

    ResultEnums(Integer code,String message) {
        this.code = code;
        this.message =message;
    }
}
