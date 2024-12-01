package com.parser.impl;

import com.cron.CronParams;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class DivideParserTest {

    private final DivideParser divideParser = new DivideParser();

    @Test
    void testDivideParserWithStar() {
        CronParams cronParamType = CronParams.MINUTE;
        String param = "*/5";

        List<Integer> result = divideParser.getRange(param, cronParamType);

        assertEquals(12, result.size(), "There should be 12 numbers divisible by 5 between 0 and 59.");
        assertTrue(result.contains(5), "Range should include 5.");
        assertTrue(result.contains(10), "Range should include 10.");
        assertTrue(result.contains(55), "Range should include 55.");
    }

    @Test
    void testDivideParserWithSpecificValue() {
        // For DAY_OF_MONTH cron param, it should give every 3rd day starting from 3
        CronParams cronParamType = CronParams.DAY_OF_MONTH;
        String param = "3/3";

        List<Integer> result = divideParser.getRange(param, cronParamType);

        assertEquals(10, result.size(), "There should be 10 numbers divisible by 3 between 1 and 31.");
        assertTrue(result.contains(3), "Range should include 3.");
        assertTrue(result.contains(30), "Range should include 30.");
    }

    @Test
    void testDivideParserWithNonNumericInput() {
        CronParams cronParamType = CronParams.MINUTE;
        String param = "5/abc";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            divideParser.getRange(param, cronParamType);
        });

        assertTrue(exception.getMessage().contains("Not a number to parse in"), exception.getMessage());
    }

    @Test
    void testDivideParserNoMatchingValues() {
        CronParams cronParamType = CronParams.MINUTE;
        String param = "10/60";
        List<Integer> result = divideParser.getRange(param, cronParamType);
        assertTrue(result.isEmpty(), "Range should be empty as no multiples of 60 are within the minute range.");
    }
}
