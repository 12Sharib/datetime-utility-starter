package com.project.datetime_utility_starter.utils.enums;

import lombok.Getter;

@Getter
public enum ErrorsEnum {
  INVALID_DATE_FORMAT("Invalid date format. Ensure the dates match the required format: "),
  INVALID_TIMEZONE("Invalid timezone"),
  INVALID_FORMAT("Invalid format"),
  UNSUPPORTED_FIELD_TYPE("Unsupported field type"),
  FIELD_VALUE_SET_FAILED("Failed to set value for field"),
  BEAN_CREATION_FAILED("Bean creation failed due to date/time processing error");


  private final String message;
  ErrorsEnum(final String message){
    this.message = message;
  }
}
