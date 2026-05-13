package com.kuit.baemin.exception;

import com.kuit.baemin.exception.errorcode.ErrorStatus;

public class OrderException extends GeneralException {
    public OrderException(ErrorStatus errorStatus) {
        super(errorStatus);
    }
}
