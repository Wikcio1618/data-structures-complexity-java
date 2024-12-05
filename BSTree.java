
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
        return (DFSearchRec(root, needle) != null);
    }

    private BinaryNode<T> DFSearchRec(BinaryNode<T> curr, T needle) {
        if (curr == null) {
            return null;
        }
        if (curr.value.equals(needle)) {
            return curr;
        }
        if (needle.compareTo(curr.value) < 0) {
            return DFSearchRec(curr.left, needle);
        } else {
            return DFSearchRec(curr.right, needle);
        }
    }

    @Override
    public T removeFirst(T value) {
        root = removeRec(root, value);
        return root == null ? null : root.value;
    }

    private BinaryNode<T> removeRec(BinaryNode<T> curr, T value) {
        if (curr == null) {
            return null;
        }
        if (value.compareTo(curr.value) < 0) {
            curr.left = removeRec(curr.left, value);
        } else if (value.compareTo(curr.value) > 0) {
            curr.right = removeRec(curr.right, value);
        } else {

            if (curr.left == null) {
                return curr.right;
            } else if (curr.right == null) {
                return curr.left;
            } else {
                BinaryNode<T> predNode = findPredeccessor(curr);
                curr.value = predNode.value;
                curr.left = removeRec(curr.left, predNode.value);
            }
        }
        return curr;
    }

    private BinaryNode<T> findPredeccessor(BinaryNode<T> node) {
        assert node.left != null;
        node = node.left;
        while (node.right != null) {
            node = node.right;
        }
        return node;
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
        System.out.println();
        tree.display();

        // for (BinaryNode<Integer> node : tree.traverse(TraversalOrder.PRE)) {
        //     System.out.print(node.value + " ");
        // }
        // System.out.println();
        // for (BinaryNode<Integer> node : tree.traverse(TraversalOrder.POST)) {
        //     System.out.print(node.value + " ");
        // }
        tree.removeFirst(6);
        tree.display();
        
        tree.clear();
        tree.insert(50);
        tree.insert(30);
        tree.insert(70);
        tree.insert(20);
        tree.insert(40);
        tree.insert(60);
        tree.insert(80);

        System.out.println("Original Tree (In-order):");
        tree.display(); // Expected: 20, 30, 40, 50, 60, 70, 80

        // Test removal of a leaf node
        System.out.println("\nRemoving leaf node 20...");
        tree.removeFirst(20);
        tree.display(); // Expected: 30, 40, 50, 60, 70, 80

        // Test removal of a node with one child
        System.out.println("\nRemoving node with one child 30...");
        tree.removeFirst(30);
        tree.display(); // Expected: 40, 50, 60, 70, 80

        // Test removal of a node with two children
        System.out.println("\nRemoving node with two children 50...");
        tree.removeFirst(50);
        tree.display(); // Expected: 40, 60, 70, 80

        // Test removing a non-existent value
        System.out.println("\nRemoving non-existent node 100...");
        tree.removeFirst(100);
        tree.display(); // Expected: 40, 60, 70, 80 (unchanged)

        // Test removing all nodes
        System.out.println("\nRemoving all nodes...");
        tree.removeFirst(40);
        tree.removeFirst(60);
        tree.removeFirst(70);
        tree.removeFirst(80);
        tree.display(); // Expected: (empty tree)
    }

}
