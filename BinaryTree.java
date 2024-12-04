
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public abstract class BinaryTree<T> {

    BinaryNode<T> root;

    public enum TraversalOrder {
        PRE,
        IN,
        POST
    }

    public BinaryTree() {

    }

    public List<BinaryNode<T>> traverse(TraversalOrder order) {
        List<BinaryNode<T>> result = new ArrayList<>();
        traverseRec(root, result, order);
        
        return result;
    }

    private void traverseRec(BinaryNode<T> curr, List<BinaryNode<T>> path, TraversalOrder order) {
        if (curr == null) {
            return;
        }
        
        switch (order) {
            case PRE -> {
                path.add(curr);
                traverseRec(curr.left, path, order);
                traverseRec(curr.right, path, order);
            }
            case IN -> {
                System.out.println("There");
                traverseRec(curr.left, path, order);
                path.add(curr);
                traverseRec(curr.right, path, order);
            }
            case POST -> {
                traverseRec(curr.left, path, order);
                traverseRec(curr.right, path, order);
                path.add(curr);
            }
            default -> {
            }
        }
    }

    public List<BinaryNode<T>> BFTraverse() {
        List<BinaryNode<T>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<BinaryNode<T>> q = new LinkedList<>();
        q.add(root);
        BinaryNode<T> curr;
        while (!q.isEmpty()) {
            curr = q.poll();
            result.add(curr);
            if (curr.left != null) {
                q.add(curr.left);
            }
            if (curr.right != null) {
                q.add(curr.right);
            }
        }

        return result;
    }
}
