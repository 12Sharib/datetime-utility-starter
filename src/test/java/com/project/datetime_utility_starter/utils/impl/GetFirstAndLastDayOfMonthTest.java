package com.project.datetime_utility_starter.utils.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.project.datetime_utility_starter.TestBase;
import com.project.datetime_utility_starter.utils.exceptions.DateTimeUtilException;
import org.junit.jupiter.api.Test;

class GetFirstAndLastDayOfMonthTest extends TestBase {

  @Test
  void testGetFirstDayOfMonthPositive() throws DateTimeUtilException {
    assertEquals("2025-02-01", dateTimeUtils.getFirstDayOfMonth("2025-02-10"));
  }

  @Test
  void testGetLastDayOfMonthPositive() throws DateTimeUtilException {
    assertEquals("2025-02-28", dateTimeUtils.getLastDayOfMonth("2025-02-10"));
  }

  @Test
  void testGetFirstAndLastDayOfMonthNegative() {
    assertThrows(Exception.class, () -> dateTimeUtils.getFirstDayOfMonth("invalid-date"));
    assertThrows(Exception.class, () -> dateTimeUtils.getLastDayOfMonth("invalid-date"));
  }
}