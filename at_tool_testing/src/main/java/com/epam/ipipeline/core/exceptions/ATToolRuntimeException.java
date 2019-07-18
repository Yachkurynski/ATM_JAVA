package com.epam.ipipeline.core.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ATToolRuntimeException extends RuntimeException {

  public ATToolRuntimeException(String message) {
    super(message);
  }

  public ATToolRuntimeException(Throwable throwable) {
    super(throwable);
  }
}
