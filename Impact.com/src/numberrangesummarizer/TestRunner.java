package numberrangesummarizer;

import java.util.*;

public class TestRunner {
    public static void main(String[] args) {
        NumberRangeSummarizer summarizer = new NumberRangeSummarizerImpl();

        String input = "1,3,6,7,8,12,13,14,15,21,22,23,24,31";
        Collection<Integer> collected = summarizer.collect(input);
        String result = summarizer.summarizeCollection(collected);

        System.out.println("Input: " + input);
        System.out.println("Collected: " + collected);
        System.out.println("Summary: " + result);
    }
}
