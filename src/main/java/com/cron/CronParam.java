package com.cron;

import com.commons.Constants;
import com.parser.IParamParser;
import com.parser.ParamParserFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CronParam {

    private String name;
    private List<Integer> outputRange;

    public CronParam(String param, CronParams paramType) {
        if (param == null || param.isEmpty()) {
            throw new IllegalArgumentException(paramType.name() + " null or empty param passed");
        }

        IParamParser parser = ParamParserFactory.getParamParser(param);
        if (parser != null) {
            outputRange = parser.getRange(param, paramType);
        } else {
            int value = 0;
            try {
                value = Integer.valueOf(param);

                if (value < paramType.getMin() || value > paramType.getMax()) {
                    throw new IllegalArgumentException(paramType.name());
                }
            } catch (Exception ex) {
                throw new IllegalArgumentException(String.format(Constants.ILLEGAL_PARAM, paramType.getName()));
            }

            outputRange = new ArrayList<>();
            outputRange.add(value);
        }

        this.name = paramType.name();
    }

    public List<Integer> getOutputRange() {
        return outputRange;
    }

    @Override
    public String toString() {
        return name + " : " + outputRange
                .stream()
                .map(String::valueOf)
                .collect(Collectors.joining(" "));
    }
}
