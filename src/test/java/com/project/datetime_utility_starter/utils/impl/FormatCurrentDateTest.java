package com.project.datetime_utility_starter.utils.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.project.datetime_utility_starter.TestBase;
import com.project.datetime_utility_starter.utils.exceptions.DateTimeUtilException;
import com.project.datetime_utility_starter.utils.formats.DateFormats;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Test;

class FormatCurrentDateTest extends TestBase {

  @Test
  void testFormatCurrentDate_BASIC() throws DateTimeUtilException {
    String expectedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    String response = dateTimeUtils.formatCurrentDate(DateFormats.FORMAT_BASIC);
    assertEquals(expectedDate, response);
  }

  @Test
  void testFormatCurrentDate_BASIC_DATETIME() throws DateTimeUtilException {
    String expectedDateTime =
        LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
    String response = dateTimeUtils.formatCurrentDate(DateFormats.FORMAT_BASIC_DATETIME);
    assertEquals(expectedDateTime, response);
  }

  @Test
  void testFormatCurrentDate_ISO_DATE() throws DateTimeUtilException {
    String expectedDate = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
    String response = dateTimeUtils.formatCurrentDate(DateFormats.FORMAT_ISO_DATE);
    assertEquals(expectedDate, response);
  }

  @Test
  void testFormatCurrentDate_ISO_DATETIME() throws DateTimeUtilException {
    String expectedDateTime =
        LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
    String response = dateTimeUtils.formatCurrentDate(DateFormats.FORMAT_ISO_DATETIME);
    assertEquals(expectedDateTime, response);
  }

  @Test
  void testFormatCurrentDate_ISO_DATETIME_MILLIS_X() throws DateTimeUtilException {
    String expectedDateTime =
        ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX"));
    String response = dateTimeUtils.formatCurrentDate(DateFormats.FORMAT_ISO_DATETIME_MILLIS_X);
    assertEquals(expectedDateTime, response);
  }

  @Test
  void testFormatCurrentDate_ISO_DATETIME_MILLIS_Z() throws DateTimeUtilException {
    String expectedDateTime =
        ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ"));
    String response = dateTimeUtils.formatCurrentDate(DateFormats.FORMAT_ISO_DATETIME_MILLIS_Z);
    assertEquals(expectedDateTime, response);
  }

  @Test
  void testFormatCurrentDate_CUSTOM_DD_MM_YYYY() throws DateTimeUtilException {
    String expectedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    String response = dateTimeUtils.formatCurrentDate(DateFormats.FORMAT_CUSTOM_DD_MM_YYYY);
    assertEquals(expectedDate, response);
  }

  @Test
  void testFormatCurrentDate_CUSTOM_MM_DD_YYYY() throws DateTimeUtilException {
    String expectedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
    String response = dateTimeUtils.formatCurrentDate(DateFormats.FORMAT_CUSTOM_MM_DD_YYYY);
    assertEquals(expectedDate, response);
  }

