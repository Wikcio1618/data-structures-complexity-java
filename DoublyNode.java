
public class DoublyNode<T> extends SinglyNode<T> {

    public DoublyNode<T> prev;

    public DoublyNode(T value) {
        super(value);
    }

    public DoublyNode(T value, DoublyNode<T> prev, DoublyNode<T> next) {
        super(value, next);
        this.prev = prev;
    }

}
