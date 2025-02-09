package com.project.datetime_utility_starter.utils.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.project.datetime_utility_starter.TestBase;
import com.project.datetime_utility_starter.utils.exceptions.DateTimeUtilException;
import org.junit.jupiter.api.Test;

class GetFirstAndLastDayOfYearTest extends TestBase {

  @Test
  void testGetFirstDayOfYearPositive() throws DateTimeUtilException {
    assertEquals("2025-01-01", dateTimeUtils.getFirstDayOfYear("2025-06-15"));
  }

  @Test
  void testGetLastDayOfYearPositive() throws DateTimeUtilException {
    assertEquals("2025-12-31", dateTimeUtils.getLastDayOfYear("2025-06-15"));
  }

  @Test
  void testGetFirstAndLastDayOfYearNegative() {
    assertThrows(Exception.class, () -> dateTimeUtils.getFirstDayOfYear("invalid-date"));
    assertThrows(Exception.class, () -> dateTimeUtils.getLastDayOfYear("invalid-date"));
  }
}
