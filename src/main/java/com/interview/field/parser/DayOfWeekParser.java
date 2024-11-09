package com.interview.field.parser;

import java.util.List;

public class DayOfWeekParser extends BaseCronFieldParser {
    private static final int MIN = 0;
    private static final int MAX = 6;

    public DayOfWeekParser() {
        super(MIN, MAX);
    }

    @Override
    public List<Integer> expand(String field) {
        CronFieldValidator.validate(field, MIN, MAX);
        return super.expand(field);
    }

}