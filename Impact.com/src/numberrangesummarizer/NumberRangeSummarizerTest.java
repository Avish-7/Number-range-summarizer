package numberrangesummarizer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;

/**
 * Unit tests for the NumberRangeSummarizerImpl class.
 */
public class NumberRangeSummarizerTest {

    private NumberRangeSummarizer summarizer;

    @BeforeEach
    void setUp() {
        summarizer = new NumberRangeSummarizerImpl();
    }

    @Test
    void testCollect_basicInput() {
        String input = "1,3,6,7,8,12,13,14,15,21,22,23,24,31";
        Collection<Integer> result = summarizer.collect(input);
        assertEquals(14, result.size());
        assertTrue(result.contains(1));
        assertTrue(result.contains(31));
    }

    @Test
    void testCollect_withUnsortedInput() {
        String input = "5,3,1,2,4";
        Collection<Integer> result = summarizer.collect(input);
        // The summarizeCollection method will sort it, but collect does not guarantee order.
        assertEquals(5, result.size());
    }

    @Test
    void testCollect_withDuplicates() {
        String input = "1,2,2,3,3,3";
        Collection<Integer> result = summarizer.collect(input);
        assertEquals(6, result.size()); // Collect preserves duplicates initially
    }

    @Test
    void testCollect_withNegativeNumbers() {
        String input = "-5,-1,0,1,5";
        Collection<Integer> result = summarizer.collect(input);
        assertEquals(5, result.size());
        assertTrue(result.contains(-5));
    }

    @Test
    void testCollect_withWhitespace() {
        String input = " 1,  2,3 , 4 ";
        Collection<Integer> result = summarizer.collect(input);
        assertEquals(4, result.size());
    }

    @Test
    void testCollect_emptyInput() {
        String input = "";
        Collection<Integer> result = summarizer.collect(input);
        assertTrue(result.isEmpty());
    }

    @Test
    void testCollect_nullInput() {
        String input = null;
        Collection<Integer> result = summarizer.collect(input);
        assertTrue(result.isEmpty());
    }

    @Test
    void testSummarize_providedExample() {
        String input = "1,3,6,7,8,12,13,14,15,21,22,23,24,31";
        Collection<Integer> numbers = summarizer.collect(input);
        String result = summarizer.summarizeCollection(numbers);
        assertEquals("1, 3, 6-8, 12-15, 21-24, 31", result);
    }

    @Test
    void testSummarize_sequentialNumbers() {
        Collection<Integer> numbers = summarizer.collect("1,2,3,4,5");
        String result = summarizer.summarizeCollection(numbers);
        assertEquals("1-5", result);
    }

    @Test
    void testSummarize_noSequentialNumbers() {
        Collection<Integer> numbers = summarizer.collect("1,3,5,7,9");
        String result = summarizer.summarizeCollection(numbers);
        assertEquals("1, 3, 5, 7, 9", result);
    }

    @Test
    void testSummarize_withDuplicatesAndUnsorted() {
        Collection<Integer> numbers = summarizer.collect("5,3,1,2,4,1,5");
        String result = summarizer.summarizeCollection(numbers);
        assertEquals("1-5", result);
    }

    @Test
    void testSummarize_withNegativeNumbers() {
        Collection<Integer> numbers = summarizer.collect("-3,-2,-1,2,3,4,7");
        String result = summarizer.summarizeCollection(numbers);
        assertEquals("-3--1, 2-4, 7", result);
    }

    @Test
    void testSummarize_emptyCollection() {
        Collection<Integer> numbers = summarizer.collect("");
        String result = summarizer.summarizeCollection(numbers);
        assertEquals("", result);
    }
    
    @Test
    void testSummarize_singleNumber() {
        Collection<Integer> numbers = summarizer.collect("42");
        String result = summarizer.summarizeCollection(numbers);
        assertEquals("42", result);
    }
}
