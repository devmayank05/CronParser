package com.parser.impl;

import com.cron.CronParams;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RangeParserTest {

    @Test
    void testValidRange() {
        RangeParser rangeParser = new RangeParser();
        List<Integer> result = rangeParser.getRange("3-7", CronParams.HOUR);

        assertEquals(5, result.size());  // There should be 5 numbers (3, 4, 5, 6, 7)
        assertTrue(result.contains(3));
        assertTrue(result.contains(7));
    }

    @Test
    void testInvalidRangeFormat() {
        RangeParser rangeParser = new RangeParser();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            rangeParser.getRange("3-7-9", CronParams.HOUR);
        });

        assertTrue(exception.getMessage().contains("invalid param count to parse in"));
    }

    @Test
    void testOutOfRange() {
        RangeParser rangeParser = new RangeParser();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            rangeParser.getRange("6-8", CronParams.DAY_OF_WEEK);
        });

        assertTrue(exception.getMessage().contains("invalid range to parse in"));
    }

    @Test
    void testInvalidNumberFormat() {
        RangeParser rangeParser = new RangeParser();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            rangeParser.getRange("3-a", CronParams.HOUR);
        });

        assertTrue(exception.getMessage().contains("Not a number to parse in"));
    }
}
