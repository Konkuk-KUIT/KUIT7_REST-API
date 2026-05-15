package com.kuit.baemin.exception;

import com.kuit.baemin.exception.errorcode.ErrorStatus;

public class RestaurantException extends GeneralException {
    public RestaurantException(ErrorStatus errorStatus) {
        super(errorStatus);
    }
}
