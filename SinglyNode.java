
public class SinglyNode<T> extends Node<T> {

    public SinglyNode<T> right;

    public SinglyNode(T value) {
        super(value);
    }

    public SinglyNode(T value, SinglyNode<T> right) {
        super(value);
        this.right = right;
    }

}
