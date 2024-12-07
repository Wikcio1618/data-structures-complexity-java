
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class UnweighedListGraph<T> implements DataStructureInterface<T> {

    protected Map<T, List<T>> adjList = new HashMap<>();
    private boolean isDirected = false;

    public UnweighedListGraph() {

    }

    public UnweighedListGraph(boolean isDirected) {
        this.isDirected = isDirected;
    }

    void addVertex(T vertex) {
        adjList.putIfAbsent(vertex, new ArrayList<>());
    }

    void addEdge(T source, T destination) {
        addVertex(source);
        addVertex(destination);
        adjList.get(source).add(destination);
        if (!isDirected) {
            adjList.get(destination).add(source);
        }

    }

    List<T> getVertices() {
        return new ArrayList<>(adjList.keySet());
    }

    List<T> getNeis(T vertex) {
        return adjList.getOrDefault(vertex, Collections.emptyList());
    }

    public List<T> traverse(SearchType type) {
        assert (type == SearchType.BFS || type == SearchType.DFS);
        List<T> result = new ArrayList<>();
        LinkedList<T> list = new LinkedList<>();
        Set<T> visited = new HashSet<>();

        List<T> vertices = getVertices();
        for (T start : vertices) {
            if (!visited.contains(start)) {
                list.add(start);
                visited.add(start);

                while (!list.isEmpty()) {
                    T curr = (type == SearchType.BFS) ? list.poll() : list.pollLast();
                    result.add(curr);

                    for (T nei : getNeis(curr)) {
                        if (!visited.contains(nei)) {
                            visited.add(nei);
                            list.add(nei);
                        }
                    }
                }
            }
        }

        return result;
    }


    @Override
    public void insert(T value) {
        this.addVertex(value);
    }

    @Override
    public boolean search(T value) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'search'");
    }

    @Override
    public T deleteFirst(T value) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteFirst'");
    }

    @Override
    public boolean isEmpty() {
        return adjList.isEmpty();
    }

    @Override
    public void display() {
        for (Entry<T, List<T>> entry : adjList.entrySet()) {
            System.out.print(entry.getKey() + ": ");
            for (T elem : entry.getValue()) {
                System.out.print(elem + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    @Override
    public void clear() {
        adjList = new HashMap<>();
    }

    public boolean isIsDirected() {
        return isDirected;
    }

    public static void main(String[] args) {
        // Create a graph instance
        UnweighedListGraph<String> graph = new UnweighedListGraph<>();

        // Add edges (two disconnected components)
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("C", "D");
        graph.addEdge("C", "E");

        graph.addEdge("A", "Z");
        graph.addEdge("Z", "Y");
        graph.addEdge("Y", "X");

        graph.display();

        // Perform BFS traversal
        System.out.println("BFS Traversal (expected order may vary):");
        System.out.println(graph.traverse(SearchType.BFS));

        // Perform DFS traversal
        System.out.println("DFS Traversal (expected order may vary):");
        System.out.println(graph.traverse(SearchType.DFS));
    }
}
