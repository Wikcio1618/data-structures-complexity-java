
import java.util.List;

public class BSTree<T extends Comparable<T>> extends BinaryTree<T> implements DataStructureInterface<T> {

    public BSTree() {
    }

    @Override
    public void insert(T value) {

        if (root == null) {
            root = new BinaryNode<>(value);
            return;
        }
        insertRec(root, value);
    }

    private void insertRec(BinaryNode<T> curr, T value) {
        if (value.compareTo((T) curr.value) <= 0) {
            if (curr.left == null) {
                curr.left = new BinaryNode<>(value);
            } else {
                insertRec(curr.left, value);
            }
        } else {
            if (curr.right == null) {
                curr.right = new BinaryNode<>(value);
            } else {
                insertRec(curr.right, value);
            }
        }
    }

    @Override
    public boolean search(T needle) {
        return DFSearchRec(root, needle);
    }

    private boolean DFSearchRec(BinaryNode<T> curr, T needle) {
        if (curr == null) {
            return false;
        }
        if (curr.value.equals(needle)) {
            return true;
        }
        if (needle.compareTo(curr.value) < 0) {
            return DFSearchRec(curr.left, needle);
        } else {
            return DFSearchRec(curr.right, needle);
        }
    }

    @Override
    public T removeFirst(T value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isEmpty() {
        return (root == null);
    }

    @Override
    public void display() {
        List<BinaryNode<T>> list = traverse(TraversalOrder.IN);
        for (BinaryNode<T> node : list) {
            System.out.print(node.value + " ");
        }
        System.out.println();
    }

    @Override
    public void clear() {
        root = null;
    }

    public static void main(String[] args) {
        BSTree<Integer> tree = new BSTree<>();
        tree.insert(6);
        tree.insert(4);
        tree.insert(5);
        tree.insert(11);
        tree.insert(3);
        tree.insert(15);
        tree.insert(10);
        tree.insert(2);
        tree.insert(1);
        tree.insert(13);

        tree.display();

        for (BinaryNode<Integer> node : tree.traverse(TraversalOrder.PRE)) {
            System.out.print(node.value + " ");
        }
        System.out.println();
        for (BinaryNode<Integer> node : tree.traverse(TraversalOrder.POST)) {
            System.out.print(node.value + " ");
        }
    }

}
