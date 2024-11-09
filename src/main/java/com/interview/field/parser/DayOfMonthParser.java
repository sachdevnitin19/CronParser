package com.interview.field.parser;

import java.util.List;

public class DayOfMonthParser extends BaseCronFieldParser {
    private static final int MIN = 1;
    private static final int MAX = 31;

    public DayOfMonthParser() {
        super(MIN, MAX);
    }

    @Override
    public List<Integer> expand(String field) {
        CronFieldValidator.validate(field, MIN, MAX);
        return super.expand(field);
    }
}