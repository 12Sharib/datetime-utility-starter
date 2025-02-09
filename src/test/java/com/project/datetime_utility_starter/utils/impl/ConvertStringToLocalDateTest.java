package com.project.datetime_utility_starter.utils.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.project.datetime_utility_starter.TestBase;
import com.project.datetime_utility_starter.utils.exceptions.DateTimeUtilException;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class ConvertStringToLocalDateTest extends TestBase {


  @Test
  void testConvertStringToLocalDate_Positive() throws DateTimeUtilException {
    LocalDate expectedDate = LocalDate.of(2024, 2, 8);
    assertEquals(expectedDate, dateTimeUtils.convertStringToLocalDate("2024-02-08", "yyyy-MM-dd"));
  }

  @Test
  void testConvertStringToLocalDate_Negative_InvalidDateFormat() {
    assertThrows(DateTimeUtilException.class,
        () -> dateTimeUtils.convertStringToLocalDate("08-02-2024", "yyyy-MM-dd"));
  }

  @Test
  void testConvertStringToLocalDate_Negative_EmptyDate() {
    assertThrows(DateTimeUtilException.class,
        () -> dateTimeUtils.convertStringToLocalDate("", "yyyy-MM-dd"));
  }

  @Test
  void testConvertStringToLocalDate_Negative_NullDate() {
    assertThrows(NullPointerException.class,
        () -> dateTimeUtils.convertStringToLocalDate(null, "yyyy-MM-dd"));
  }

  @Test
  void testConvertStringToLocalDate_Negative_InvalidFormat() {
    assertThrows(DateTimeUtilException.class,
        () -> dateTimeUtils.convertStringToLocalDate("2024-02-08", "invalid-format"));
  }

  @Test
  void testConvertStringToLocalDate_CornerCase_LeapYear() throws DateTimeUtilException {
    LocalDate expectedDate = LocalDate.of(2024, 2, 29);
    assertEquals(expectedDate, dateTimeUtils.convertStringToLocalDate("2024-02-29", "yyyy-MM-dd"));
  }

  @Test
  void testConvertStringToLocalDate_CornerCase_MinDate() throws DateTimeUtilException {
    LocalDate expectedDate = LocalDate.of(1900, 1, 1);
    assertEquals(expectedDate, dateTimeUtils.convertStringToLocalDate("1900-01-01", "yyyy-MM-dd"));
  }
}
