package com.project.datetime_utility_starter.utils.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.project.datetime_utility_starter.TestBase;
import com.project.datetime_utility_starter.utils.exceptions.DateTimeUtilException;
import org.junit.jupiter.api.Test;

class AddDaysToDateTest extends TestBase {

    @Test
    void testAddDaysToDatePositive() throws DateTimeUtilException {
        assertEquals("2025-02-15", dateTimeUtils.addDaysToDate("2025-02-10", 5));
    }

    @Test
    void testAddDaysToDateNegative() {
        assertThrows(Exception.class, () -> dateTimeUtils.addDaysToDate("invalid-date", 5));
    }

    @Test
    void testAddDaysToDateEdgeCaseLeapYear() throws DateTimeUtilException {
        assertEquals("2024-02-29", dateTimeUtils.addDaysToDate("2024-02-28", 1));
    }

    @Test
    void testAddDaysToDateEdgeCaseYearChange() throws DateTimeUtilException {
        assertEquals("2026-01-01", dateTimeUtils.addDaysToDate("2025-12-31", 1));
    }
}