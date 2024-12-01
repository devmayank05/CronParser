package com.cron;


public class CronExpressionBuilder {

    private CronExpression expression = new CronExpression();

    public CronExpressionBuilder withMinute(String minute) {
        expression.getCronParams().put(CronParams.MINUTE.name(), new CronParam(minute, CronParams.MINUTE));
        return this;
    }

    public CronExpressionBuilder withHour(String hour) {
        expression.getCronParams().put(CronParams.HOUR.name(), new CronParam(hour, CronParams.HOUR));
        return this;
    }

    public CronExpressionBuilder withDayOfMonth(String dayOfMonth) {
        expression.getCronParams().put(CronParams.DAY_OF_MONTH.name(), new CronParam(dayOfMonth, CronParams.DAY_OF_MONTH));
        return this;
    }

    public CronExpressionBuilder withMonth(String month) {
        expression.getCronParams().put(CronParams.MONTH.name(), new CronParam(month, CronParams.MONTH));
        return this;
    }

    public CronExpressionBuilder withDayOfWeek(String dayOfWeek) {
        expression.getCronParams().put(CronParams.DAY_OF_WEEK.name(), new CronParam(dayOfWeek, CronParams.DAY_OF_WEEK));
        return this;
    }

    public CronExpressionBuilder withCommad(String command) {
        expression.setCommand(command);
        return this;
    }

    public CronExpression build() {
        return expression;
    }
}
