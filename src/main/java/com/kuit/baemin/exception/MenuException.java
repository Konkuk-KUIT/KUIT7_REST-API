package com.kuit.baemin.exception;
import com.kuit.baemin.exception.errorcode.ErrorStatus;
public class MenuException extends GeneralException {
    public MenuException(ErrorStatus errorStatus) { super(errorStatus); }
}