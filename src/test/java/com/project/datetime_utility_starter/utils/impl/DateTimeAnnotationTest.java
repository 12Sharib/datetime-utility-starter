package com.project.datetime_utility_starter.utils.impl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.project.datetime_utility_starter.TestBase;
import com.project.datetime_utility_starter.annotations.CurrentDate;
import com.project.datetime_utility_starter.annotations.CurrentDateTime;
import com.project.datetime_utility_starter.annotations.DateTimeAnnotationProcessor;
import com.project.datetime_utility_starter.utils.constants.TimezoneConstants;
import com.project.datetime_utility_starter.utils.formats.DateFormats;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Test;

class DateTimeAnnotationTest extends TestBase {

  @CurrentDate(format = DateFormats.FORMAT_CUSTOM_DD_MM_YYYY, timezone = TimezoneConstants.AMERICA_CHICAGO)
  private String createdAt;

  @CurrentDate(format = DateFormats.FORMAT_ISO_DATE)
  private String defaultDate;

  @CurrentDateTime(timezone = TimezoneConstants.ASIA_KOLKATA)
  private String time;

  @CurrentDateTime(timezone = TimezoneConstants.UTC)
  private String defaultDateTime;

  @Test
  void testFormattedAnnotation_CurrentDate() {
    assertNotNull(createdAt);
    assertDoesNotThrow(() -> LocalDate.parse(createdAt, DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    System.out.println("CreatedAt: " + createdAt);
  }

  @Test
  void testFormattedAnnotation_CurrentDateTime() {
    assertNotNull(time);
    assertDoesNotThrow(() -> LocalDateTime.parse(time,
        DateTimeFormatter.ofPattern(DateFormats.FORMAT_ISO_DATETIME)));
  }

  @Test
  void testDefaultFormat_CurrentDate() {
    assertNotNull(defaultDate);
    assertDoesNotThrow(
        () -> LocalDate.parse(defaultDate, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
  }

  @Test
  void testDefaultFormat_CurrentDateTime() {
    assertNotNull(defaultDateTime);
    assertDoesNotThrow(() -> LocalDateTime.parse(defaultDateTime,
        DateTimeFormatter.ofPattern(DateFormats.FORMAT_ISO_DATETIME)));
  }

  @Test
  void testDifferentTimezones() {
    assertNotEquals(createdAt, defaultDate);
    assertNotEquals(time, defaultDateTime);
  }


  @Test
  void testInvalidFormat() {
    Exception exception = assertThrows(RuntimeException.class, () -> {
      InvalidFormatTest invalidTest = new InvalidFormatTest();
      new DateTimeAnnotationProcessor().postProcessBeforeInitialization(invalidTest, null);
    });
    System.out.println(exception);
    assertTrue(exception.getMessage().contains("Invalid date format"));
  }

  @Test
  void testInvalidTimezone() {
    Exception exception = assertThrows(RuntimeException.class, () -> {
      InvalidTimezoneTest invalidTest = new InvalidTimezoneTest();
      new DateTimeAnnotationProcessor().postProcessBeforeInitialization(invalidTest, null);
    });
    assertTrue(exception.getMessage().contains("Invalid timezone"));
  }


  @Test
  void testStaticFieldNotSet() {
    assertNull(StaticFieldTest.staticField);
  }

  static class InvalidFormatTest {

    @CurrentDate(format = "invalid_format", timezone = TimezoneConstants.UTC)
    private String invalidDate;
  }

  static class InvalidTimezoneTest {

    @CurrentDate(format = "yyyy-MM-dd", timezone = "INVALID_ZONE")
    private String invalidTimezone;
  }

  static class ImmutableFinalFieldTest {

    @CurrentDate(format = "yyyy-MM-dd", timezone = TimezoneConstants.UTC)
    private final String finalDate = null;
  }

  static class StaticFieldTest {

    @CurrentDate(format = "yyyy-MM-dd", timezone = TimezoneConstants.UTC)
    private static String staticField;
  }
}
