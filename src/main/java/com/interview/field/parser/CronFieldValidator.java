package com.interview.field.parser;

import java.util.regex.Pattern;

public class CronFieldValidator {

    private static final Pattern SINGLE_INT_PATTERN = Pattern.compile("^\\d+$");
    private static final Pattern LIST_PATTERN = Pattern.compile("^(\\d+,)*\\d+$");
    private static final Pattern RANGE_PATTERN = Pattern.compile("^\\d+-\\d+$");
    private static final Pattern STEP_PATTERN = Pattern.compile("^(\\*/\\d+|\\d+-\\d+/\\d+)$");

    public static void validate(String field, int min, int max) {
        if (field.equals("*")) return;  // '*' is always valid

        if (SINGLE_INT_PATTERN.matcher(field).matches()) {
            validateSingleValue(field, min, max);
        } else if (LIST_PATTERN.matcher(field).matches()) {
            validateList(field, min, max);
        } else if (RANGE_PATTERN.matcher(field).matches()) {
            validateRange(field, min, max);
        } else if (STEP_PATTERN.matcher(field).matches()) {
            validateStep(field, min, max);
        } else {
            throw new IllegalArgumentException("Invalid format: " + field);
        }
    }

    private static void validateSingleValue(String field, int min, int max) {
        int value = parseInteger(field);
        if (value < min || value > max) {
            throw new IllegalArgumentException("Value out of range: " + field);
        }
    }

    private static void validateList(String field, int min, int max) {
        String[] values = field.split(",");
        for (String value : values) {
            validateSingleValue(value, min, max);
        }
    }

    private static void validateRange(String field, int min, int max) {
        String[] range = field.split("-");
        int start = parseInteger(range[0]);
        int end = parseInteger(range[1]);

        if (start < min || end > max || start > end) {
            throw new IllegalArgumentException("Invalid range: " + field);
        }
    }

    private static void validateStep(String field, int min, int max) {
        String[] parts = field.split("/");
        String base = parts[0];
        int step = parseInteger(parts[1]);

        if (step <= 0) {
            throw new IllegalArgumentException("Step value must be greater than 0: " + field);
        }

        if (!base.equals("*")) {
            if (RANGE_PATTERN.matcher(base).matches()) {
                validateRange(base, min, max);
            } else {
                throw new IllegalArgumentException("Invalid base for step: " + field);
            }
        }
    }

    private static int parseInteger(String value) {
        try {
            int parsedValue = Integer.parseInt(value);
            if (parsedValue < 0) {
                throw new IllegalArgumentException("Negative value not allowed: " + value);
            }
            return parsedValue;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid integer: " + value, e);
        }
    }
}
