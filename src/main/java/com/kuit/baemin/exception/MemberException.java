package com.kuit.baemin.exception;

import com.kuit.baemin.exception.errorcode.ErrorStatus;

public class MemberException extends GeneralException {

  public MemberException(ErrorStatus errorStatus) {
    super(errorStatus);
  }
}
