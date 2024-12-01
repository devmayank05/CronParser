package com.parser.impl;

import com.cron.CronParams;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AsteriskParserTest {

    private final AsteriskParser asteriskParser = new AsteriskParser();

    @Test
    void testAsteriskParserValidRange() {
        CronParams cronParamType = CronParams.MINUTE;
        String param = "*";

        List<Integer> result = asteriskParser.getRange(param, cronParamType);

        assertEquals(60, result.size(), "The range should contain 60 minutes.");
        for (int i = 0; i < 60; i++) {
            assertTrue(result.contains(i), "Range should contain " + i + ".");
        }
    }

    @Test
    void testAsteriskParserDayOfMonth() {
        CronParams cronParamType = CronParams.DAY_OF_MONTH;
        String param = "*";

        List<Integer> result = asteriskParser.getRange(param, cronParamType);

        assertEquals(31, result.size(), "The range should contain 31 days.");
        for (int i = 1; i <= 31; i++) {
            assertTrue(result.contains(i), "Range should contain " + i + ".");
        }
    }

    @Test
    void testAsteriskParserMonth() {
        CronParams cronParamType = CronParams.MONTH;
        String param = "*";

        List<Integer> result = asteriskParser.getRange(param, cronParamType);

        assertEquals(12, result.size(), "The range should contain 12 months.");
        for (int i = 1; i <= 12; i++) {
            assertTrue(result.contains(i), "Range should contain " + i + ".");
        }
    }

    @Test
    void testAsteriskParserDayOfWeek() {
        CronParams cronParamType = CronParams.DAY_OF_WEEK;
        String param = "*";

        List<Integer> result = asteriskParser.getRange(param, cronParamType);

        assertEquals(7, result.size(), "The range should contain 7 days of the week.");
        assertTrue(result.contains(0), "Range should include 0 (Sunday).");
        assertTrue(result.contains(6), "Range should include 6 (Saturday).");
    }
}
