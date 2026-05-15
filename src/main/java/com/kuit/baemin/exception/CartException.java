package com.kuit.baemin.exception;

import com.kuit.baemin.exception.errorcode.ErrorStatus;

public class CartException extends GeneralException {

    public CartException(ErrorStatus errorStatus) {
        super(errorStatus);
    }
}
