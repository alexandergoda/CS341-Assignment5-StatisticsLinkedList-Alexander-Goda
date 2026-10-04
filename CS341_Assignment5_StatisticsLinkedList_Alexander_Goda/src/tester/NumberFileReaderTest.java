package tester;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import statistics.FileReadResult;
import statistics.NumberFileReader;

/**
 * JUnit tests for the Assignment 5 input-file requirements.
 *
 * @author Alexander Goda
 */
public class NumberFileReaderTest {
    private final NumberFileReader reader = new NumberFileReader();

    @Test
    void testValidInputFile() throws IOException {
        FileReadResult result = reader.readFile(new File("test-data/valid_numbers.txt"));
        assertEquals(8, result.getNumbers().getSize(), "Eight valid numbers should be read");
        assertEquals(5.0, result.getNumbers().getMean(), 0.000001, "Mean should equal five");
        assertEquals(0, result.getInvalidInputCount(), "There should be no invalid input");
    }

    @Test
    void testInvalidInputSuchAs3b4() throws IOException {
        FileReadResult result = reader.readFile(new File("test-data/invalid_input.txt"));
        assertEquals(3, result.getNumbers().getSize(), "Three valid numbers should remain");
        assertEquals(2, result.getInvalidInputCount(), "Two invalid entries should be reported");
        assertTrue(result.getInvalidEntries().get(0).contains("3b4"), "3b4 should be reported as invalid");
    }

    @Test
    void testEmptyFile() throws IOException {
        FileReadResult result = reader.readFile(new File("test-data/empty_file.txt"));
        assertEquals(0, result.getNumbers().getSize(), "Empty file should produce no values");
        assertTrue(Double.isNaN(result.getNumbers().getMean()), "Mean should be unavailable");
    }

    @Test
    void testOneLineFile() throws IOException {
        FileReadResult result = reader.readFile(new File("test-data/one_line.txt"));
        assertEquals(1, result.getNumbers().getSize(), "One-line file should produce one value");
        assertEquals(12.5, result.getNumbers().getMean(), 0.000001, "Mean should equal the only value");
        assertTrue(Double.isNaN(result.getNumbers().getSampleStandardDeviation()),
                "Sample standard deviation should be unavailable for one value");
    }

    @Test
    void testEmptyLinesInMiddle() throws IOException {
        FileReadResult result = reader.readFile(new File("test-data/empty_lines_middle.txt"));
        assertEquals(3, result.getNumbers().getSize(), "Three valid numbers should be read");
        assertEquals(2, result.getBlankLineCount(), "Two blank lines should be reported");
        assertEquals(2.0, result.getNumbers().getMean(), 0.000001, "Mean should equal two");
    }

    @Test
    void testNonFiniteValuesAreInvalid() throws IOException {
        FileReadResult result = reader.readFile(new File("test-data/non_finite.txt"));
        assertEquals(1, result.getNumbers().getSize(), "Only the finite value should be accepted");
        assertEquals(2, result.getInvalidInputCount(), "NaN and Infinity should be rejected");
    }
}
