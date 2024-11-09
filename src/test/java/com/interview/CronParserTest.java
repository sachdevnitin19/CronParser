package com.interview;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CronParserTest {

    @Test
    public void testSimpleCronExpression() {
        String input = "*/15 0 1,15 * 1-5 /usr/bin/find";
        String expectedOutput = """
                minute        0 15 30 45
                hour          0
                day of month  1 15
                month         1 2 3 4 5 6 7 8 9 10 11 12
                day of week   1 2 3 4 5
                command       /usr/bin/find
                """;

        assertEquals(expectedOutput.strip(), CronParser.parse(input).strip());
    }

    @Test
    public void testWildcardMinute() {
        String input = "* 12 * * * /bin/echo";
        String expectedOutput = """
                minute        0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45 46 47 48 49 50 51 52 53 54 55 56 57 58 59
                hour          12
                day of month  1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31
                month         1 2 3 4 5 6 7 8 9 10 11 12
                day of week   0 1 2 3 4 5 6
                command       /bin/echo
                """;

        assertEquals(expectedOutput.strip(), CronParser.parse(input).strip());
    }

    @Test
    public void testSingleValueFields() {
        String input = "0 0 1 1 0 /bin/date";
        String expectedOutput = """
                minute        0
                hour          0
                day of month  1
                month         1
                day of week   0
                command       /bin/date
                """;

        assertEquals(expectedOutput.strip(), CronParser.parse(input).strip());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidCronExpression() {
        String input = "*/15 0 *";
        CronParser.parse(input);
    }
}
