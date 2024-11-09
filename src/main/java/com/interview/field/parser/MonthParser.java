package com.interview.field.parser;

import java.util.List;

public class MonthParser extends BaseCronFieldParser {
    private static final int MIN = 1;
    private static final int MAX = 12;

    public MonthParser() {
        super(MIN, MAX);
    }

    @Override
    public List<Integer> expand(String field) {
        CronFieldValidator.validate(field, MIN, MAX);
        return super.expand(field);
    }
}