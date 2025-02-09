package com.project.datetime_utility_starter.utils.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.project.datetime_utility_starter.TestBase;
import com.project.datetime_utility_starter.utils.enums.ErrorsEnum;
import com.project.datetime_utility_starter.utils.exceptions.DateTimeUtilException;
import org.junit.jupiter.api.Test;

class IsExistInLeapYearTest extends TestBase {

  private final DateTimeUtilsImpl dateTimeUtils = new DateTimeUtilsImpl();

  @Test
  void testLeapYearDate_ShouldReturnTrue() throws DateTimeUtilException {
    assertTrue(dateTimeUtils.isExistsInLeapYear("2020-02-29"));
  }

  @Test
  void testNonLeapYearDate_ShouldReturnFalse() throws DateTimeUtilException {
    assertFalse(dateTimeUtils.isExistsInLeapYear("2019-03-01"));
  }

  @Test
  void testNonLeapYearEdgeCase_ShouldReturnFalse() throws DateTimeUtilException {
    assertFalse(dateTimeUtils.isExistsInLeapYear("1900-02-28"));
  }

  @Test
  void testValidNonLeapYearDate_ShouldReturnFalse() throws DateTimeUtilException {
    assertFalse(dateTimeUtils.isExistsInLeapYear("2023-12-01"));
  }

  @Test
  void testValidLeapYearDate_ShouldReturnTrue() throws DateTimeUtilException {
    assertTrue(dateTimeUtils.isExistsInLeapYear("2000-02-29"));
  }

  @Test
  void testLeapYearButInvalidDate_ShouldThrowException() {
    DateTimeUtilException exception = assertThrows(DateTimeUtilException.class,
        () -> dateTimeUtils.isExistsInLeapYear("2000-02-30"));
    assertEquals(ErrorsEnum.INVALID_DATE.getMessage(), exception.getMessage());
  }


  // Negative Test Cases
  @Test
  void testInvalidDateFormat_ShouldThrowException() {
    DateTimeUtilException exception = assertThrows(DateTimeUtilException.class,
        () -> dateTimeUtils.isExistsInLeapYear("29-02-2020"));
    assertEquals(ErrorsEnum.INVALID_DATE_FORMAT.getMessage(), exception.getMessage());
  }

  @Test
  void testEmptyDate_ShouldThrowException() {
    DateTimeUtilException exception = assertThrows(DateTimeUtilException.class,
        () -> dateTimeUtils.isExistsInLeapYear(""));
    assertEquals(ErrorsEnum.INVALID_DATE_FORMAT.getMessage(), exception.getMessage());
  }

  @Test
  void testNullDate_ShouldThrowException() {

    assertThrows(NullPointerException.class, () -> dateTimeUtils.isExistsInLeapYear(null));
  }

  @Test
  void testInvalidMonth_ShouldThrowException() {
    DateTimeUtilException exception = assertThrows(DateTimeUtilException.class,
        () -> dateTimeUtils.isExistsInLeapYear("2020-13-01"));
    assertEquals(ErrorsEnum.INVALID_DATE_FORMAT.getMessage(), exception.getMessage());
  }

  @Test
  void testInvalidDay_ShouldThrowException() {
    DateTimeUtilException exception = assertThrows(DateTimeUtilException.class,
        () -> dateTimeUtils.isExistsInLeapYear("2020-02-30"));
    assertEquals(ErrorsEnum.INVALID_DATE.getMessage(), exception.getMessage());
  }

  @Test
  void testNonExistentDate_ShouldThrowException() {
    DateTimeUtilException exception = assertThrows(DateTimeUtilException.class,
        () -> dateTimeUtils.isExistsInLeapYear("2020-04-31"));
    assertEquals(ErrorsEnum.INVALID_DATE.getMessage(), exception.getMessage());
  }

}
