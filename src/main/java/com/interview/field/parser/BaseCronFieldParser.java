package com.interview.field.parser;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseCronFieldParser implements CronFieldParser {
    private final int min;
    private final int max;

    protected BaseCronFieldParser(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public List<Integer> expand(String field) {
        List<Integer> result = new ArrayList<>();

        if (field.equals("*")) {
            for (int i = min; i <= max; i++) {
                result.add(i);
            }
        } else if (field.contains("/")) {
            String[] parts = field.split("/");
            int interval = Integer.parseInt(parts[1]);
            int start = parts[0].equals("*") ? min : Integer.parseInt(parts[0]);
            for (int i = start; i <= max; i += interval) {
                result.add(i);
            }
        } else if (field.contains("-")) {
            String[] parts = field.split("-");
            int start = Integer.parseInt(parts[0]);
            int end = Integer.parseInt(parts[1]);
            for (int i = start; i <= end; i++) {
                result.add(i);
            }
        } else if (field.contains(",")) {
            String[] values = field.split(",");
            for (String value : values) {
                result.add(Integer.parseInt(value));
            }
        } else {
            result.add(Integer.parseInt(field));
        }

        return result;
    }
}
