package com.interview;


import com.interview.field.parser.CronFieldParser;
import com.interview.field.parser.CronFieldParserFactory;


import java.util.List;

import static com.interview.CronFormatter.formatCommand;
import static com.interview.CronFormatter.formatField;

public class CronParser {

    public static String parse(String cronExpression) {
        String[] parts = cronExpression.split(" ");
        if (parts.length < 6)
            throw new IllegalArgumentException("Invalid input");

        StringBuilder output = new StringBuilder();
        String[] fieldNames = {"minute", "hour", "day of month", "month", "day of week"};

        for (int i = 0; i < fieldNames.length; i++) {
            CronFieldParser parser = CronFieldParserFactory.getParser(fieldNames[i]);
            List<Integer> expandedValues = parser.expand(parts[i]);
            output.append(formatField(fieldNames[i], expandedValues)).append("\n");
        }

        output.append(formatCommand(parts[5]));
        return output.toString();
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java -cp <jarfile> com.interview.CronParser \"<cron expression>\"");
            return;
        }
        System.out.println(parse(args[0]));
    }
}
