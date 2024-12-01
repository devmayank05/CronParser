package com.parser.impl;

import com.commons.Constants;
import com.cron.CronParams;
import com.parser.IParamParser;

import java.util.ArrayList;
import java.util.List;

public class RangeParser implements IParamParser {

    @Override
    public List<Integer> getRange(String param, CronParams cronParamType) throws IllegalArgumentException {
        // Validate range format and parameters early
        String[] range = param.split("-");
        if (range.length != 2) {
            throw new IllegalArgumentException(String.format(Constants.INVALID_PARAM_COUNT,
                    RangeParser.class.getName(),
                    cronParamType.getName()));
        }

        int min;
        int max;
        try {
            min = Integer.parseInt(range[0]);
            max = Integer.parseInt(range[1]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    String.format(Constants.NO_NUMBER,
                            RangeParser.class.getName(),
                            cronParamType.getName()));
        }

        // Validate the parsed range values
        if (min < cronParamType.getMin() || max > cronParamType.getMax() || min > max) {
            throw new IllegalArgumentException(
                    String.format(Constants.INVALID_RANGE,
                            RangeParser.class.getName(),
                            cronParamType.getName()));
        }

        // Generate the range
        List<Integer> rangeOp = new ArrayList<>();
        for (int i = min; i <= max; i++) {
            rangeOp.add(i);
        }

        return rangeOp;
    }
}