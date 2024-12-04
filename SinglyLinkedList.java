
public class SinglyLinkedList<T> implements DataStructureInterface<T> {

    private SinglyNode<T> head;
    private SinglyNode<T> tail;
    private int length;

    public SinglyLinkedList() {
        this.length = 0;
    }

    public SinglyLinkedList(Iterable<T> list) {
        for (T elem : list) {
            insert(elem);
        }
    }

    public int size() {
        return length;
    }

    @Override
    public final void insert(T value) {
        if (tail == null) {
            head = tail = new SinglyNode<>(value);
        } else {
            tail.right = new SinglyNode<>(value);
            tail = tail.right;
        }
        length++;
    }

    @Override
    public boolean search(T needle) {
        if (head == null) {
            return false;
        }

        SinglyNode<T> curr = head;
        while (curr.right != null) {
            if (curr.value.equals(needle)) {
                return true;
            }
            curr = curr.right;
        }
        return curr.value.equals(needle);
    }

    @Override
    public T removeFirst(T value) {
        if (head == null) {
            return null;
        }

        if (value.equals(head.value)) {
            head = head.right;
            if (head == null) {
                tail = null;
            }
            length--;
            return value;
        }

        SinglyNode<T> curr = head;
        while (curr.right != null) {
            if (value.equals(curr.right.value)) {
                if (curr.right.right == null) {
                    curr.right = null;
                    tail = curr;

                } else {
                    curr.right = curr.right.right;
                }
                length--;
                return value;
            }
            curr = curr.right;
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        return (length == 0);
    }

    public T removeStart() {
        if (head == null) {
            return null;
        }
        T value = head.value;
        head = head.right;
        if (head == null) {
            tail = null;
        }
        length--;
        return value;
    }

    public T removeEnd() {
        if (tail == null) {
            return null;
        }
        if (head == tail) {
            T value = head.value;
            head = tail = null;
            length--;
            return value;
        }

        SinglyNode<T> curr = head;
        while (curr.right != tail) {
            curr = curr.right;
        }
        T value = tail.value;
        tail = curr;
        tail.right = null;

        length--;
        return value;
    }

    @Override
    public void clear() {
        head = tail = null;
        length = 0;
    }

    @Override
    public void display() {
        if (isEmpty()) {
            return;
        }
        SinglyNode<T> curr = head;
        while (curr.right != null) {
            System.out.print(curr.value + " ");
            curr = curr.right;
        }
        System.out.println(curr.value);
    }

    public static void main(String[] args) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        list.insert(1);
        list.insert(4);
        list.insert(2);
        list.insert(3);
        list.display();
        System.out.println(list.size());
        System.out.println(list.removeFirst(2));
        list.display();
        System.out.println(list.size());
        System.out.println(list.search(10));
        System.out.println(list.search(3));
        System.out.println(list.search(4));
        System.out.println(list.search(1));
        System.out.println(list.removeEnd());
        list.display();
        System.out.println(list.removeEnd());
        System.out.println(list.removeEnd());
        System.out.println(list.removeEnd());
        list.display();
        System.out.println(list.size());
        for (int i = 0; i < 20; i++) {
            list.insert(i);
        }
        list.display();
        for (int i = 0; i < 20; i++) {
            System.out.println(list.removeStart());
        }
        list.display();

        SinglyLinkedList<String> strList = new SinglyLinkedList<>();

        // Test 1: Insert elements into the strList
        System.out.println("Test 1: Insert Elements");
        strList.insert("Alice");
        strList.insert("Bob");
        strList.insert("Charlie");
        strList.insert("Alice"); // Duplicate
        System.out.println("Expected size: 4, Actual size: " + strList.size());
        System.out.println();

        // Test 2: Search for elements
        System.out.println("Test 2: Search for Elements");
        System.out.println("Search 'Alice': " + (strList.search("Alice") ? "Found" : "Not Found"));
        System.out.println("Search 'Eve': " + (strList.search("Eve") ? "Found" : "Not Found"));
        System.out.println();

        // Test 3: Remove the start element
        System.out.println("Test 3: Remove Start Element");
        String removedStart = strList.removeStart();
        System.out.println("Removed: " + removedStart);
        System.out.println("Expected size: 3, Actual size: " + strList.size());
        System.out.println();

        // Test 4: Remove the end element
        System.out.println("Test 4: Remove End Element");
        String removedEnd = strList.removeEnd();
        System.out.println("Removed: " + removedEnd);
        System.out.println("Expected size: 2, Actual size: " + strList.size());
        System.out.println();

        // Test 5: Remove the first occurrence of "Alice"
        System.out.println("Test 5: Remove First Occurrence of 'Alice'");
        String removedFirst = strList.removeFirst("Alice");
        System.out.println("Removed 'Alice': " + (removedFirst != null ? "Yes" : "No"));
        System.out.println("Expected size: 1, Actual size: " + strList.size());
        System.out.println();

        // Test 6: Remove a non-existing element
        System.out.println("Test 6: Remove Non-Existing Element 'Eve'");
        String removedNonExistent = strList.removeFirst("Eve");
        System.out.println("Removed 'Eve': " + (removedNonExistent != null ? "Yes" : "No"));
        System.out.println("Expected size: 1, Actual size: " + strList.size());
        System.out.println();

        // Test 7: Check behavior on an empty strList
        System.out.println("Test 7: Remove All Elements and Test Empty List");
        strList.removeStart(); // Remove the last element
        System.out.println("Expected size: 0, Actual size: " + strList.size());
        System.out.println("Remove from empty strList: ");
        try {
            strList.removeStart();
        } catch (Exception e) {
            System.out.println("Caught Exception: " + e.getMessage());
        }
        System.out.println();
    }

    
}
