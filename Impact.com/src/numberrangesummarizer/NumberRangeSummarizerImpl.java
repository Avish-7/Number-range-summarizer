package numberrangesummarizer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Implements the NumberRangeSummarizer interface to process comma-delimited number strings.
 */
public class NumberRangeSummarizerImpl implements NumberRangeSummarizer {

    @Override
    public Collection<Integer> collect(String input) {
        if (input == null || input.trim().isEmpty()) {
            return Collections.emptyList();
        }

        // Use a stream to process the input string.
        // This handles splitting, trimming whitespace, filtering empty strings,
        // parsing to integers, and collecting into a list.
        return Arrays.stream(input.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    @Override
    public String summarizeCollection(Collection<Integer> input) {
        if (input == null || input.isEmpty()) {
            return "";
        }

        // Using a TreeSet handles sorting and ensures uniqueness automatically.
        List<Integer> sortedUniqueNumbers = new ArrayList<>(new TreeSet<>(input));
        
        if (sortedUniqueNumbers.isEmpty()) {
            return "";
        }

        StringBuilder result = new StringBuilder();
        int rangeStart = sortedUniqueNumbers.get(0);

        for (int i = 0; i < sortedUniqueNumbers.size(); i++) {
            int currentNumber = sortedUniqueNumbers.get(i);
            
            // Check if it's the last number or if the next number is not sequential.
            boolean isEndOfRange = (i + 1 >= sortedUniqueNumbers.size()) || 
                                   (sortedUniqueNumbers.get(i + 1) != currentNumber + 1);

            if (isEndOfRange) {
                if (result.length() > 0) {
                    result.append(", ");
                }

                // If the range contains more than one number, format as "start-end".
                if (currentNumber > rangeStart) {
                    result.append(rangeStart).append("-").append(currentNumber);
                } else {
                    // Otherwise, just append the single number.
                    result.append(rangeStart);
                }

                // Set the start of the next potential range.
                if (i + 1 < sortedUniqueNumbers.size()) {
                    rangeStart = sortedUniqueNumbers.get(i + 1);
                }
            }
        }

        return result.toString();
    }
}
