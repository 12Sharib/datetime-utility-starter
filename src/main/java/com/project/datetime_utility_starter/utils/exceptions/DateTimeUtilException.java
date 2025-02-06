package com.project.datetime_utility_starter.utils.exceptions;

import com.project.datetime_utility_starter.utils.response.ErrorResponse;
import lombok.Getter;

@Getter
public class DateTimeUtilException extends Exception {
  private ErrorResponse errorResponse;
  private String message;

  public DateTimeUtilException(final ErrorResponse errorResponse) {
    this.errorResponse = errorResponse;
  }

  public DateTimeUtilException(String message) {
    this.message = message;
  }
}
