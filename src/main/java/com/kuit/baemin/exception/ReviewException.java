package com.kuit.baemin.exception;

import com.kuit.baemin.exception.errorcode.ErrorStatus;

public class ReviewException extends GeneralException {
    public ReviewException(ErrorStatus errorStatus) {
        super(errorStatus);
    }
}
