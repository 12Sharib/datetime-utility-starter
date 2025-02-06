package com.project.datetime_utility_starter.utils.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.project.datetime_utility_starter.TestBase;
import com.project.datetime_utility_starter.utils.exceptions.DateTimeUtilException;
import org.junit.jupiter.api.Test;

class CalculateBusinessDaysTest extends TestBase {
  @Test
  void testCalculateBusinessDaysWithinSameWeek() throws DateTimeUtilException {
    assertEquals(3, dateTimeUtils.calculateBusinessDays("2025-01-08", "2025-01-12"));
  }

  @Test
  void testCalculateBusinessDaysWithWeekendSpan() throws DateTimeUtilException {
    assertEquals(5, dateTimeUtils.calculateBusinessDays("2025-01-05", "2025-01-10"));
  }

  @Test
  void testStartAndEndDatesSameWeekday() throws DateTimeUtilException {
    assertEquals(1, dateTimeUtils.calculateBusinessDays("2025-01-08", "2025-01-08"));
  }

  @Test
  void testStartAndEndDatesAreWeekends() throws DateTimeUtilException {
    assertEquals(0, dateTimeUtils.calculateBusinessDays("2025-01-11", "2025-01-11"));
  }

  @Test
  void testInvalidDateFormat() {
    assertThrows(DateTimeUtilException.class, () -> dateTimeUtils.calculateBusinessDays("08-01-2025", "2025-01-12"));
  }

  @Test
  void testStartDateAfterEndDate() {
    assertThrows(DateTimeUtilException.class, () -> dateTimeUtils.calculateBusinessDays("2025-01-12", "2025-01-08"));
  }

  @Test
  void testLeapYearScenario() throws DateTimeUtilException {
    assertEquals(4, dateTimeUtils.calculateBusinessDays("2024-02-27", "2024-03-01"));
  }

  @Test
  void testLargeDateRange() throws DateTimeUtilException {
    assertEquals(64, dateTimeUtils.calculateBusinessDays("2025-01-01", "2025-03-31")); // Example output.
  }
  @Test
  void testEmptyStringInputForStartDate() {
    assertThrows(DateTimeUtilException.class, () -> dateTimeUtils.calculateBusinessDays("", "2025-01-12"));
  }

  @Test
  void testEmptyStringInputForEndDate() {
    assertThrows(DateTimeUtilException.class, () -> dateTimeUtils.calculateBusinessDays("2025-01-08", ""));
  }

  @Test
  void testNullStartDate() {
    assertThrows(NullPointerException.class, () -> dateTimeUtils.calculateBusinessDays(null, "2025-01-12"));
  }

  @Test
  void testNullEndDate() {
    assertThrows(NullPointerException.class, () -> dateTimeUtils.calculateBusinessDays("2025-01-08", null));
  }

  @Test
  void testStartDateOnWeekendEndDateOnWeekday() throws DateTimeUtilException {
    assertEquals(3, dateTimeUtils.calculateBusinessDays("2025-01-11", "2025-01-15")); // Saturday to Wednesday
  }

  @Test
  void testStartDateOnWeekdayEndDateOnWeekend() throws DateTimeUtilException {
    assertEquals(3, dateTimeUtils.calculateBusinessDays("2025-01-08", "2025-01-11")); // Wednesday to Saturday
  }

  @Test
  void testStartDateAndEndDateAreOnAdjacentWeekdays() throws DateTimeUtilException {
    assertEquals(2, dateTimeUtils.calculateBusinessDays("2025-01-08", "2025-01-09")); // Wednesday to Thursday
  }

  @Test
  void testDateRangeEntirelyOverTheWeekend() throws DateTimeUtilException {
    assertEquals(0, dateTimeUtils.calculateBusinessDays("2025-01-11", "2025-01-12")); // Saturday to Sunday
  }

  @Test
  void testSingleDayOnAWeekend() throws DateTimeUtilException {
    assertEquals(0, dateTimeUtils.calculateBusinessDays("2025-01-11", "2025-01-11")); // Saturday only
  }


}
