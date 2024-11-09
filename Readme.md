# Cron Parser

A simple command-line application to parse and expand cron expressions into readable times, following the standard cron format. This project also includes validation to ensure correct cron syntax.

## Features

- Parses cron expressions with five time fields (minute, hour, day of month, month, and day of week) plus a command.
- Validates each cron field for correct syntax, range, and format (e.g., lists, ranges, and step values).
- Outputs expanded cron times in a readable table format.
- Includes a comprehensive suite of unit tests.

## Example

For a cron expression:

```bash
*/15 0 1,15 * 1-5 /usr/bin/find
```

The output will be:

```plaintext
minute        0 15 30 45
hour          0
day of month  1 15
month         1 2 3 4 5 6 7 8 9 10 11 12
day of week   1 2 3 4 5
command       /usr/bin/find
```

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher

## Setup and Installation

1. **Clone the repository**:

   ```bash
   git clone https://github.com/sachdevnitin19/CronParser.git
   cd CronParser
   ```

2. **Build the project**:

   Use Maven to compile and package the project.

   ```bash
   mvn clean package
   ```

3. **Run the application**:

   You can run the application directly using the packaged JAR file.

   ```bash
   java -jar target/cron-parser.jar "*/15 0 1,15 * 1-5 /usr/bin/find"
   ```

   Replace the sample cron expression above with your own as needed.

## Project Structure

```
src
├── main
│   └── java
│       └── com
│           └── interview
│                 └── field
│                       └── parser
│                          ├── CronFieldValidator.java     # Validates each cron field for syntax and range
│                          ├── BaseCronFieldParser.java    # Base class to parse individual cron fields
│                          ├── MinuteParser.java           # Parses and expands minute field
│                          ├── HourParser.java             # Parses and expands hour field
│                          ├── DayOfMonthParser.java       # Parses and expands day of month field
│                          ├── MonthParser.java            # Parses and expands month field
│                          ├── DayOfWeekParser.java        # Parses and expands day of week field
│                          └── CronFieldParserFactory.java # Factory to instantiate field parsers
│               ├── CronParser.java             # Main class for parsing and expanding cron expressions
│               └── CronFormatter.java          # class for formatting the output strings
└── test
    └── java
        └── com
            └── example
                ├── CronParserTest.java         # Unit tests for CronParser class
                └── CronFieldValidatorTest.java # Unit tests for CronFieldValidator class
```

## Running Tests

To run the test suite, execute the following command:

```bash
mvn test
```

This will run the unit tests defined in `CronParserTest` and `CronFieldValidatorTest`, ensuring all cron parsing and validation functionality works as expected.

## Implementation Details

- **CronParser**: The main class that splits a cron expression and expands each field.
- **BaseCronFieldParser**: A base class providing shared logic for parsing and expanding cron fields. Each field parser (e.g., `MinuteParser`, `HourParser`) extends this class and sets field-specific minimum and maximum values.
- **CronFieldValidator**: Validates field values for range, format, and syntax. Throws `IllegalArgumentException` for invalid inputs.
- **CronFieldParserFactory**: A factory class to instantiate the appropriate field parser for each cron field (minute, hour, etc.).

## Validation Rules

The `CronFieldValidator` enforces the following rules:
- **Single Values**: Must be within the specified range (e.g., `5` for minutes).
- **Lists**: Comma-separated values must be valid (e.g., `1,5,10`).
- **Ranges**: Start and end values must be within range and in ascending order (e.g., `0-10`).
- **Steps**: Step values must be positive (e.g., `*/15`, `5-20/5`).
- **Wildcard**: The `*` character is allowed as a wildcard.

