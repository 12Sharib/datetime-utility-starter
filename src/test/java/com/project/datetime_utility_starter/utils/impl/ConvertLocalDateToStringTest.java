package com.project.datetime_utility_starter.utils.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.project.datetime_utility_starter.TestBase;
import com.project.datetime_utility_starter.utils.exceptions.DateTimeUtilException;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class ConvertLocalDateToStringTest extends TestBase {

  @Test
  void testConvertLocalDateToString_Positive() throws DateTimeUtilException {
    LocalDate date = LocalDate.of(2024, 2, 8);
    assertEquals("2024-02-08", dateTimeUtils.convertLocalDateToString(date, "yyyy-MM-dd"));
  }

  @Test
  void testConvertLocalDateToString_Negative_NullDate() {
    assertThrows(NullPointerException.class,
        () -> dateTimeUtils.convertLocalDateToString(null, "yyyy-MM-dd"));
  }

  @Test
  void testConvertLocalDateToString_Negative_InvalidFormat() {
    assertThrows(DateTimeUtilException.class,
        () -> dateTimeUtils.convertLocalDateToString(LocalDate.now(), "invalid-format"));
  }

  @Test
  void testConvertLocalDateToString_CornerCase_LeapYear() throws DateTimeUtilException {
    LocalDate date = LocalDate.of(2024, 2, 29);
    assertEquals("2024-02-29", dateTimeUtils.convertLocalDateToString(date, "yyyy-MM-dd"));
  }
}
