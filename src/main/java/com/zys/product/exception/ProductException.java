package com.zys.product.exception;

import com.zys.product.enums.ResultEnums;

public class ProductException extends RuntimeException{
    private Integer code;

    public ProductException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public ProductException(ResultEnums resultEnums) {
        super(resultEnums.getMessage());
        this.code = resultEnums.getCode();
    }


}
