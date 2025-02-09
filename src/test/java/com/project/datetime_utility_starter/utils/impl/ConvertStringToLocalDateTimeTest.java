package com.project.datetime_utility_starter.utils.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.project.datetime_utility_starter.TestBase;
import com.project.datetime_utility_starter.utils.exceptions.DateTimeUtilException;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class ConvertStringToLocalDateTimeTest extends TestBase {

  @Test
  void testConvertStringToLocalDateTime_Positive() throws DateTimeUtilException {
    LocalDateTime expectedDateTime = LocalDateTime.of(2024, 2, 8, 15, 30, 45);
    assertEquals(expectedDateTime,
        dateTimeUtils.convertStringToLocalDateTime("2024-02-08 15:30:45", "yyyy-MM-dd HH:mm:ss"));
  }

  @Test
  void testConvertStringToLocalDateTime_Negative_InvalidDateFormat() {
    assertThrows(DateTimeUtilException.class,
        () -> dateTimeUtils.convertStringToLocalDateTime("08-02-2024 15:30:45",
            "yyyy-MM-dd HH:mm:ss"));
  }

  @Test
  void testConvertStringToLocalDateTime_Negative_InvalidTimeFormat() {
    assertThrows(DateTimeUtilException.class,
        () -> dateTimeUtils.convertStringToLocalDateTime("2024-02-08 25:30:45",
            "yyyy-MM-dd HH:mm:ss"));
  }

  @Test
  void testConvertStringToLocalDateTime_Negative_NullDateTime() {
    assertThrows(NullPointerException.class,
        () -> dateTimeUtils.convertStringToLocalDateTime(null, "yyyy-MM-dd HH:mm:ss"));
  }

  @Test
  void testConvertStringToLocalDateTime_CornerCase_Midnight() throws DateTimeUtilException {
    LocalDateTime expectedDateTime = LocalDateTime.of(2024, 2, 8, 0, 0, 0);
    assertEquals(expectedDateTime,
        dateTimeUtils.convertStringToLocalDateTime("2024-02-08 00:00:00", "yyyy-MM-dd HH:mm:ss"));
  }
}
