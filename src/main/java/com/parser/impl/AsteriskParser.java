package com.parser.impl;

import com.cron.CronParams;
import com.parser.IParamParser;

import java.util.ArrayList;
import java.util.List;

public class AsteriskParser implements IParamParser {

    @Override
    public List<Integer> getRange(String param, CronParams cronParamType) throws IllegalArgumentException {
        List<Integer> rangeOp = new ArrayList<>();
        for (int i = cronParamType.getMin(); i <= cronParamType.getMax(); i++) {
            rangeOp.add(i);
        }

        return rangeOp;
    }
}
