package com.kuit.baemin.exception;

import com.kuit.baemin.exception.errorcode.ErrorStatus;

public class SteamedException extends GeneralException {
    public SteamedException(ErrorStatus errorStatus) { super(errorStatus); }
}
