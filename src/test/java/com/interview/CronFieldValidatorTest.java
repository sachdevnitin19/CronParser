package com.interview;

import com.interview.field.parser.CronFieldValidator;
import org.junit.Test;

import static org.junit.Assert.assertThrows;

public class CronFieldValidatorTest {

    @Test
    public void testValidSingleValue() {
        // Valid single values within the range
        CronFieldValidator.validate("5", 0, 59);
        CronFieldValidator.validate("59", 0, 59);
    }

    @Test
    public void testInvalidSingleValueOutOfRange() {
        // Value is out of range (too high)
        assertThrows(IllegalArgumentException.class, () -> CronFieldValidator.validate("60", 0, 59));

        // Value is out of range (too low)
        assertThrows(IllegalArgumentException.class, () -> CronFieldValidator.validate("-1", 0, 59));
    }

    @Test
    public void testValidList() {
        // Valid list within the range
        CronFieldValidator.validate("5,10,15", 0, 59);
    }

    @Test
    public void testInvalidListOutOfRange() {
        // One or more values in the list are out of range
        assertThrows(IllegalArgumentException.class, () -> CronFieldValidator.validate("5,10,60", 0, 59));
    }

    @Test
    public void testValidRange() {
        // Valid range within the bounds
        CronFieldValidator.validate("0-10", 0, 59);
    }

    @Test
    public void testInvalidRangeOutOfBounds() {
        // Start of range is out of bounds
        assertThrows(IllegalArgumentException.class, () -> CronFieldValidator.validate("-10-10", 0, 59));

        // End of range is out of bounds
        assertThrows(IllegalArgumentException.class, () -> CronFieldValidator.validate("10-60", 0, 59));

        // Start greater than end
        assertThrows(IllegalArgumentException.class, () -> CronFieldValidator.validate("10-5", 0, 59));
    }

    @Test
    public void testValidStepWithWildcard() {
        // Valid step expression with wildcard
        CronFieldValidator.validate("*/15", 0, 59);
    }

    @Test
    public void testValidStepWithRange() {
        // Valid step expression with a defined range
        CronFieldValidator.validate("5-20/5", 0, 59);
    }

    @Test
    public void testInvalidStepWithZeroStep() {
        // Step value is zero, which is invalid
        assertThrows(IllegalArgumentException.class, () -> CronFieldValidator.validate("*/0", 0, 59));
    }

    @Test
    public void testInvalidStepFormat() {
        // Invalid format for step expression (missing range or wildcard)
        assertThrows(IllegalArgumentException.class, () -> CronFieldValidator.validate("5/10", 0, 59));
    }

    @Test
    public void testWildcardOnly() {
        // Wildcard alone should be valid
        CronFieldValidator.validate("*", 0, 59);
    }

    @Test
    public void testInvalidFormat() {
        // Invalid format that doesn't match any allowed pattern
        assertThrows(IllegalArgumentException.class, () -> CronFieldValidator.validate("abc", 0, 59));
        assertThrows(IllegalArgumentException.class, () -> CronFieldValidator.validate("5--10", 0, 59));
        assertThrows(IllegalArgumentException.class, () -> CronFieldValidator.validate("5,,10", 0, 59));
    }
}
