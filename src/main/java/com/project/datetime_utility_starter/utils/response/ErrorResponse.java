package com.project.datetime_utility_starter.utils.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {

  private boolean success;
  private String message;

  public ErrorResponse(final Boolean success, final String message) {
    this.success = success;
    this.message = message;
  }

  public ErrorResponse(final String message) {
    this.message = message;
  }
}
