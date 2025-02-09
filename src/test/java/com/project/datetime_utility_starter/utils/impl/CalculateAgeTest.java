package com.project.datetime_utility_starter.utils.impl;

import static org.junit.jupiter.api.Assertions.*;

import com.project.datetime_utility_starter.TestBase;
import com.project.datetime_utility_starter.utils.exceptions.DateTimeUtilException;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

class CalculateAgeTest extends TestBase {


  @Test
  void testCalculateAge_Positive() throws DateTimeUtilException {
    assertEquals(26, dateTimeUtils.calculateAge("1999-02-08"));
  }

  @Test
  void testCalculateAge_Boundary_JustBorn() throws DateTimeUtilException {
    assertEquals(0, dateTimeUtils.calculateAge(LocalDate.now().toString()));
  }

  @Test
  void testCalculateAge_Boundary_OneDayOld() throws DateTimeUtilException {
    LocalDate yesterday = LocalDate.now().minusDays(1);
    assertEquals(0, dateTimeUtils.calculateAge(yesterday.toString()));
  }

  @Test
  void testCalculateAge_CenturyOld() throws DateTimeUtilException {
    assertEquals(101, dateTimeUtils.calculateAge("1924-02-08"));
  }

  @Test
  void testCalculateAge_Negative_FutureDate() {
    assertThrows(DateTimeUtilException.class, () -> dateTimeUtils.calculateAge("2099-01-01"));
  }

  @Test
  void testCalculateAge_Negative_InvalidFormat() {
    assertThrows(DateTimeUtilException.class, () -> dateTimeUtils.calculateAge("08-02-1999"));
  }

  @Test
  void testCalculateAge_Negative_EmptyInput() {
    assertThrows(DateTimeUtilException.class, () -> dateTimeUtils.calculateAge(""));
  }
}
