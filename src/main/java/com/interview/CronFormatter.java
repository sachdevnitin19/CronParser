package com.interview;

import java.util.List;

public class CronFormatter {
    public static String formatField(String name, List<Integer> values) {
        return String.format("%-14s%s", name, values.toString().replaceAll("[\\[\\],]", ""));
    }

    public static String formatCommand(String command) {
        return String.format("%-14s%s", "command", command);
    }
}