public class BinaryNode<T> {
    public T value;
    public BinaryNode<T> left;
    public BinaryNode<T> right;

    public BinaryNode(T val) {
        this.value = val;
    }

    public BinaryNode(T val, BinaryNode<T> left, BinaryNode<T> right) {
        this.left = left;
        this.right = right;
    }
}
