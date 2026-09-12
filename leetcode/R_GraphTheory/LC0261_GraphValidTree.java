package leetcode.R_GraphTheory;

import java.util.*;

/**
 * LeetCode 261: Graph Valid Tree
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/graph-valid-tree/
 *
 * Algorithm:
 * - Graph Tree Properties: Connected & Acyclic (Undirected Graph)
 * - Approach 1: Depth-First Search (DFS) with parent tracking
 * - Approach 2: Disjoint Set Union (Union-Find)
 *
 * Concepts:
 * - Given `n` nodes labeled from `0` to `n - 1` and a list of undirected edges:
 *   A graph is a valid tree if and only if:
 *   1. It has exactly `n - 1` edges (`edges.length == n - 1`). If not, it either contains a cycle or is disconnected.
 *   2. It contains no cycles and is fully connected (all `n` nodes belong to a single connected component).
 * - DFS Approach:
 *   - Check `edges.length == n - 1`.
 *   - Start DFS from node `0`, passing `parent` to prevent immediate back-traversal over the undirected edge.
 *   - If an already-visited neighbor is not the parent, a cycle is present.
 *   - Check if the number of visited nodes equals `n`.
 *
 * Complexity:
 * - Time Complexity:  O(V + E) = O(n) because E = n - 1.
 * - Space Complexity: O(V + E) = O(n) for the adjacency list and recursion stack / DSU structures.
 */
public class LC0261_GraphValidTree {

    // Approach 1: DFS with parent tracking
    public boolean validTree(int n, int[][] edges) {
        // Condition 1: A tree with n nodes MUST have exactly n - 1 edges
        if (edges.length != n - 1) {
            return false;
        }

        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        Set<Integer> visited = new HashSet<>();

        // Check acyclic starting from node 0
        if (!dfs(adj, visited, 0, -1)) {
            return false;
        }

        // Check connectivity
        return visited.size() == n;
    }

    private boolean dfs(List<List<Integer>> adj, Set<Integer> visited, int node, int parent) {
        visited.add(node);

        for (int neighbor : adj.get(node)) {
            if (neighbor == parent) {
                continue; // Skip undirected trivial back-edge to parent
            }

            if (visited.contains(neighbor)) {
                return false; // Cycle detected
            }

            if (!dfs(adj, visited, neighbor, node)) {
                return false;
            }
        }

        return true;
    }

    // Approach 2: Union-Find (Disjoint Set)
    public boolean validTreeUnionFind(int n, int[][] edges) {
        if (edges.length != n - 1) {
            return false;
        }

        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for (int[] edge : edges) {
            int rootU = find(parent, edge[0]);
            int rootV = find(parent, edge[1]);

            if (rootU == rootV) {
                return false; // Cycle detected: both nodes already in the same component
            }
            parent[rootU] = rootV; // Union
        }

        return true;
    }

    private int find(int[] parent, int i) {
        if (parent[i] != i) {
            parent[i] = find(parent, parent[i]); // Path compression
        }
        return parent[i];
    }

    public static void main(String[] args) {
        LC0261_GraphValidTree solver = new LC0261_GraphValidTree();

        // Scenario 1: Valid tree
        // 0 - 1 - 2
        // |   |
        // 3   4 (n=5, edges=4)
        int[][] edges1 = {{0, 1}, {0, 2}, {0, 3}, {1, 4}};
        System.out.println("Edges 1 is valid tree (DFS): " + solver.validTree(5, edges1));           // Expected: true
        System.out.println("Edges 1 is valid tree (DSU): " + solver.validTreeUnionFind(5, edges1)); // Expected: true

        // Scenario 2: Graph with cycle
        // 0 - 1 - 2
        // | \ |
        // 3   4
        int[][] edges2 = {{0, 1}, {1, 2}, {2, 3}, {1, 3}, {1, 4}};
        System.out.println("Edges 2 is valid tree (DFS): " + solver.validTree(5, edges2));           // Expected: false

        // Scenario 3: Disconnected graph
        int[][] edges3 = {{0, 1}, {2, 3}};
        System.out.println("Edges 3 is valid tree (DFS): " + solver.validTree(4, edges3));           // Expected: false
    }
}
