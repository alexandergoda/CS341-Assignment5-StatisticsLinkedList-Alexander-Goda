package tester;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import statistics.NumberLinkedList;

/**
 * JUnit tests for the custom linked list and statistics calculations.
 *
 * @author Alexander Goda
 */
public class NumberLinkedListTest {

    @Test
    void testNewListIsEmpty() {
        NumberLinkedList list = new NumberLinkedList();
        assertTrue(list.isEmpty(), "A new list should be empty");
        assertEquals(0, list.getSize(), "A new list should have size zero");
    }

    @Test
    void testAddAndGetValues() {
        NumberLinkedList list = new NumberLinkedList();
        list.add(1.5);
        list.add(-2.0);
        list.add(8.25);

        assertEquals(3, list.getSize(), "Three values should be stored");
        assertEquals(1.5, list.get(0), 0.000001, "First value should match");
        assertEquals(-2.0, list.get(1), 0.000001, "Second value should match");
        assertEquals(8.25, list.get(2), 0.000001, "Third value should match");
    }

    @Test
    void testMean() {
        NumberLinkedList list = new NumberLinkedList();
        list.add(2.0);
        list.add(4.0);
        list.add(6.0);
        assertEquals(4.0, list.getMean(), 0.000001, "Mean should equal four");
    }

    @Test
    void testSampleStandardDeviation() {
        NumberLinkedList list = new NumberLinkedList();
        double[] values = { 2, 4, 4, 4, 5, 5, 7, 9 };
        for (double value : values) {
            list.add(value);
        }
        assertEquals(2.138089935, list.getSampleStandardDeviation(), 0.000001,
                "Sample standard deviation should use n - 1");
    }

    @Test
    void testEmptyListStatisticsAreNotAvailable() {
        NumberLinkedList list = new NumberLinkedList();
        assertTrue(Double.isNaN(list.getMean()), "Empty list mean should be unavailable");
        assertTrue(Double.isNaN(list.getSampleStandardDeviation()),
                "Empty list standard deviation should be unavailable");
    }

    @Test
    void testOneValueStandardDeviationIsNotAvailable() {
        NumberLinkedList list = new NumberLinkedList();
        list.add(12.5);
        assertEquals(12.5, list.getMean(), 0.000001, "One-value mean should equal that value");
        assertTrue(Double.isNaN(list.getSampleStandardDeviation()),
                "Sample standard deviation needs at least two values");
    }
}
