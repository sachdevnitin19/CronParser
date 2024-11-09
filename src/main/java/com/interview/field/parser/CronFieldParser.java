package com.interview.field.parser;

import java.util.List;

public interface CronFieldParser {
    List<Integer> expand(String field);
}