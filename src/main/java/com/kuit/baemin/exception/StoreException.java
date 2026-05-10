package com.kuit.baemin.exception;

import com.kuit.baemin.exception.errorcode.ErrorStatus;

public class StoreException extends GeneralException {
  public StoreException(ErrorStatus errorStatus) {
    super(errorStatus);
  }
}
