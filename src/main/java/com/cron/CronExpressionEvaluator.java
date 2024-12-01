package com.cron;

public class CronExpressionEvaluator {

    public static CronExpression evalulateExpression(String expression) throws IllegalArgumentException {
        String[] params = splitAndValidate(expression);

        CronExpressionBuilderV2 cronExpressionBuilder = new CronExpressionBuilderV2();
        CronExpression cronExpression = cronExpressionBuilder
                .withMinute(params[0])
                .withHour(params[1])
                .withDayOfMonth(params[2])
                .withMonth(params[3])
                .withDayOfWeek(params[4])
                .withCommad(params[5])
                .build();

        return cronExpression;
    }

    private static String[] splitAndValidate(String expression) {
        if (expression == null || expression.isEmpty()) {
            throw new IllegalArgumentException("Input expression either empty or null");
        }

        String[] params = expression.split(" ");

        // Configure Nos of params
        if (params.length > 6) {
            throw new IllegalArgumentException("Additional Params provided");
        } else if (params.length < 6) {
            throw new IllegalArgumentException("One or more params missing");
        }

        return params;
    }
}
