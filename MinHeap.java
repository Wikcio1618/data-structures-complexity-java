
@SuppressWarnings("unchecked")
public class MinHeap<T extends Comparable<T>> implements DataStructureInterface<T> {

    // Fields
    private T[] heap;  // Array representation of the heap
    private int size;    // Current number of elements in the heap
    private int capacity = 10; // Maximum capacity of the heap

    // Constructor
    public MinHeap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.heap = (T[]) new Comparable[capacity];
    }

    public MinHeap() {
        this.size = 0;
        this.heap = (T[]) new Comparable[capacity];
    }

    // Method Signatures
    /**
     * Inserts a new value into the heap.
     *
     * @param value The value to insert.
     */
    @Override
    public void insert(T value) {
        heap[size] = value;
        heapifyUp(size);

        size++;
        if (size >= capacity) {
            resize();
        }
    }

    @Override
    public boolean search(T needle) {
        for (int i = 0; i < size; i++) {
            if (heap[i] == needle) {
                return true;  // Found the needle
            }
        }
        return false;  // Needle not found
    }

    @Override
    public T deleteFirst(T value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void display() {
        if (size == 0) {
            System.out.println("Heap is empty.");
            return;
        }

        System.out.println("Heap in pyramid shape:");

        int level = 0; // Current level in the heap
        int elementsInLevel = 1; // Number of elements in the current level
        int index = 0; // Current index in the heap array

        while (index < size) {
            // Print leading spaces for the level
            int spaces = (1 << (size - level - 1)); // Adjust spacing based on level
            printSpaces(spaces);

            // Print all elements in this level
            for (int i = 0; i < elementsInLevel && index < size; i++) {
                System.out.print(heap[index++] + " ");
                printSpaces(spaces * 2 - 1); // Adjust spacing between elements
            }

            System.out.println(); // Newline after printing the level

            level++; // Move to the next level
            elementsInLevel *= 2; // Double the elements in the next level
        }
    }

// Helper function to print spaces
    private void printSpaces(int count) {
        for (int i = 0; i < count; i++) {
            System.out.print(" ");
        }
    }

    @Override
    public void clear() {
        heap = (T[]) new Comparable[capacity];
        size = 0;
    }

    /**
     * Removes and returns the root element (minimum for MinHeap or maximum for
     * MaxHeap).
     *
     * @return The root element of the heap. or `null` if the heap is empty
     */
    public T removeRoot() {
        if (size == 0) {
            return null;
        }
        T rootValue = heap[0];
        heap[0] = heap[size - 1];
        heapifyDown(0);
        size--;
        return rootValue;
    }

    /**
     * Returns the root element without removing it.
     *
     * @return The root element of the heap. or `null` if the heap is empty
     */
    public T peekRoot() {
        if (size == 0) {
            return null;
        }
        return heap[0];
    }

    /**
     * Returns the number of elements in the heap.
     *
     * @return The size of the heap.
     */
    public int getSize() {
        return size;
    }

    /**
     * Checks if the heap is empty.
     *
     * @return true if the heap is empty, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;

    }

    /**
     * Performs heapify operation starting from a given index.
     *
     * @param index The index from which to start heapifying.
     */
    private void heapifyDown(int index) {
        if (index >= size) {
            return;
        }
        int leftIdx = 2 * index + 1;
        int rightIdx = 2 * index + 2;
        int swapIdx = index;
        if (leftIdx < size && heap[index].compareTo(heap[leftIdx]) > 0) {
            swapIdx = leftIdx;
        }

        if (rightIdx < size && heap[swapIdx].compareTo(heap[rightIdx]) > 0) {
            swapIdx = rightIdx;
        }
        if (swapIdx != index) {
            swap(index, swapIdx);
            heapifyDown(swapIdx);
        }
    }

    /**
     * Performs bubble-up operation to maintain heap properties after insertion.
     *
     * @param index The index to start bubbling up from.
     */
    private void heapifyUp(int index) {
        if (index <= 0) {
            return;
        }
        int parentIdx = (index - 1) / 2;
        if (heap[index].compareTo(heap[parentIdx]) < 0) {
            swap(index, parentIdx);
            heapifyUp(parentIdx);
        }
    }

    /**
     * Resizes the heap when the capacity is exceeded.
     */
    private void resize() {
        capacity = capacity * 2;
        T[] newHeap = (T[]) new Comparable[capacity];
        System.arraycopy(heap, 0, newHeap, 0, size);
        heap = newHeap;
    }

    /**
     * Swaps two elements in the heap.
     *
     * @param i Index of the first element.
     * @param j Index of the second element.
     */
    private void swap(int i, int j) {
        T temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    public static void main(String[] args) {
        // Create a MinHeap instance
        MinHeap<Integer> minHeap = new MinHeap<>();

        // Test: Insert elements into the heap
        System.out.println("Inserting elements into the heap...");
        minHeap.insert(10);
        minHeap.insert(5);
        minHeap.insert(20);
        minHeap.insert(3);
        minHeap.insert(8);
        minHeap.insert(2);
        minHeap.insert(15);
        minHeap.display();

        // Test: Peek at the root (minimum element)
        System.out.println("\nThe root element (min): " + minHeap.peekRoot());

        // Test: Remove the root and display the heap
        System.out.println("\nRemoving the root...");
        minHeap.removeRoot();
        minHeap.display();

        // Test: Size of the heap
        System.out.println("\nSize of the heap: " + minHeap.getSize());

        // Test: Remove all elements one by one
        System.out.println("\nRemoving all elements:");
        while (!minHeap.isEmpty()) {
            System.out.println("Removed: " + minHeap.removeRoot());
            minHeap.display();
        }

        // Test: Clear the heap and check if empty
        System.out.println("\nClearing the heap...");
        minHeap.clear();
        System.out.println("Heap cleared. Is the heap empty? " + minHeap.isEmpty());
    }

}
