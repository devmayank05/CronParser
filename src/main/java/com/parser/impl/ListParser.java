package com.parser.impl;

import com.commons.Constants;
import com.cron.CronParams;
import com.parser.IParamParser;

import java.util.ArrayList;
import java.util.List;

public class ListParser implements IParamParser {

    @Override
    public List<Integer> getRange(String param, CronParams cronParamType) throws IllegalArgumentException {
        List<Integer> rangeOp = new ArrayList<>();

        String[] splitNos = param.split(",");

        if (splitNos.length == 0) {
            throw new IllegalArgumentException(String.format(Constants.INVALID_PARAM_COUNT,
                    ListParser.class.getName(),
                    cronParamType.getName()));
        }

        try {
            for (String stringNos : splitNos) {
                int nos = Integer.valueOf(stringNos.trim());
                if (nos < cronParamType.getMin() || nos > cronParamType.getMax()) {
                    throw new IllegalArgumentException(
                            String.format(Constants.INVALID_RANGE,
                                    ListParser.class.getName(),
                                    cronParamType.getName()));
                }

                rangeOp.add(nos);
            }
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException(
                    String.format(Constants.NO_NUMBER,
                            ListParser.class.getName(),
                            cronParamType.getName()));
        }

        return rangeOp;
    }
}
