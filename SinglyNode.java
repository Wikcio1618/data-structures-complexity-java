
public class SinglyNode<T> extends Node<T> {

    public SinglyNode<T> next;

    public SinglyNode(T value) {
        super(value);
    }

    public SinglyNode(T value, SinglyNode<T> next) {
        super(value);
        this.next = next;
    }

}
