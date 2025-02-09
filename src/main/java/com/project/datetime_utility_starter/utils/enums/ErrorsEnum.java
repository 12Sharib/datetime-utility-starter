package com.project.datetime_utility_starter.utils.enums;

import lombok.Getter;

@Getter
public enum ErrorsEnum {
  INVALID_DATE_FORMAT(
      "Invalid date format. Please use one of the predefined formats from the DateFormats class"),
  INVALID_TIMEZONE("Invalid timezone."),
  UNSUPPORTED_FIELD_TYPE("Unsupported field type."),
  FIELD_VALUE_SET_FAILED("Failed to set value for field."),
  BEAN_CREATION_FAILED("Bean creation failed due to date/time processing error"),
  INVALID_DESIRED_FORMAT(
      "Invalid date format. Please use one of the predefined formats from the DateFormats class."),
  INVALID_UNIT(
      "Invalid unit. Use 'days', 'weeks', or 'months'."),
  INVALID_END_BEFORE_START_DATE("Start date must not be after the end date."),
  INVALID_DATE(
      "Invalid date, provide valid date."),
  INVALID_BIRTH_DATE("Birthdate cannot be in the future");


  private final String message;

  ErrorsEnum(final String message) {
    this.message = message;
  }
}
