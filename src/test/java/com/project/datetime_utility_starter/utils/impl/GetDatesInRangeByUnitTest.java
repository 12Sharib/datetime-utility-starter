package com.project.datetime_utility_starter.utils.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.project.datetime_utility_starter.TestBase;
import com.project.datetime_utility_starter.utils.exceptions.DateTimeUtilException;
import com.project.datetime_utility_starter.utils.formats.DateFormats;
import java.util.List;
import org.junit.jupiter.api.Test;

class GetDatesInRangeByUnitTest extends TestBase {
  @Test
  void testGetDatesInRangeByUnit_Days() throws DateTimeUtilException {
    String startDate = "01/01/2024";
    String endDate = "10/01/2024";

    List<String> actualDates =
        dateTimeUtils.getDatesInRangeByUnit(
            startDate, endDate, "days", DateFormats.FORMAT_CUSTOM_DD_MM_YYYY);

    assertEquals(10, actualDates.size());
  }

  @Test
  void testGetDatesInRangeByUnit_Weeks() throws DateTimeUtilException {
    String startDate = "2024-01-01";
    String endDate = "2024-01-20";

    List<String> actualWeeks =
        dateTimeUtils.getDatesInRangeByUnit(
            startDate, endDate, "weeks", DateFormats.FORMAT_ISO_DATE);

    assertEquals(3, actualWeeks.size());
  }

  // Test case for "months"
  @Test
  void testGetDatesInRangeByUnit_Months() throws DateTimeUtilException {
    String startDate = "2024-01-01";
    String endDate = "2024-09-05"; // Roughly 9 months difference

    List<String> actualMonths =
        dateTimeUtils.getDatesInRangeByUnit(
            startDate, endDate, "months", DateFormats.FORMAT_ISO_DATE);
    assertEquals(9, actualMonths.size()); // Expecting 9 months (2024-01, 2024-02, ... 2024-09)
  }

  // Test case for invalid start date format
  @Test
  void testGetDatesInRangeByUnit_InvalidStartDateFormat() {
    String startDate = "2024/01/01";
    String endDate = "2024-01-10";

    Exception exception =
        assertThrows(
            DateTimeUtilException.class,
            () -> {
              dateTimeUtils.getDatesInRangeByUnit(
                  startDate, endDate, "days", DateFormats.FORMAT_ISO_DATE);
            });

    assertTrue(exception.getMessage().contains("Invalid date format"));
  }

  // Test case for invalid end date format
  @Test
  void testGetDatesInRangeByUnit_InvalidEndDateFormat() {
    String startDate = "2024-01-01";
    String endDate = "2024/01/10";

    Exception exception =
        assertThrows(
            DateTimeUtilException.class,
            () -> {
              dateTimeUtils.getDatesInRangeByUnit(
                  startDate, endDate, "days", DateFormats.FORMAT_ISO_DATE);
            });

    assertTrue(exception.getMessage().contains("Invalid date format"));
  }

  // Test case for start date after end date
  @Test
  void testGetDatesInRangeByUnit_StartAfterEnd() {
    String startDate = "2024-01-10";
    String endDate = "2024-01-01";

    Exception exception =
        assertThrows(
            DateTimeUtilException.class,
            () -> {
              dateTimeUtils.getDatesInRangeByUnit(
                  startDate, endDate, "days", DateFormats.FORMAT_ISO_DATE);
            });

    assertTrue(exception.getMessage().contains("Start date must not be after the end date"));
  }

  // Test case for leap year handling (Feb 2024)
  @Test
  void testGetDatesInRangeByUnit_LeapYear() throws DateTimeUtilException {
    String startDate = "2024-02-01";
    String endDate = "2024-02-29";

    List<String> actualDates =
        dateTimeUtils.getDatesInRangeByUnit(
            startDate, endDate, "days", DateFormats.FORMAT_ISO_DATE);

    assertEquals(29, actualDates.size()); // Expecting 29 days in February 2024
  }

  // Test case for date range with one unit (e.g., 1 day)
  @Test
  void testGetDatesInRangeByUnit_SingleUnit() throws DateTimeUtilException {
    String startDate = "2024-01-01";
    String endDate = "2024-01-02";

    List<String> actualDates =
        dateTimeUtils.getDatesInRangeByUnit(
            startDate, endDate, "days", DateFormats.FORMAT_ISO_DATE);
    assertEquals(2, actualDates.size()); // Expecting only 1 day (2024-01-01)
  }

  // Test case for edge case where start and end date are the same
  @Test
  void testGetDatesInRangeByUnit_SameStartAndEndDate() throws DateTimeUtilException {
    String startDate = "2024-01-01";
    String endDate = "2024-01-01"; // Same date for both start and end

    List<String> actualDates =
        dateTimeUtils.getDatesInRangeByUnit(
            startDate, endDate, "days", DateFormats.FORMAT_ISO_DATE);

    assertEquals(1, actualDates.size()); // Expecting only 1 day (2024-01-01)
  }

  // Test case for invalid unit (should throw an exception)
  @Test
  void testGetDatesInRangeByUnit_InvalidUnit() {
    String startDate = "2024-01-01";
    String endDate = "2024-01-10";

    Exception exception =
        assertThrows(
            DateTimeUtilException.class,
            () -> {
              dateTimeUtils.getDatesInRangeByUnit(
                  startDate, endDate, "invalid_unit", DateFormats.FORMAT_ISO_DATE);
            });

    assertTrue(exception.getMessage().contains("Invalid unit. Use 'days', 'weeks', or 'months'"));
  }

  // Test case for custom date format (dd/MM/yyyy)
  @Test
  void testGetDatesInRangeByUnit_CustomFormat() throws DateTimeUtilException {
    String startDate = "01/01/2024";
    String endDate = "10/01/2024"; // 9 days difference

    List<String> actualDates =
        dateTimeUtils.getDatesInRangeByUnit(
            startDate, endDate, "days", DateFormats.FORMAT_CUSTOM_DD_MM_YYYY);

    assertEquals(10, actualDates.size());
  }

  @Test
  void testGetDatesInRangeByUnit_InvalidDateFormat() {
    String startDate = "2024-01-01";
    String endDate = "2024-01-10";
    String invalidFormat = "yyyy/MM/dd";

    Exception exception =
        assertThrows(
            DateTimeUtilException.class,
            () -> {
              dateTimeUtils.getDatesInRangeByUnit(startDate, endDate, "days", invalidFormat);
            });

    assertTrue(exception.getMessage().contains("Invalid date format"));
  }
}
