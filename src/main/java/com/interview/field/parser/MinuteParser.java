package com.interview.field.parser;

import java.util.List;

public class MinuteParser extends BaseCronFieldParser {
    private static final int MIN = 0;
    private static final int MAX = 59;

    public MinuteParser() {
        super(MIN, MAX);
    }

    @Override
    public List<Integer> expand(String field) {
        CronFieldValidator.validate(field, MIN, MAX);
        return super.expand(field);
    }
}