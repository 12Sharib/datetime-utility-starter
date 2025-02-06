package com.project.datetime_utility_starter.utils.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.project.datetime_utility_starter.TestBase;
import com.project.datetime_utility_starter.utils.exceptions.DateTimeUtilException;
import org.junit.jupiter.api.Test;

class CalculateWeekendsTest extends TestBase {

  @Test
  void testCalculateWeekends_ValidDateRange() throws DateTimeUtilException {
    Long result = dateTimeUtils.calculateWeekends("2025-01-01", "2025-01-14");
    assertEquals(4L, result);
  }

  @Test
  void testCalculateWeekends_SameDayWeekend() throws DateTimeUtilException {
    Long result = dateTimeUtils.calculateWeekends("2025-01-04", "2025-01-04");
    assertEquals(1L, result);
  }

  @Test
  void testCalculateWeekends_SameDayNonWeekend() throws DateTimeUtilException {
    Long result = dateTimeUtils.calculateWeekends("2025-01-03", "2025-01-03");
    assertEquals(0L, result);
  }

  @Test
  void testCalculateWeekends_EntireWeek() throws DateTimeUtilException {
    Long result = dateTimeUtils.calculateWeekends("2025-01-06", "2025-01-12");
    assertEquals(2L, result);
  }

  @Test
  void testCalculateWeekends_SameStartAndEndWeekend() throws DateTimeUtilException {
    Long result = dateTimeUtils.calculateWeekends("2025-01-05", "2025-01-05");
    assertEquals(1L, result);
  }

  @Test
  void testCalculateWeekends_SpanMultipleWeeks() throws DateTimeUtilException {
    Long result = dateTimeUtils.calculateWeekends("2025-01-01", "2025-01-31");
    assertEquals(8L, result);
  }

  @Test
  void testCalculateWeekends_OnlyWeekendsInRange() throws DateTimeUtilException {
    Long result = dateTimeUtils.calculateWeekends("2025-01-04", "2025-01-05");
    assertEquals(2L, result);
  }

  @Test
  void testCalculateWeekends_StartDateAfterEndDate() {
    DateTimeUtilException exception = assertThrows(DateTimeUtilException.class,
        () -> dateTimeUtils.calculateWeekends("2025-01-14", "2025-01-01"));
    assertEquals("Start date cannot be after end date.", exception.getMessage());
  }

  @Test
  void testCalculateWeekends_InvalidDateFormat() {
    DateTimeUtilException exception = assertThrows(DateTimeUtilException.class,
        () -> dateTimeUtils.calculateWeekends("14-01-2025", "01-01-2025"));
    assertEquals("Invalid date format. Expected format is yyyy-MM-dd.", exception.getMessage());
  }

  @Test
  void testCalculateWeekends_NullStartDate() {
    NullPointerException exception = assertThrows(NullPointerException.class,
        () -> dateTimeUtils.calculateWeekends(null, "2025-01-14"));
    assertNotNull(exception.getMessage());
  }

  @Test
  void testCalculateWeekends_NullEndDate() {
    NullPointerException exception = assertThrows(NullPointerException.class,
        () -> dateTimeUtils.calculateWeekends("2025-01-01", null));
    assertNotNull(exception.getMessage());
  }

  @Test
  void testCalculateWeekends_EmptyStartDate() {
    DateTimeUtilException exception = assertThrows(DateTimeUtilException.class,
        () -> dateTimeUtils.calculateWeekends("", "2025-01-14"));
    assertEquals("Invalid date format. Expected format is yyyy-MM-dd.", exception.getMessage());
  }

  @Test
  void testCalculateWeekends_EmptyEndDate() {
    DateTimeUtilException exception = assertThrows(DateTimeUtilException.class,
        () -> dateTimeUtils.calculateWeekends("2025-01-01", ""));
    assertEquals("Invalid date format. Expected format is yyyy-MM-dd.", exception.getMessage());
  }

  @Test
  void testCalculateWeekends_StartAndEndAreSameNonWeekendDay() throws DateTimeUtilException {
    Long result = dateTimeUtils.calculateWeekends("2025-01-06", "2025-01-06");
    assertEquals(0L, result);
  }

  @Test
  void testCalculateWeekends_SingleDaySaturday() throws DateTimeUtilException {
    Long result = dateTimeUtils.calculateWeekends("2025-01-11", "2025-01-11");
    assertEquals(1L, result);
  }

  @Test
  void testCalculateWeekends_SingleDaySunday() throws DateTimeUtilException {
    Long result = dateTimeUtils.calculateWeekends("2025-01-12", "2025-01-12");
    assertEquals(1L, result);
  }

}
