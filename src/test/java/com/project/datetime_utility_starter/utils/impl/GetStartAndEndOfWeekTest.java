package com.project.datetime_utility_starter.utils.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.project.datetime_utility_starter.TestBase;
import com.project.datetime_utility_starter.utils.exceptions.DateTimeUtilException;
import org.junit.jupiter.api.Test;

class GetStartAndEndOfWeekTest extends TestBase {

    @Test
    void testGetStartOfWeekPositive() throws DateTimeUtilException {
        assertEquals("2025-02-03", dateTimeUtils.getStartOfWeek("2025-02-06"));
    }

    @Test
    void testGetEndOfWeekPositive() throws DateTimeUtilException {
        assertEquals("2025-02-09", dateTimeUtils.getEndOfWeek("2025-02-06"));
    }

    @Test
    void testGetStartAndEndOfWeekNegative() {
        assertThrows(Exception.class, () -> dateTimeUtils.getStartOfWeek("invalid-date"));
        assertThrows(Exception.class, () -> dateTimeUtils.getEndOfWeek("invalid-date"));
    }
}