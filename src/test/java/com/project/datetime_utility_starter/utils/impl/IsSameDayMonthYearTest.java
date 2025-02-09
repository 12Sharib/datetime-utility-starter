package com.project.datetime_utility_starter.utils.impl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.project.datetime_utility_starter.TestBase;
import com.project.datetime_utility_starter.utils.exceptions.DateTimeUtilException;
import org.junit.jupiter.api.Test;

class IsSameDayMonthYearTest extends TestBase {

  @Test
  void testIsSameDay_Positive() throws DateTimeUtilException {
    assertTrue(dateTimeUtils.isSameDay("2025-02-08", "2025-02-08"));
  }

  @Test
  void testIsSameDay_Negative_DifferentDay() throws DateTimeUtilException {
    assertFalse(dateTimeUtils.isSameDay("2025-02-08", "2025-02-09"));
  }

  @Test
  void testIsSameDay_Negative_DifferentMonth() throws DateTimeUtilException {
    assertFalse(dateTimeUtils.isSameDay("2025-02-08", "2025-03-08"));
  }

  @Test
  void testIsSameDay_Negative_DifferentYear() throws DateTimeUtilException {
    assertFalse(dateTimeUtils.isSameDay("2025-02-08", "2024-02-08"));
  }

  @Test
  void testIsSameDay_Boundary_LeapYear() throws DateTimeUtilException {
    assertTrue(dateTimeUtils.isSameDay("2024-02-29", "2024-02-29"));
  }

  @Test
  void testIsSameDay_Negative_InvalidFormat() {
    assertThrows(DateTimeUtilException.class,
        () -> dateTimeUtils.isSameDay("08-02-2025", "2025-02-08"));
  }

  @Test
  void testIsSameDay_Negative_EmptyInput() {
    assertThrows(DateTimeUtilException.class, () -> dateTimeUtils.isSameDay("", "2025-02-08"));
  }


  @Test
  void testIsSameMonth_Positive() throws DateTimeUtilException {
    assertTrue(dateTimeUtils.isSameMonth("2025-02-01", "2025-02-28"));
  }

  @Test
  void testIsSameMonth_Negative_DifferentMonth() throws DateTimeUtilException {
    assertFalse(dateTimeUtils.isSameMonth("2025-02-08", "2025-03-08"));
  }

  @Test
  void testIsSameMonth_Negative_DifferentYear() throws DateTimeUtilException {
    assertFalse(dateTimeUtils.isSameMonth("2025-02-08", "2024-02-08"));
  }

  @Test
  void testIsSameMonth_Boundary_LeapYear() throws DateTimeUtilException {
    assertTrue(dateTimeUtils.isSameMonth("2024-02-01", "2024-02-29"));
  }

  @Test
  void testIsSameMonth_Boundary_DecemberToJanuary() throws DateTimeUtilException {
    assertFalse(dateTimeUtils.isSameMonth("2024-12-31", "2025-01-01"));
  }

  @Test
  void testIsSameMonth_Negative_InvalidFormat() {
    assertThrows(DateTimeUtilException.class,
        () -> dateTimeUtils.isSameMonth("08-02-2025", "2025-02-08"));
  }

  @Test
  void testIsSameMonth_Negative_EmptyInput() {
    assertThrows(DateTimeUtilException.class, () -> dateTimeUtils.isSameMonth("", "2025-02-08"));
  }


  @Test
  void testIsSameYear_Positive() throws DateTimeUtilException {
    assertTrue(dateTimeUtils.isSameYear("2025-02-08", "2025-12-25"));
  }

  @Test
  void testIsSameYear_Negative_DifferentYear() throws DateTimeUtilException {
    assertFalse(dateTimeUtils.isSameYear("2025-02-08", "2024-02-08"));
  }

  @Test
  void testIsSameYear_Boundary_EndOfYear() throws DateTimeUtilException {
    assertTrue(dateTimeUtils.isSameYear("2025-01-01", "2025-12-31"));
  }

  @Test
  void testIsSameYear_Boundary_DecemberToJanuary() throws DateTimeUtilException {
    assertFalse(dateTimeUtils.isSameYear("2024-12-31", "2025-01-01"));
  }

  @Test
  void testIsSameYear_Negative_InvalidFormat() {
    assertThrows(DateTimeUtilException.class,
        () -> dateTimeUtils.isSameYear("08-02-2025", "2025-02-08"));
  }

  @Test
  void testIsSameYear_Negative_EmptyInput() {
    assertThrows(DateTimeUtilException.class, () -> dateTimeUtils.isSameYear("", "2025-02-08"));
  }
}
