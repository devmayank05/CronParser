# Cron Expression Validator and Builder

This project is designed to validate and build cron expressions based on client input. It is divided into three main components: input validation, segment parsing, and cron expression construction. Each segment of the cron expression is parsed using different parsers with distinct logic, allowing flexibility and maintainability in handling various cron expression formats.

### Valid Cron Expression:
*/15 0 1,15 * 1-5 /usr/bin/find

## Project Structure

1. **Client-Facing Layer:**
   - The `CronExpressionEvaluator` class handles the basic validation of the input cron expression string. It checks if the input is in a valid format and delegates the parsing of each segment to the appropriate parser.
   
2. **Segment Parsing:**
   - In this layer, the input string is split into individual cron expression segments. Each segment is processed using a specific parser (e.g., `RangeParser`, `ListParser`, `AsteriskParser`, `DivideParser`). These parsers handle different formats of cron segments, such as ranges, lists, steps, and wildcards.
   - Parsers ensure the following:
     - Correct number of parameters after splitting the segment.
     - Parameters are valid numbers (or a valid wildcard).
     - The parsed range falls within the allowed limits for the corresponding cron parameter type (e.g., minute, hour, day).

3. **Cron Expression Construction:**
   - Once the segments are parsed, the `CronExpressionBuilderV2` class is responsible for constructing the final `CronExpression` object. The builder uses the parsed values to create a valid cron expression that meets the user’s requirements.
   - Initially, I considered using a `Dictionary` for storing the parameters in the `CronExpression` class, but I later decided to keep them as individual fields since most parameters are static and unlikely to change in the future.

## Core Functionality

- **Cron Expression Evaluation:**
  - `CronExpressionEvaluator` takes a cron expression string as input, validates it, and splits it into segments.
  - The individual segments are then passed to the relevant parsers to retrieve their valid range values.
  - The range values are passed to the `CronExpressionBuilderV2` to build the final cron expression.

- **Segment Parsing:**
  - Each cron segment (minute, hour, day of month, month, day of week) is parsed using specialized parsers:
    - `RangeParser`: Handles range expressions like `1-5` or `*/15`.
    - `ListParser`: Handles list expressions like `1,2,3`.
    - `AsteriskParser`: Handles wildcard expressions like `*`.
    - `DivideParser`: Handles step values like `/5`.
  - Each parser ensures that the parsed values are within the valid range for that particular segment.

- **Final Cron Expression:**
  - After parsing all segments, the `CronExpressionBuilderV2` assembles the cron expression by setting each segment's values.
  - A `CronExpression` object is built, which represents the final, valid cron expression.

## Features

- **Validation:**
  - Ensures that the cron expression is correctly formatted and that the values in each segment fall within their valid ranges.
  - Provides detailed error messages if any segment is invalid.

- **Flexible Parsing:**
  - Supports multiple formats of cron expression segments.

- **Unit Testing:**
  - Unit tests are provided to validate the correctness of the final `CronExpression` object.
  - Tests also validate the behavior of each segment parser, ensuring that the segment values are correctly parsed and fall within the valid range.

## Test
   - We can run as console application by giving cron string as param
   - if we need to test multiple params, we run application and it will keep promting to enter cron string

