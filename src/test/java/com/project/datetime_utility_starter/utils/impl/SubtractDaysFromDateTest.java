package com.project.datetime_utility_starter.utils.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.project.datetime_utility_starter.TestBase;
import com.project.datetime_utility_starter.utils.exceptions.DateTimeUtilException;
import org.junit.jupiter.api.Test;

class SubtractDaysFromDateTest extends TestBase {
    @Test
    void testSubtractDaysToDatePositive() throws DateTimeUtilException {
        assertEquals("2025-02-05", dateTimeUtils.subtractDaysFromDate("2025-02-10", 5));
    }

    @Test
    void testSubtractDaysToDateNegative() {
        assertThrows(Exception.class, () -> dateTimeUtils.subtractDaysFromDate("invalid-date", 5));
    }

    @Test
    void testSubtractDaysToDateEdgeCaseLeapYear() throws DateTimeUtilException {
        assertEquals("2024-02-28", dateTimeUtils.subtractDaysFromDate("2024-02-29", 1));
    }
}