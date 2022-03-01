package team3InfixParser;

/** A linked-list stack
@param <T>: Object type
*/
public class Stack<T> {
/** A singly-linked list node */
private class Node {
    // Data fields
    T data;
    Node next;
    // Constructor
    Node(T value) { data = value; }
}

// Data fields
private Node top;
private int numOfItems;

// Constructors

public Stack() {}  // Default constructor

public Stack(Stack<T> other) {  // Copy constructor
    numOfItems = other.numOfItems;
    if (numOfItems != 0) {
        top = new Node(other.top.data);
        Node p = top, q = other.top.next;
        while (q != null) {
            p.next = new Node(q.data);
            p = p.next;
            q = q.next;
        }
    }
}

// Methods

/** Returns the size of the stack.
    @return: number of items stored in the stack
*/
public int size() { return numOfItems; }  // Time complexity: O(1)

/** Tests whether the stack is empty.
    @return: {true} if the stack is empty; {false} otherwise
*/
public boolean isEmpty() { return size() == 0; }  // Time complexity: O(1)

/** Returns the element on top of the stack.
    @return: the top element
    @throws IllegalArgumentException: stack is empty.
*/
public T peek() {
    if (isEmpty()) { throw new IllegalArgumentException("Attempt to access item in empty stack."); }
    return top.data;
}  // Time complexity: O(1)

/** Removes and returns the element on top of the stack.
    @return: the top element removed
    @throws IllegalArgumentException: stack is empty.
*/
public T pop() {
    if (isEmpty()) { throw new IllegalArgumentException("Attempt to remove item from empty stack."); }
    T toBeRemoved = top.data;
    top = top.next;
    numOfItems--;
    return toBeRemoved;
}  // Time complexity: O(1)

/** Adds a new item onto the top of the stack.
    @param item: the item to push
*/
public void push(T item) {
    Node newTop = new Node(item);
    newTop.next = top;
    top = newTop;
    numOfItems++;
}  // Time complexity: O(1)
}