  @Test
  void testFormatCurrentDate_CUSTOM_FULL_DATE() throws DateTimeUtilException {
    String expectedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("MMMM d, yyyy"));
    String response = dateTimeUtils.formatCurrentDate(DateFormats.FORMAT_CUSTOM_FULL_DATE);
    assertEquals(expectedDate, response);
  }

  @Test
  void testFormatCurrentDate_CUSTOM_DD_MMMM_YYYY() throws DateTimeUtilException {
    String expectedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("d MMMM yyyy"));
    String response = dateTimeUtils.formatCurrentDate(DateFormats.FORMAT_CUSTOM_DD_MMMM_YYYY);
    assertEquals(expectedDate, response);
  }

  @Test
  void testFormatCurrentDate_CUSTOM_DD_MMM_YYYY() throws DateTimeUtilException {
    String expectedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
    String response = dateTimeUtils.formatCurrentDate(DateFormats.FORMAT_CUSTOM_DD_MMM_YYYY);
    assertEquals(expectedDate, response);
  }

  @Test
  void testFormatCurrentDate_TIME_ONLY_HH_MM_SS() throws DateTimeUtilException {
    String expectedTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    String response = dateTimeUtils.formatCurrentDate(DateFormats.FORMAT_TIME_ONLY_HH_MM_SS);
    assertEquals(expectedTime, response);
  }

  @Test
  void testFormatCurrentDate_TIME_ONLY_HH_MM_AM_PM() throws DateTimeUtilException {
    String expectedTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("hh:mm a"));
    String response = dateTimeUtils.formatCurrentDate(DateFormats.FORMAT_TIME_ONLY_HH_MM_AM_PM);
    assertEquals(expectedTime, response);
  }

  @Test
  void testFormatCurrentDate_TIME_ONLY_HH_MM() throws DateTimeUtilException {
    String expectedTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
    String response = dateTimeUtils.formatCurrentDate(DateFormats.FORMAT_TIME_ONLY_HH_MM);
    assertEquals(expectedTime, response);
  }

  @Test
  void testFormatCurrentDate_DATETIME_DD_MM_HH_MM_SS() throws DateTimeUtilException {
    String expectedDateTime =
        LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    String response = dateTimeUtils.formatCurrentDate(DateFormats.FORMAT_DATETIME_DD_MM_HH_MM_SS);
    assertEquals(expectedDateTime, response);
  }

  @Test
  void testFormatCurrentDate_DATETIME_ISO() throws DateTimeUtilException {
    String expectedDateTime =
        LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    String response = dateTimeUtils.formatCurrentDate(DateFormats.FORMAT_DATETIME_ISO);
    assertEquals(expectedDateTime, response);
  }

  @Test
  void testFormatCurrentDate_DATETIME_MM_DD_AM_PM() throws DateTimeUtilException {
    String expectedDateTime =
        LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM-dd-yyyy hh:mm a"));
    String response = dateTimeUtils.formatCurrentDate(DateFormats.FORMAT_DATETIME_MM_DD_AM_PM);
    assertEquals(expectedDateTime, response);
  }

  @Test
  void testFormatCurrentDate_YEAR_WEEK() throws DateTimeUtilException {
    String expectedYearWeek = LocalDate.now().format(DateTimeFormatter.ofPattern("YYYY-'W'ww-e"));
    String response = dateTimeUtils.formatCurrentDate(DateFormats.FORMAT_YEAR_WEEK);
    assertEquals(expectedYearWeek, response);
  }

  @Test
  void testFormatCurrentDate_FULL_DATE_DAY_OF_WEEK() throws DateTimeUtilException {
    String expectedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy"));
    String response = dateTimeUtils.formatCurrentDate(DateFormats.FORMAT_FULL_DATE_DAY_OF_WEEK);
    assertEquals(expectedDate, response);
  }

  @Test
  void testFormatCurrentDate_SHORT_DATE() throws DateTimeUtilException {
    String expectedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd/yy"));
    String response = dateTimeUtils.formatCurrentDate(DateFormats.FORMAT_SHORT_DATE);
    assertEquals(expectedDate, response);
  }

  @Test
  void testFormatCurrentDate_DATETIME_WITH_TZ() throws DateTimeUtilException {
    String expectedDateTime =
        ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssXXX"));
    String response = dateTimeUtils.formatCurrentDate(DateFormats.FORMAT_DATETIME_WITH_TZ);
    assertEquals(expectedDateTime, response);
  }

  @Test
  void testFormatCurrentDate_MONTH_YEAR() throws DateTimeUtilException {
    String expectedMonthYear = LocalDate.now().format(DateTimeFormatter.ofPattern("MMMM yyyy"));
    String response = dateTimeUtils.formatCurrentDate(DateFormats.FORMAT_MONTH_YEAR);
    assertEquals(expectedMonthYear, response);
  }
  @Test
  void testInvalidFormat() {
   final DateTimeUtilException exception =  assertThrows(DateTimeUtilException.class, () -> {
      dateTimeUtils.formatCurrentDate("yyy-mm");
    });
   assertTrue(exception.getMessage().contains("Invalid date format."));
  }
}
