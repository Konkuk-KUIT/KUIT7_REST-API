package com.kuit.baemin.exception;

import com.kuit.baemin.exception.errorcode.ErrorStatus;

public class FavoriteException extends GeneralException {
  public FavoriteException(ErrorStatus errorStatus) {
    super(errorStatus);
  }
}
