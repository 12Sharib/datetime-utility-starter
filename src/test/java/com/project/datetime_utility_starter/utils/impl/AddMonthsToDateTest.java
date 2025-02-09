package com.project.datetime_utility_starter.utils.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.project.datetime_utility_starter.TestBase;
import com.project.datetime_utility_starter.utils.exceptions.DateTimeUtilException;
import org.junit.jupiter.api.Test;

class AddMonthsToDateTest extends TestBase {

  @Test
  void testAddMonthsToDatePositive() throws DateTimeUtilException {
    assertEquals("2025-05-10", dateTimeUtils.addMonthsToDate("2025-02-10", 3));
  }

  @Test
  void testAddMonthsToDateNegative() {
    assertThrows(Exception.class, () -> dateTimeUtils.addMonthsToDate("invalid-date", 3));
  }

  @Test
  void testAddMonthsToDateEdgeCaseEndOfMonth() throws DateTimeUtilException {
    assertEquals("2025-04-30", dateTimeUtils.addMonthsToDate("2025-01-31", 3));
  }
}