package statistics;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * GUI controller for CS341 Assignment 5.
 *
 * @author Alexander Goda
 */
public class Main {
    private JFrame frame;
    private JTextField selectedFileText;
    private JTextArea outputTextArea;
    private JButton selectFileButton;

    /**
     * Starts the application on the Swing event-dispatch thread.
     *
     * @param args command-line arguments are not used
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Main window = new Main();
                    window.frame.setVisible(true);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
    }

    /**
     * Constructs the application and registers its events.
     */
    public Main() {
        initialize();
        createEvents();
    }

    /**
     * Builds the GUI components.
     */
    private void initialize() {
        frame = new JFrame("Assignment 5 - Sample Statistics");
        frame.setBounds(100, 100, 720, 520);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel titleLabel = new JLabel("Sample Mean and Standard Deviation");
        titleLabel.setFont(new Font("Lucida Grande", Font.BOLD, 18));
        titleLabel.setBounds(28, 18, 430, 30);
        frame.getContentPane().add(titleLabel);

        selectFileButton = new JButton("Select Input File");
        selectFileButton.setBounds(28, 65, 165, 34);
        frame.getContentPane().add(selectFileButton);

        selectedFileText = new JTextField();
        selectedFileText.setEditable(false);
        selectedFileText.setBounds(205, 66, 475, 32);
        frame.getContentPane().add(selectedFileText);
        selectedFileText.setColumns(10);

        outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);
        outputTextArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
        outputTextArea.setLineWrap(true);
        outputTextArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(outputTextArea);
        scrollPane.setBounds(28, 120, 652, 325);
        frame.getContentPane().add(scrollPane);

        outputTextArea.setText("Select a text file containing one real number per line.");
    }

    /**
     * Registers GUI event handlers.
     */
    private void createEvents() {
        selectFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                chooseAndProcessFile();
            }
        });
    }

    /**
     * Opens JFileChooser and processes the selected text file.
     */
    private void chooseAndProcessFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select Input Text File");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text files (*.txt)", "txt"));

        int selection = fileChooser.showOpenDialog(frame);
        if (selection != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File inputFile = fileChooser.getSelectedFile();
        selectedFileText.setText(inputFile.getAbsolutePath());

        NumberFileReader fileReader = new NumberFileReader();
        try {
            FileReadResult result = fileReader.readFile(inputFile);
            displayResults(inputFile, result);
        } catch (IOException exception) {
            outputTextArea.setText("Unable to read the selected file.\n\n" + exception.getMessage());
        }
    }

    /**
     * Displays statistics and input-validation results in the GUI.
     *
     * @param inputFile selected file
     * @param result parsed file data
     */
    private void displayResults(File inputFile, FileReadResult result) {
        NumberLinkedList numbers = result.getNumbers();
        StringBuilder output = new StringBuilder();

        output.append("INPUT SUMMARY\n");
        output.append("-------------\n");
        output.append("File: ").append(inputFile.getName()).append("\n");
        output.append("Valid numbers: ").append(numbers.getSize()).append("\n");
        output.append("Invalid entries: ").append(result.getInvalidInputCount()).append("\n");
        output.append("Blank lines skipped: ").append(result.getBlankLineCount()).append("\n\n");

        output.append("RESULTS\n");
        output.append("-------\n");
        if (numbers.isEmpty()) {
            output.append("Mean: N/A (no valid numbers)\n");
            output.append("Sample standard deviation: N/A (requires at least two valid numbers)\n");
        } else {
            output.append(String.format(Locale.US, "Mean: %.6f%n", numbers.getMean()));
            if (numbers.getSize() < 2) {
                output.append("Sample standard deviation: N/A (requires at least two valid numbers)\n");
            } else {
                output.append(String.format(Locale.US, "Sample standard deviation: %.6f%n",
                        numbers.getSampleStandardDeviation()));
            }
        }

        if (result.getInvalidInputCount() > 0) {
            output.append("\nINVALID INPUT DETAILS\n");
            output.append("---------------------\n");
            for (String invalidEntry : result.getInvalidEntries()) {
                output.append(invalidEntry).append("\n");
            }
        }

        outputTextArea.setText(output.toString());
        outputTextArea.setCaretPosition(0);
    }
}
