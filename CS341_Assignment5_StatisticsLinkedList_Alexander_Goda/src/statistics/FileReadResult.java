package statistics;

import java.util.List;

/**
 * Stores the result of reading and validating one input file.
 *
 * @author Alexander Goda
 */
public class FileReadResult {
    private final NumberLinkedList numbers;
    private final int blankLineCount;
    private final List<String> invalidEntries;

    /**
     * Constructs an immutable summary of an input-file read operation.
     *
     * @param numbers valid numbers read from the file
     * @param blankLineCount number of blank lines skipped
     * @param invalidEntries descriptions of invalid lines
     */
    public FileReadResult(NumberLinkedList numbers, int blankLineCount, List<String> invalidEntries) {
        this.numbers = numbers;
        this.blankLineCount = blankLineCount;
        this.invalidEntries = List.copyOf(invalidEntries);
    }

    public NumberLinkedList getNumbers() {
        return numbers;
    }

    public int getBlankLineCount() {
        return blankLineCount;
    }

    public int getInvalidInputCount() {
        return invalidEntries.size();
    }

    public List<String> getInvalidEntries() {
        return invalidEntries;
    }
}
