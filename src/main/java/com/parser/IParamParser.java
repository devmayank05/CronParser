package com.parser;

import com.cron.CronParams;

import java.util.List;

/**
 * This interface hold one api which take string param as input
 * and will return list of range
 */
public interface IParamParser {

    /**
     * @param param         any string param passed in input
     * @param cronParamType To have asteric min and max range
     * @return list of Evaluated ranges
     */
    List<Integer> getRange(String param, CronParams cronParamType) throws IllegalArgumentException;
}
