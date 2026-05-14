package com.kuit.baemin.exception;

import com.kuit.baemin.exception.errorcode.ErrorStatus;
import lombok.Getter;

@Getter
public class GeneralException extends RuntimeException {

  private final ErrorStatus errorStatus;

  public GeneralException(ErrorStatus errorStatus) {
    super(errorStatus.getMessage());
    this.errorStatus = errorStatus;
  }
}
