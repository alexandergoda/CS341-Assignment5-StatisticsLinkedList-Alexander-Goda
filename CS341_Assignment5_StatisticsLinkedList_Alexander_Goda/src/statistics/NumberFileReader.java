package statistics;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads one number per line from a text file and validates the input.
 *
 * @author Alexander Goda
 */
public class NumberFileReader {

    /**
     * Reads a text file. Valid finite real numbers are placed in the custom
     * linked list. Blank lines are skipped and invalid lines are summarized.
     *
     * @param file input file selected by the user
     * @return file-reading result
     * @throws IOException if the file cannot be read
     */
    public FileReadResult readFile(File file) throws IOException {
        NumberLinkedList numbers = new NumberLinkedList();
        List<String> invalidEntries = new ArrayList<>();
        int blankLines = 0;
        int lineNumber = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                String text = line.trim();

                if (text.isEmpty()) {
                    blankLines++;
                    continue;
                }

                try {
                    double value = Double.parseDouble(text);
                    if (Double.isFinite(value)) {
                        numbers.add(value);
                    } else {
                        invalidEntries.add("Line " + lineNumber + ": " + text);
                    }
                } catch (NumberFormatException exception) {
                    invalidEntries.add("Line " + lineNumber + ": " + text);
                }
            }
        }

        return new FileReadResult(numbers, blankLines, invalidEntries);
    }
}
