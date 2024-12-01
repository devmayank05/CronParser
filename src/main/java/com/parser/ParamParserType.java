package com.parser;

/**
 * Field Parser types will used in factory
 */
public enum ParamParserType {
    /**
     * All Range
     */
    ASTERICPARSER,

    /**
     * Given list separated by comma
     */
    LISTPARSER,

    /**
     * Given Range min max separted by dash
     */
    RANGEPARSER,

    /**
     * Divide all range by /Divide operator
     */
    DIVIDEPARSER;
}
