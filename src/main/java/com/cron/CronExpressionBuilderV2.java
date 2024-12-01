package com.cron;


public class CronExpressionBuilderV2 {

    private CronExpression expression = new CronExpression();

    public CronExpressionBuilderV2 withMinute(String minute) {
        expression.setMinute(new CronParam(minute, CronParams.MINUTE));
        return this;
    }

    public CronExpressionBuilderV2 withHour(String hour) {
        expression.setHour(new CronParam(hour, CronParams.HOUR));
        return this;
    }

    public CronExpressionBuilderV2 withDayOfMonth(String dayOfMonth) {
        expression.setDayOfMonth(new CronParam(dayOfMonth, CronParams.DAY_OF_MONTH));
        return this;
    }

    public CronExpressionBuilderV2 withMonth(String month) {
        expression.setMonth(new CronParam(month, CronParams.MONTH));
        return this;
    }

    public CronExpressionBuilderV2 withDayOfWeek(String dayOfWeek) {
        expression.setDayOfWeek(new CronParam(dayOfWeek, CronParams.DAY_OF_WEEK));
        return this;
    }

    public CronExpressionBuilderV2 withCommad(String command) {
        expression.setCommand(command);
        return this;
    }

    public CronExpression build() {
        return expression;
    }
}
