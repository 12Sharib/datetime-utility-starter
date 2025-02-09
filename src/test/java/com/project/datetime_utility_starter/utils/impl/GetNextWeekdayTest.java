package com.project.datetime_utility_starter.utils.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.project.datetime_utility_starter.TestBase;
import com.project.datetime_utility_starter.utils.exceptions.DateTimeUtilException;
import java.time.DayOfWeek;
import org.junit.jupiter.api.Test;

class GetNextWeekdayTest extends TestBase {

    @Test
    void testGetNextWeekdayPositive() throws DateTimeUtilException {
        assertEquals("2025-02-17", dateTimeUtils.getNextWeekday("2025-02-10", DayOfWeek.MONDAY));
    }

    @Test
    void testGetNextWeekdayNegative() {
        assertThrows(Exception.class, () -> dateTimeUtils.getNextWeekday("invalid-date", DayOfWeek.MONDAY));
    }

    @Test
    void testGetNextWeekdayEdgeCaseWeekend() throws DateTimeUtilException {
        assertEquals("2025-02-10", dateTimeUtils.getNextWeekday("2025-02-08", DayOfWeek.MONDAY));
    }
}