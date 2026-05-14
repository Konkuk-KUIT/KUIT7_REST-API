package com.kuit.baemin.exception.handler;

import com.kuit.baemin.exception.GeneralException;
import com.kuit.baemin.exception.errorcode.ErrorStatus;

public class CouponException extends GeneralException {
    public CouponException(ErrorStatus errorStatus) {
        super(errorStatus);
    }
}
