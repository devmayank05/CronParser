package com.parser.impl;

import com.commons.Constants;
import com.cron.CronParams;
import com.parser.IParamParser;

import java.util.ArrayList;
import java.util.List;

public class DivideParser implements IParamParser {

    @Override
    public List<Integer> getRange(String param, CronParams cronParamType) throws IllegalArgumentException {
        List<Integer> rangeOp = new ArrayList<>();
        String[] splitNos = param.split("/");
        if (splitNos.length != 2) {
            throw new IllegalArgumentException(
                    String.format(Constants.INVALID_PARAM_COUNT,
                            DivideParser.class.getName(),
                            cronParamType.getName()));
        }

        try {
            int min = "*".equals(splitNos[0].trim()) ? cronParamType.getMin() : Integer.valueOf(splitNos[0]);
            int divide = Integer.valueOf(splitNos[1].trim());

            if (min < cronParamType.getMin()) {
                throw new IllegalArgumentException(
                        String.format(Constants.INVALID_RANGE,
                                DivideParser.class.getName(),
                                cronParamType.getName()));
            }

            for (int i = min; i <= cronParamType.getMax(); i++) {
                if (i % divide == 0) {
                    rangeOp.add(i);
                }
            }

        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException(
                    String.format(Constants.NO_NUMBER,
                            DivideParser.class.getName(),
                            cronParamType.getName()));
        }

        return rangeOp;
    }
}
