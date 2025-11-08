package numberrangesummarizer;

import java.util.Collection;

/**
 * An interface for collecting a comma-delimited list of numbers and summarizing them into ranges.
 */
public interface NumberRangeSummarizer {

    /**
     * Collect the input string and parse it into a collection of integers.
     *
     * @param input A comma-delimited list of numbers.
     * @return A collection of integers, sorted and unique.
     */
    Collection<Integer> collect(String input);

    /**
     * Summarize a collection of integers into a comma-delimited string of ranges.
     *
     * @param input A collection of integers.
     * @return A string representing the summarized ranges, e.g., "1-3, 6, 8-10".
     */
    String summarizeCollection(Collection<Integer> input);
}
