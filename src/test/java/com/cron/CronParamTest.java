package com.cron;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CronParamTest {

    @Test
    void testValidRange() {
        CronParams cronParamType = CronParams.MINUTE;
        String param = "30"; // Valid minute value

        CronParam cronParam = new CronParam(param, cronParamType);

        assertEquals(1, cronParam.getOutputRange().size(), "Output range should contain 1 value.");
        assertTrue(cronParam.getOutputRange().contains(30), "Output range should contain 30.");
    }

    @Test
    void testValidAsteriskRange() {
        CronParams cronParamType = CronParams.MINUTE;
        String param = "*"; // Asterisk input

        CronParam cronParam = new CronParam(param, cronParamType);

        assertEquals(60, cronParam.getOutputRange().size(), "Output range should contain 60 minutes.");
    }

    @Test
    void testInvalidRangeValue() {
        CronParams cronParamType = CronParams.MINUTE;
        String param = "100"; // Invalid minute value (out of range)

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new CronParam(param, cronParamType);
        });

        assertTrue(exception.getMessage().contains("illegal Param"), "Range validation without parser");
    }

    @Test
    void testInvalidRangeValueWithParser() {
        CronParams cronParamType = CronParams.MINUTE;
        String param = "30-80"; // Invalid minute value (out of range)

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new CronParam(param, cronParamType);
        });

        assertTrue(exception.getMessage().contains("invalid range to parse in"), "Range validation with parser");
    }

    @Test
    void testEmptyInput() {
        CronParams cronParamType = CronParams.MINUTE;
        String param = ""; // Empty string

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new CronParam(param, cronParamType);
        });

        assertEquals("MINUTE null or empty param passed", exception.getMessage());
    }

    @Test
    void testNullInput() {
        CronParams cronParamType = CronParams.MINUTE;
        String param = null; // Null input

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new CronParam(param, cronParamType);
        });

        assertEquals("MINUTE null or empty param passed", exception.getMessage());
    }
}
