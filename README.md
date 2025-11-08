# Number Range Summarizer

## Description

This Java application provides a utility to summarize a collection of numbers into a string of ranges. It takes a comma-delimited string of numbers, collects them into a collection of integers, and then summarizes them into a more readable format. For example, the input `"1,3,6,7,8,12,13,14,15,21,22,23,24,31"` would be summarized as `"1, 3, 6-8, 12-15, 21-24, 31"`.

## Project Structure

The project is organized into the following structure:

```
.
├── README.md
├── bin
│   └── numberrangesummarizer
│       ├── NumberRangeSummarizer.class
│       ├── NumberRangeSummarizerImpl.class
│       ├── NumberRangeSummarizerTest.class
│       └── TestRunner.class
├── lib
│   └── junit-platform-console-standalone-1.10.0.jar
└── src
    └── numberrangesummarizer
        ├── App.java
        ├── NumberRangeSummarizer.java
        ├── NumberRangeSummarizerImpl.java
        ├── NumberRangeSummarizerTest.java
        └── TestRunner.java
```

-   `src/`: Contains the Java source files.
    -   `numberrangesummarizer/`: The package for the application.
        -   `NumberRangeSummarizer.java`: An interface defining the contract for collecting and summarizing numbers.
        -   `NumberRangeSummarizerImpl.java`: The implementation of the `NumberRangeSummarizer` interface.
        -   `NumberRangeSummarizerTest.java`: JUnit tests for the `NumberRangeSummarizerImpl`.
        -   `TestRunner.java`: A class to run the JUnit tests from the command line.
        -   `App.java`: The main application class (currently empty).
-   `bin/`: Contains the compiled `.class` files.
-   `lib/`: Contains the JUnit library required for testing.
-   `README.md`: This file.

## How to Compile

To compile the application, you need a Java Development Kit (JDK) installed. Open a terminal or command prompt and navigate to the `src` directory. Then, run the following command:

```bash
javac -d ../bin -cp ../lib/junit-platform-console-standalone-1.10.0.jar numberrangesummarizer/*.java
```

This command compiles all the `.java` files in the `numberrangesummarizer` package and places the compiled `.class` files in the `bin` directory.

## How to Run

The `App.java` file is the main entry point for the application. You can add code to this file to take user input and display the summarized output.

To run the `App` class, use the following command from the root directory:

```bash
java -cp bin numberrangesummarizer.App
```

## How to Run Tests

The project includes a suite of JUnit tests to verify the functionality of the `NumberRangeSummarizerImpl` class. To run the tests, use the `TestRunner` class, which utilizes the JUnit Platform Console Standalone JAR.

From the root directory, run the following command:

```bash
java -jar lib/junit-platform-console-standalone-1.10.0.jar --class-path bin --scan-class-path
```

This command will discover and run all the tests in the `bin` directory and display the results in the console.

## Code Explanation

### `NumberRangeSummarizer.java`

This is an interface that defines the two main operations of the utility:

-   `collect(String input)`: Takes a comma-delimited string of numbers and returns a `Collection<Integer>`.
-   `summarizeCollection(Collection<Integer> input)`: Takes a collection of integers and returns a summarized string.

### `NumberRangeSummarizerImpl.java`

This class implements the `NumberRangeSummarizer` interface.

-   **`collect(String input)`**:
    -   It handles `null` or empty input strings by returning an empty list.
    -   It uses a Java Stream to split the input string by commas.
    -   Each part is trimmed of whitespace, checked for emptiness, and then parsed into an `Integer`.
    -   The resulting integers are collected into a `List`.

-   **`summarizeCollection(Collection<Integer> input)`**:
    -   It handles `null` or empty collections by returning an empty string.
    -   It uses a `TreeSet` to automatically sort the numbers and remove duplicates.
    -   It then iterates through the sorted list of numbers to find sequential ranges.
    -   A `StringBuilder` is used to efficiently build the result string.
    -   If a sequence of numbers is found (e.g., 6, 7, 8), it is formatted as a range (e.g., "6-8").
    -   Single numbers are appended as they are.

### `NumberRangeSummarizerTest.java`

This class contains JUnit tests for the `NumberRangeSummarizerImpl` class. It covers various scenarios, including:

-   Basic input with a mix of single numbers and ranges.
-   Unsorted input and input with duplicates.
-   Input with negative numbers.
-   Input with extra whitespace.
-   Empty and `null` inputs.
-   A single number input.

This comprehensive test suite ensures that the implementation is robust and handles edge cases correctly.
