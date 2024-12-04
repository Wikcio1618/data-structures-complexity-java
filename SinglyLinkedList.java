
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
            tail.next = new SinglyNode<>(value);
            tail = tail.next;
        }
        length++;
    }

    @Override
    public boolean search(T needle) {
        if (head == null) {
            return false;
        }

        SinglyNode<T> curr = head;
        while (curr.next != null) {
            if (curr.value.equals(needle)) {
                return true;
            }
            curr = curr.next;
        }
        return curr.value.equals(needle);
    }

    @Override
    public T removeFirst(T value) {
        if (head == null) {
            return null;
        }

        if (value.equals(head.value)) {
            head = head.next;
            if (head == null) {
                tail = null;
            }
            length--;
            return value;
        }

        SinglyNode<T> curr = head;
        while (curr.next != null) {
            if (value.equals(curr.next.value)) {
                if (curr.next.next == null) {
                    curr.next = null;
                    tail = curr;

                } else {
                    curr.next = curr.next.next;
                }
                length--;
                return value;
            }
            curr = curr.next;
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
        head = head.next;
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
        while (curr.next != tail) {
            curr = curr.next;
        }
        T value = tail.value;
        tail = curr;
        tail.next = null;

        length--;
        return value;
    }

    @Override
    public void display() {
        if (isEmpty()) {
            return;
        }
        SinglyNode<T> curr = head;
        while (curr.next != null) {
            System.out.print(curr.value + " ");
            curr = curr.next;
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
    }
}
