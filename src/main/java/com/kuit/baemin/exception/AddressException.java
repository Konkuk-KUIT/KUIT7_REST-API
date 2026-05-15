package com.kuit.baemin.exception;

import com.kuit.baemin.exception.errorcode.ErrorStatus;

public class AddressException extends GeneralException {
    public AddressException(ErrorStatus errorStatus) {
        super(errorStatus);
    }
}
