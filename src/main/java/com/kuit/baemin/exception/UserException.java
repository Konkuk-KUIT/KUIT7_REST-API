package com.kuit.baemin.exception;

import com.kuit.baemin.exception.errorcode.ErrorStatus;

public class
UserException extends GeneralException {
    public UserException(ErrorStatus errorStatus) {
        super(errorStatus);
    }
}
