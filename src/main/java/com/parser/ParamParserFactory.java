package com.parser;

import com.parser.impl.AsteriskParser;
import com.parser.impl.DivideParser;
import com.parser.impl.ListParser;
import com.parser.impl.RangeParser;

import java.util.HashMap;
import java.util.Map;

public class ParamParserFactory {

    private static Map<ParamParserType, IParamParser> instances = new HashMap<>();

    static {
        instances.put(ParamParserType.ASTERICPARSER, new AsteriskParser());
        instances.put(ParamParserType.LISTPARSER, new ListParser());
        instances.put(ParamParserType.RANGEPARSER, new RangeParser());
        instances.put(ParamParserType.DIVIDEPARSER, new DivideParser());
    }

    public static IParamParser getParamParser(String param) {
        if ("*".equals(param)) {
            return instances.get(ParamParserType.ASTERICPARSER);
        } else if (param.contains(",")) {
            return instances.get(ParamParserType.LISTPARSER);
        } else if (param.contains("-")) {
            return instances.get(ParamParserType.RANGEPARSER);
        } else if (param.contains("/")) {
            return instances.get(ParamParserType.DIVIDEPARSER);
        } else {
            return null;
        }
    }
}