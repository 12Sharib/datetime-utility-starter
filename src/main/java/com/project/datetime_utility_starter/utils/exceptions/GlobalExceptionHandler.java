package com.project.datetime_utility_starter.utils.exceptions;

import com.project.datetime_utility_starter.utils.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler extends RuntimeException {
  @ExceptionHandler(DateTimeUtilException.class)
  public ResponseEntity<ErrorResponse> dateTimeUtilExceptions(final DateTimeUtilException exception)
      throws DateTimeUtilException {
    return new ResponseEntity<>(exception.getErrorResponse(), HttpStatus.BAD_REQUEST);
  }
}
