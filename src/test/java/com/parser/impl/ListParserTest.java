package com.parser.impl;

import com.cron.CronParams;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ListParserTest {

    private final ListParser listParser = new ListParser();

    @Test
    void testValidListInput() {
        CronParams cronParamType = CronParams.MINUTE;
        String param = "5,10,15,20";

        List<Integer> result = listParser.getRange(param, cronParamType);

        assertEquals(4, result.size(), "There should be 4 values in the range.");
        assertTrue(result.contains(5), "List should contain 5.");
        assertTrue(result.contains(10), "List should contain 10.");
        assertTrue(result.contains(15), "List should contain 15.");
        assertTrue(result.contains(20), "List should contain 20.");
    }

    @Test
    void testInvalidNumberFormat() {
        CronParams cronParamType = CronParams.MINUTE;
        String param = "5,ten,15";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            listParser.getRange(param, cronParamType);
        });

        assertTrue(exception.getMessage().contains("Not a number to parse in"), exception.getMessage());
    }

    @Test
    void testOutOfRangeValue() {
        CronParams cronParamType = CronParams.MINUTE;
        String param = "5,60,15";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            listParser.getRange(param, cronParamType);
        });

        assertTrue(exception.getMessage().contains("invalid range to parse in"), exception.getMessage());
    }

    @Test
    void testNoValuesInList() {
        CronParams cronParamType = CronParams.MINUTE;
        String param = ",,,";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            listParser.getRange(param, cronParamType);
        });

        assertTrue(exception.getMessage().contains("invalid param count to parse in"), exception.getMessage());
    }

    @Test
    void testBoundaryValues() {
        CronParams cronParamType = CronParams.MINUTE;
        String param = "0,59";

        List<Integer> result = listParser.getRange(param, cronParamType);

        assertEquals(2, result.size(), "There should be 2 values in the range.");
        assertTrue(result.contains(0), "List should contain 0.");
        assertTrue(result.contains(59), "List should contain 59.");
    }
}
