package statistics;

/**
 * Represents one node in the custom linked list used by Assignment 5.
 *
 * @author Alexander Goda
 */
class NumberNode {
    private final double value;
    private NumberNode next;

    /**
     * Constructs a node containing one real number.
     *
     * @param value number stored in this node
     */
    NumberNode(double value) {
        this.value = value;
    }

    /**
     * Returns the number stored in this node.
     *
     * @return node value
     */
    double getValue() {
        return value;
    }

    /**
     * Returns the next node in the linked list.
     *
     * @return next node, or null at the end of the list
     */
    NumberNode getNext() {
        return next;
    }

    /**
     * Assigns the next node in the linked list.
     *
     * @param next next node
     */
    void setNext(NumberNode next) {
        this.next = next;
    }
}
