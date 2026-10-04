package statistics;

/**
 * A custom singly linked list that stores real numbers for Assignment 5.
 * The class keeps a tail pointer and running totals so values are appended
 * efficiently and statistics do not require unnecessary list traversals.
 *
 * @author Alexander Goda
 */
public class NumberLinkedList {
    private NumberNode head;
    private NumberNode tail;
    private int size;
    private double sum;
    private double sumOfSquares;

    /**
     * Adds a real number to the end of the list.
     *
     * @param value value to store
     */
    public void add(double value) {
        NumberNode newNode = new NumberNode(value);

        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.setNext(newNode);
            tail = newNode;
        }

        size++;
        sum += value;
        sumOfSquares += value * value;
    }

    /**
     * Returns the number of values stored in the list.
     *
     * @return number of values
     */
    public int getSize() {
        return size;
    }

    /**
     * Returns true when the list has no values.
     *
     * @return true if the list is empty
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns a value by its zero-based position in the list.
     *
     * @param index zero-based position
     * @return value at the requested position
     * @throws IndexOutOfBoundsException when index is outside the list
     */
    public double get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of range: " + index);
        }

        NumberNode current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.getValue();
    }

    /**
     * Calculates the arithmetic mean of the stored values.
     *
     * @return mean, or NaN when the list is empty
     */
    public double getMean() {
        if (size == 0) {
            return Double.NaN;
        }
        return sum / size;
    }

    /**
     * Calculates sample standard deviation using n - 1 in the denominator.
     *
     * @return sample standard deviation, or NaN when fewer than two values exist
     */
    public double getSampleStandardDeviation() {
        if (size < 2) {
            return Double.NaN;
        }

        double numerator = sumOfSquares - ((sum * sum) / size);
        if (numerator < 0.0 && numerator > -0.0000000001) {
            numerator = 0.0;
        }
        return Math.sqrt(numerator / (size - 1));
    }
}
