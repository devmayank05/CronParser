package com.cron;

import com.parser.IParamParser;
import com.parser.impl.AsteriskParser;
import com.parser.impl.DivideParser;
import com.parser.impl.ListParser;
import com.parser.impl.RangeParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CronExpressionEvaluatorTest {
    IParamParser divideParser = new DivideParser();
    IParamParser listParser = new ListParser();
    IParamParser rangeParser = new RangeParser();
    IParamParser astericParser = new AsteriskParser();

    @Test
    void testValidExpression() {
        String expression = "30 12 5 8 2 *"; // valid expression
        CronExpression cronExpression = CronExpressionEvaluator.evalulateExpression(expression);

        assertNotNull(cronExpression, "CronExpression should not be null.");
        // Assuming cronExpression has a method to get the minute value.
        assertEquals(30, cronExpression.getMinute().getOutputRange().get(0), "The minute value should be 30.");
        assertEquals(12, cronExpression.getHour().getOutputRange().get(0), "The hour value should be 12.");
        assertEquals(5, cronExpression.getDayOfMonth().getOutputRange().get(0), "The day of the month value should be 5.");
        assertEquals(8, cronExpression.getMonth().getOutputRange().get(0), "The month value should be 8.");
        assertEquals(2, cronExpression.getDayOfWeek().getOutputRange().get(0), "The day of the week value should be 2.");
    }

    @Test
    void testValidExpressionWithStepAndRange() {
        String expression = "*/15 0 1,15 * 1-5 /usr/bin/find"; // valid cron expression

        CronExpression cronExpression = CronExpressionEvaluator.evalulateExpression(expression);
        assertNotNull(cronExpression, "CronExpression should not be null.");
        assertEquals(divideParser.getRange("*/15", CronParams.MINUTE).size(), cronExpression.getMinute().getOutputRange().size());
        assertEquals(1, cronExpression.getHour().getOutputRange().size(), "The hour value should be 0");
        assertEquals(listParser.getRange("1,15", CronParams.DAY_OF_MONTH).size(), cronExpression.getDayOfMonth().getOutputRange().size(), "Day of Month should contain 1.");
        assertEquals(astericParser.getRange("*", CronParams.MONTH).size(), cronExpression.getMonth().getOutputRange().size(), "Day of Month should contain 15.");
        assertEquals(rangeParser.getRange("1-5", CronParams.DAY_OF_WEEK).size(), cronExpression.getDayOfWeek().getOutputRange().size(), "Day of Week should contain Monday.");
    }

    @Test
    void testNullExpression() {
        String expression = null; // null input

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            CronExpressionEvaluator.evalulateExpression(expression);
        });

        assertEquals("Input expression either empty or null", exception.getMessage());
    }

    @Test
    void testEmptyExpression() {
        String expression = ""; // empty input

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            CronExpressionEvaluator.evalulateExpression(expression);
        });

        assertEquals("Input expression either empty or null", exception.getMessage());
    }

    @Test
    void testExpressionWithMissingParameters() {
        String expression = "30 12 5 8"; // Missing one parameter (day of week)

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            CronExpressionEvaluator.evalulateExpression(expression);
        });

        assertEquals("One or more params missing", exception.getMessage());
    }

    @Test
    void testExpressionWithExtraParameters() {
        String expression = "30 12 5 8 2 * extra"; // Extra parameter provided

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            CronExpressionEvaluator.evalulateExpression(expression);
        });

        assertEquals("Additional Params provided", exception.getMessage());
    }

    @Test
    void testExpressionWithInvalidParameter() {
        String expression = "30 12 invalid 8 2 *"; // Invalid day of month

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            CronExpressionEvaluator.evalulateExpression(expression);
        });

        assertTrue(exception.getMessage().contains("illegal Param"), "Expected exception for invalid parameter.");
    }
}
