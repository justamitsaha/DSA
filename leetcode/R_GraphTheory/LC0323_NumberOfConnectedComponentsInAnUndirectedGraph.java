package leetcode.R_GraphTheory;

import java.util.*;

/**
 * LeetCode 323: Number of Connected Components in an Undirected Graph
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/
 *
 * Algorithm:
 * - Connected Components via DFS / BFS / Disjoint Set Union (Union-Find)
 *
 * Concepts:
 * - Given `n` nodes labeled `0` to `n - 1` and a list of undirected edges:
 *   Return the number of connected components in the graph.
 * - Approach 1: Depth-First Search (DFS)
 *   - Build an adjacency list for all `n` nodes.
 *   - Iterate from `0` to `n - 1`. If node `i` is not yet visited:
 *     - Increment `components`.
 *     - Run DFS starting at `i` to mark all nodes in the same component as visited.
 * - Approach 2: Union-Find (DSU)
 *   - Initialize `components = n` with each node as its own parent.
 *   - For each edge `(u, v)`:
 *     - If `find(u) != find(v)`, union them and decrement `components--`.
 *
 * Complexity:
 * - Time Complexity:  O(V + E) for DFS; O(V + E * alpha(V)) for Union-Find with path compression.
 * - Space Complexity: O(V + E) for adjacency list and recursion stack / parent array.
 */
public class LC0323_NumberOfConnectedComponentsInAnUndirectedGraph {

    // Approach 1: DFS
    public int countComponents(int n, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        boolean[] visited = new boolean[n];
        int count = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                count++;
                dfs(adj, visited, i);
            }
        }

        return count;
    }

    private void dfs(List<List<Integer>> adj, boolean[] visited, int node) {
        visited[node] = true;
        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                dfs(adj, visited, neighbor);
            }
        }
    }

    // Approach 2: Disjoint Set Union (Union-Find)
    public int countComponentsUnionFind(int n, int[][] edges) {
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        int count = n;
        for (int[] edge : edges) {
            int rootU = find(parent, edge[0]);
            int rootV = find(parent, edge[1]);

            if (rootU != rootV) {
                parent[rootU] = rootV;
                count--;
            }
        }

        return count;
    }

    private int find(int[] parent, int i) {
        if (parent[i] != i) {
            parent[i] = find(parent, parent[i]); // Path compression
        }
        return parent[i];
    }

    public static void main(String[] args) {
        LC0323_NumberOfConnectedComponentsInAnUndirectedGraph solver =
            new LC0323_NumberOfConnectedComponentsInAnUndirectedGraph();

        // Scenario 1: 5 nodes, 2 components: {0-1-2} and {3-4}
        int[][] edges1 = {{0, 1}, {1, 2}, {3, 4}};
        System.out.println("Components (DFS): " + solver.countComponents(5, edges1));           // Expected: 2
        System.out.println("Components (DSU): " + solver.countComponentsUnionFind(5, edges1)); // Expected: 2

        // Scenario 2: 5 nodes, 1 component: {0-1-2-3-4}
        int[][] edges2 = {{0, 1}, {1, 2}, {2, 3}, {3, 4}};
        System.out.println("Components (DFS): " + solver.countComponents(5, edges2));           // Expected: 1

        // Scenario 3: 4 nodes, no edges -> 4 components
        int[][] edges3 = {};
        System.out.println("Components (empty edges): " + solver.countComponents(4, edges3));   // Expected: 4
    }
}
