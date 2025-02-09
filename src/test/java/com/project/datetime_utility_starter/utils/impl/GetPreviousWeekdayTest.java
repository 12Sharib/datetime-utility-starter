package com.project.datetime_utility_starter.utils.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.project.datetime_utility_starter.TestBase;
import com.project.datetime_utility_starter.utils.exceptions.DateTimeUtilException;
import java.time.DayOfWeek;
import org.junit.jupiter.api.Test;

class GetPreviousWeekdayTest extends TestBase {

    @Test
    void testGetPreviousWeekdayPositive() throws DateTimeUtilException {
        assertEquals("2025-02-03", dateTimeUtils.getPreviousWeekday("2025-02-10", DayOfWeek.MONDAY));
    }

    @Test
    void testGetPreviousWeekdayNegative() {
        assertThrows(Exception.class, () -> dateTimeUtils.getPreviousWeekday("invalid-date", DayOfWeek.MONDAY));
    }

    @Test
    void testGetPreviousWeekdayEdgeCaseWeekend() throws DateTimeUtilException {
        assertEquals("2025-02-07", dateTimeUtils.getPreviousWeekday("2025-02-10", DayOfWeek.FRIDAY));
    }
}