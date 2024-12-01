package com.cron;

public enum CronParams {
    MINUTE("Minute", 0, 59),
    HOUR("Hour", 0, 23),
    DAY_OF_MONTH("Day of Month", 1, 31),
    MONTH("Month", 1, 12),
    DAY_OF_WEEK("Day of Week", 0, 6); // 0 is Sunday, 6 is Saturday

    private final String name;
    private final int min;
    private final int max;

    // Constructor to set the values for each constant
    CronParams(String name, int min, int max) {
        this.name = name;
        this.min = min;
        this.max = max;
    }

    // Getters for name, min, and max
    public String getName() {
        return name;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }
}
