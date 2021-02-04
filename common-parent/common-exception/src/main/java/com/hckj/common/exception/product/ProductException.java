package com.hckj.common.exception.product;

import com.hckj.common.exception.business.BusinessException;

public class ProductException extends BusinessException {

    public ProductException() {
        super();
    }

    public ProductException(String message) {
        super(message);
    }

    public ProductException(int errorCode) {
        super(errorCode);
    }

    public ProductException(int errorCode, String message) {
        super(errorCode, message);
    }

}
