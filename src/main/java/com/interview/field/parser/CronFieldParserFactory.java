package com.interview.field.parser;

public class CronFieldParserFactory {
    public static CronFieldParser getParser(String fieldName) {
        return switch (fieldName) {
            case "minute" -> new MinuteParser();
            case "hour" -> new HourParser();
            case "day of month" -> new DayOfMonthParser();
            case "month" -> new MonthParser();
            case "day of week" -> new DayOfWeekParser();
            default -> throw new IllegalArgumentException("Unknown field name: " + fieldName);
        };
    }
}
