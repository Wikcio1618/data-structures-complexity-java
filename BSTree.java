
import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("unchecked")
public class BSTree<T extends Comparable<T>> {

    private BinaryNode<T> root = null;

    public BSTree() {
    }

    public BSTree(BinaryNode<T> root) {
        this.root = root;
    }

    public void insert(T value) {
        if (root == null) {
            root = new BinaryNode<>(value);
        }
        add(root, value);
    }

    public List<BinaryNode<T>> preOrderTraverse(List<BinaryNode<T>> result) {
        BinaryNode<T> curr = root;
        return result;
    }

    public List<T> inOrderTraverse() {
        List<T> result = new LinkedList<>();
        walkInOrder(root, result);
        return result;
    }

    public List<BinaryNode<T>> postOrderTraverse(List<BinaryNode<T>> result) {
        BinaryNode<T> curr = root;
        return result;
    }

    // public List<T> BFTraverse() {
    //     List<T> result = new LinkedList<>();
    //     Queue<T> queue = new LinkedList<>();



    //     return result;
    // }

    private void walkInOrder(BinaryNode<T> root, List<T> path) {
        if (root == null) {
            return;
        }

        walkInOrder(root.left, path);
        path.add((T) root.value);
        walkInOrder(root.right, path);
    }

    private BinaryNode<T> add(BinaryNode<T> root, T value) {
        if (root == null) {
            return new BinaryNode<>(value);
        }
        if (value.compareTo((T) root.value) <= 0) {
            root.left = add(root.left, value);
        } else {
            root.right = add(root.right, value);
        }
        return null;
    }

    public static void main(String[] args) {
        BSTree<Integer> tree = new BSTree<>();
        tree.insert(6);
        tree.insert(5);
        tree.insert(11);
        tree.insert(3);
        tree.insert(15);

        List<Integer> list = tree.inOrderTraverse();
        for (Integer x : list) {
            System.out.print(x + ", ");
        }
    }
}
