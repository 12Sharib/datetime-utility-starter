package com.project.datetime_utility_starter.utils.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.project.datetime_utility_starter.TestBase;
import com.project.datetime_utility_starter.utils.exceptions.DateTimeUtilException;
import java.util.Map;
import org.junit.jupiter.api.Test;

class CalculateDaysMonthsYearsTest extends TestBase {

  @Test
  void testValidDatesWithYearsMonthsDays()
      throws DateTimeUtilException {
    Map<String, Long> result = dateTimeUtils.calculateDaysMonthsYears("2020-01-01", "2023-03-15");
    assertEquals(3, result.get("years"));
    assertEquals(2, result.get("months"));
    assertEquals(14, result.get("days"));
  }

  @Test
  void testValidDatesWithOnlyDaysDifference() throws DateTimeUtilException {
    Map<String, Long> result = dateTimeUtils.calculateDaysMonthsYears("2023-03-01", "2023-03-15");
    assertEquals(0, result.get("years"));
    assertEquals(0, result.get("months"));
    assertEquals(14, result.get("days"));
  }

  @Test
  void testValidDatesWithOnlyMonthsDifference() throws DateTimeUtilException {
    Map<String, Long> result = dateTimeUtils.calculateDaysMonthsYears("2023-01-01", "2023-03-01");
    assertEquals(0, result.get("years"));
    assertEquals(2, result.get("months"));
    assertEquals(0, result.get("days"));
  }

  @Test
  void testSameStartAndEndDate() throws DateTimeUtilException {
    Map<String, Long> result = dateTimeUtils.calculateDaysMonthsYears("2023-03-15", "2023-03-15");
    assertEquals(0, result.get("years"));
    assertEquals(0, result.get("months"));
    assertEquals(0, result.get("days"));
  }

  @Test
  void testInvalidDateFormat() {
    Exception exception = assertThrows(DateTimeUtilException.class,
        () -> {
          dateTimeUtils.calculateDaysMonthsYears("01-01-2023", "2023-03-15");
        });
    assertEquals("Invalid date format. Expected format is yyyy-MM-dd.", exception.getMessage());
  }

  @Test
  void testEmptyStartDate() {
    Exception exception = assertThrows(DateTimeUtilException.class,
        () -> {
          dateTimeUtils.calculateDaysMonthsYears("", "2023-03-15");
        });
    assertEquals("Invalid date format. Expected format is yyyy-MM-dd.", exception.getMessage());
  }

  @Test
  void testEmptyEndDate() {
    Exception exception = assertThrows(DateTimeUtilException.class,
        () -> {
          dateTimeUtils.calculateDaysMonthsYears("2023-03-15", "");
        });
    assertEquals("Invalid date format. Expected format is yyyy-MM-dd.", exception.getMessage());
  }

  @Test
  void testStartDateAfterEndDate() {
    Exception exception = assertThrows(DateTimeUtilException.class,
        () -> {
          dateTimeUtils.calculateDaysMonthsYears("2023-03-15", "2020-01-01");
        });
    assertEquals("Start date cannot be after end date.", exception.getMessage());
  }
}
