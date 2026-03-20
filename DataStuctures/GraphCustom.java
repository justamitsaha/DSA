
import java.util.*;

/**
 * Custom Graph Implementation using Adjacency List.
 * 
 * @param <T> the type of elements in this graph
 */
public class GraphCustom<T> {
    private Map<T, List<T>> adjacencyList;

    public GraphCustom() {
        this.adjacencyList = new HashMap<>();
    }

    public void addVertex(T vertex) {
        adjacencyList.putIfAbsent(vertex, new ArrayList<>());
    }

    public void addEdge(T source, T destination, boolean bidirectional) {
        addVertex(source);
        addVertex(destination);
        adjacencyList.get(source).add(destination);
        if (bidirectional) {
            adjacencyList.get(destination).add(source);
        }
    }

    public void bfs(T start) {
        Set<T> visited = new HashSet<>();
        Queue<T> queue = new LinkedList<>();

        visited.add(start);
        queue.add(start);

        System.out.print("BFS: ");
        while (!queue.isEmpty()) {
            T vertex = queue.poll();
            System.out.print(vertex + " ");

            List<T> neighbors = adjacencyList.get(vertex);
            if (neighbors != null) {
                for (T neighbor : neighbors) {
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        queue.add(neighbor);
                    }
                }
            }
        }
        System.out.println();
    }

    public void dfs(T start) {
        Set<T> visited = new HashSet<>();
        System.out.print("DFS: ");
        dfsHelper(start, visited);
        System.out.println();
    }

    private void dfsHelper(T vertex, Set<T> visited) {
        visited.add(vertex);
        System.out.print(vertex + " ");

        List<T> neighbors = adjacencyList.get(vertex);
        if (neighbors != null) {
            for (T neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    dfsHelper(neighbor, visited);
                }
            }
        }
    }

    public static void main(String[] args) {
        GraphCustom<Integer> graph = new GraphCustom<>();
        graph.addEdge(0, 1, true);
        graph.addEdge(0, 4, true);
        graph.addEdge(1, 2, true);
        graph.addEdge(1, 3, true);
        graph.addEdge(1, 4, true);
        graph.addEdge(2, 3, true);
        graph.addEdge(3, 4, true);

        graph.bfs(0);
        graph.dfs(0);
    }
}
