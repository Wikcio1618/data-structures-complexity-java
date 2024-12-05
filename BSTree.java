
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
    public T deleteFirst(T value) {
        root = deleteRec(root, value);
        return root == null ? null : root.value;
    }

    private BinaryNode<T> deleteRec(BinaryNode<T> curr, T value) {
        if (curr == null) {
            return null;
        }
        if (value.compareTo(curr.value) < 0) {
            curr.left = deleteRec(curr.left, value);
        } else if (value.compareTo(curr.value) > 0) {
            curr.right = deleteRec(curr.right, value);
        } else {

            if (curr.left == null) {
                return curr.right;
            } else if (curr.right == null) {
                return curr.left;
            } else {
                BinaryNode<T> predNode = findPredeccessor(curr);
                curr.value = predNode.value;
                curr.left = deleteRec(curr.left, predNode.value);
            }
        }
        return curr;
    }

    public int getHeight() {
        if (root == null) {
            return 0;
        }
        return heightRec(root, 1);
    }

    private int heightRec(BinaryNode<T> curr, int currHeight) {
        if (curr == null) {
            return currHeight - 1;
        }
        return Math.max(
                heightRec(curr.left, currHeight + 1),
                heightRec(curr.right, currHeight + 1)
        );
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
        System.out.println(tree.getHeight());

        // for (BinaryNode<Integer> node : tree.traverse(TraversalOrder.PRE)) {
        //     System.out.print(node.value + " ");
        // }
        // System.out.println();
        // for (BinaryNode<Integer> node : tree.traverse(TraversalOrder.POST)) {
        //     System.out.print(node.value + " ");
        // }
        tree.deleteFirst(1);
        System.out.println(tree.getHeight());
        tree.deleteFirst(6);
        System.out.println(tree.getHeight());
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
        tree.deleteFirst(20);
        tree.display(); // Expected: 30, 40, 50, 60, 70, 80

        // Test removal of a node with one child
        System.out.println("\nRemoving node with one child 30...");
        tree.deleteFirst(30);
        tree.display(); // Expected: 40, 50, 60, 70, 80

        // Test removal of a node with two children
        System.out.println("\nRemoving node with two children 50...");
        tree.deleteFirst(50);
        tree.display(); // Expected: 40, 60, 70, 80

        // Test removing a non-existent value
        System.out.println("\nRemoving non-existent node 100...");
        tree.deleteFirst(100);
        tree.display(); // Expected: 40, 60, 70, 80 (unchanged)

        // Test removing all nodes
        System.out.println("\nRemoving all nodes...");
        tree.deleteFirst(40);
        tree.deleteFirst(60);
        tree.deleteFirst(70);
        tree.deleteFirst(80);
        tree.display(); // Expected: (empty tree)
    }

}
