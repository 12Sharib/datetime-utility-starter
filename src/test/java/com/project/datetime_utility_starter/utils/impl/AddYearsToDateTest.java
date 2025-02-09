package com.project.datetime_utility_starter.utils.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.project.datetime_utility_starter.TestBase;
import com.project.datetime_utility_starter.utils.exceptions.DateTimeUtilException;
import org.junit.jupiter.api.Test;

class AddYearsToDateTest extends TestBase {

  @Test
  void testAddYearsToDatePositive() throws DateTimeUtilException {
    assertEquals("2028-02-10", dateTimeUtils.addYearsToDate("2025-02-10", 3));
  }

  @Test
  void testAddYearsToDateNegative() {
    assertThrows(Exception.class, () -> dateTimeUtils.addYearsToDate("invalid-date", 3));
  }

  @Test
  void testAddYearsToDateEdgeCaseLeapYear() throws DateTimeUtilException {
    assertEquals("2028-02-29", dateTimeUtils.addYearsToDate("2024-02-29", 4));
  }
}