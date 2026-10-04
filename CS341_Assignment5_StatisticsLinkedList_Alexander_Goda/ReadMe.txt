CS341 Software Engineering - Assignment 5
Alexander Goda

APPLICATION
Assignment 5 - Sample Statistics

PURPOSE
This Java GUI application allows a user to select a text file containing one real number per line. The program validates the input, stores valid values in a custom linked list, and reports the sample mean and sample standard deviation.

REQUIREMENTS ANALYSIS
1. The user must be able to select an input text file through the GUI.
2. The program must accept real numbers stored one per line.
3. The program must report the number of valid values, invalid entries, and blank lines.
4. The program must clearly display the mean and sample standard deviation.
5. The program must handle invalid input, an empty file, a one-line file, and blank lines without crashing.

DESIGN PLAN
NumberNode
- Stores one real number and a reference to the next node.

NumberLinkedList
- Custom singly linked list.
- Maintains head and tail references for efficient append operations.
- Maintains size, sum, and sum of squares so mean and standard deviation do not require unnecessary traversals.

NumberFileReader
- Reads and validates one line at a time.
- Places valid finite values into NumberLinkedList.
- Counts blank lines and records invalid entries.

FileReadResult
- Carries the linked list and input-validation summary from the file reader to the GUI.

Main
- Swing GUI/controller.
- Uses JFileChooser to select an input file.
- Displays input summary, mean, and sample standard deviation.

ASSUMPTIONS AND DECISIONS
1. "Standard deviation of the sample" is interpreted as sample standard deviation using n - 1.
2. Blank lines are ignored as numeric input and counted separately.
3. Non-numeric input, NaN, and Infinity are invalid and are reported to the user.
4. With one valid value, the mean is reported but sample standard deviation is shown as N/A because at least two sample values are required.
5. With no valid values, both statistics are shown as N/A.

RUNNING THE APPLICATION
1. Import this project into Eclipse as an existing project.
2. Confirm JavaSE-17 and JUnit 5 appear in the project build path.
3. Run statistics.Main as a Java Application.
4. Click Select Input File and choose a text file containing one value per line.

RUNNING THE JUNIT TESTS
Right-click the tester package and choose Run As -> JUnit Test.
The included test-data folder covers the minimum required Assignment 5 test conditions plus additional validation.

TEST DATA
valid_numbers.txt            normal valid data
invalid_input.txt            includes 3b4 and another invalid value
empty_file.txt               empty file
one_line.txt                 one valid line
empty_lines_middle.txt       blank lines between valid values
non_finite.txt               verifies NaN and Infinity are rejected

GITHUB
Planned repository name:
CS341-Assignment5-StatisticsLinkedList-Alexander-Goda
