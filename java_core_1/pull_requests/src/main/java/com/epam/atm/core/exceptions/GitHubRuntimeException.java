package com.epam.atm.core.exceptions;

public class GitHubRuntimeException extends RuntimeException {

  public GitHubRuntimeException(String exception) {
    super(exception);
  }

  public GitHubRuntimeException(String exception, Throwable throwable) {
    super(exception, throwable);
  }
}
