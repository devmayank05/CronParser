package com.cron;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CronExpression {

    @Deprecated
    private Map<String, CronParam> cronParams = new HashMap<>();

    private CronParam Minute;
    private CronParam Hour;
    private CronParam DayOfMonth;
    private CronParam Month;
    private CronParam DayOfWeek;
    private String command;

    /**
     * either we keep collection of param where we dont have control of CronParams
     * Or
     * We keep it as tight bound class
     * As given in description in document i choose to go with tight bound class here
     *
     * @return dynamic params
     */
    public Map<String, CronParam> getCronParams() {
        return cronParams;
    }

    public CronParam getMinute() {
        return Minute;
    }

    public void setMinute(CronParam minute) {
        Minute = minute;
    }

    public CronParam getHour() {
        return Hour;
    }

    public void setHour(CronParam hour) {
        Hour = hour;
    }

    public CronParam getDayOfWeek() {
        return DayOfWeek;
    }

    public void setDayOfWeek(CronParam dayOfWeek) {
        DayOfWeek = dayOfWeek;
    }

    public CronParam getDayOfMonth() {
        return DayOfMonth;
    }

    public void setDayOfMonth(CronParam dayOfMonth) {
        DayOfMonth = dayOfMonth;
    }

    public CronParam getMonth() {
        return Month;
    }

    public void setMonth(CronParam month) {
        Month = month;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getMinute() + "\n")
                .append(getHour() + "\n")
                .append(getDayOfMonth() + "\n")
                .append(getMonth() + "\n")
                .append(getDayOfWeek() + "\n")
                .append(getCommand());
        return sb.toString();
    }
}
