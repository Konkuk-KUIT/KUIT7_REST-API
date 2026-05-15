package com.kuit.baemin.exception;

import com.kuit.baemin.exception.errorcode.ErrorStatus;

public class CouponException extends GeneralException {
    public CouponException(ErrorStatus errorStatus) {
        super(errorStatus);
    }
}
