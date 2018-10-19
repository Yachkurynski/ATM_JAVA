package com.epam.atm.core.utils.exceptions;

public class GitHubRuntimeException extends RuntimeException {

  public GitHubRuntimeException(String exception) {
    super(exception);
  }

  @SuppressWarnings("unused")
  public GitHubRuntimeException(String exception, Throwable throwable) {
    super(exception, throwable);
  }
}
