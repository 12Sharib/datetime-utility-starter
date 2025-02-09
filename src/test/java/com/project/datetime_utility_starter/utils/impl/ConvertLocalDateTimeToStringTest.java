package com.project.datetime_utility_starter.utils.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.project.datetime_utility_starter.TestBase;
import com.project.datetime_utility_starter.utils.exceptions.DateTimeUtilException;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class ConvertLocalDateTimeToStringTest extends TestBase {

  @Test
  void testConvertLocalDateTimeToString_Positive() throws DateTimeUtilException {
    LocalDateTime dateTime = LocalDateTime.of(2024, 2, 8, 15, 30, 45);
    assertEquals("2024-02-08 15:30:45",
        dateTimeUtils.convertLocalDateTimeToString(dateTime, "yyyy-MM-dd HH:mm:ss"));
  }

  @Test
  void testConvertLocalDateTimeToString_Negative_NullDateTime() {
    assertThrows(NullPointerException.class,
        () -> dateTimeUtils.convertLocalDateTimeToString(null, "yyyy-MM-dd HH:mm:ss"));
  }

  @Test
  void testConvertLocalDateTimeToString_Negative_InvalidFormat() {
    assertThrows(DateTimeUtilException.class,
        () -> dateTimeUtils.convertLocalDateTimeToString(LocalDateTime.now(), "invalid-format"));
  }

  @Test
  void testConvertLocalDateTimeToString_CornerCase_Midnight() throws DateTimeUtilException {
    LocalDateTime dateTime = LocalDateTime.of(2024, 2, 8, 0, 0, 0);
    assertEquals("2024-02-08 00:00:00",
        dateTimeUtils.convertLocalDateTimeToString(dateTime, "yyyy-MM-dd HH:mm:ss"));
  }
}
