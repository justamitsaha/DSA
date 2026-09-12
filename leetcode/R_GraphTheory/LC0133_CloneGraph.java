package leetcode.R_GraphTheory;

import java.util.*;

/**
 * LeetCode 130: Clone Graph
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/clone-graph/
 *
 * Algorithm:
 * - Graph Traversal using DFS (or BFS) with a Hash Map / Hash Table
 *
 * Concepts:
 * - Given a reference of a node in a connected undirected graph, return a deep copy (clone) of the graph.
 * - Each node contains a value (`int`) and a list of its neighbors (`List<Node>`).
 * - To prevent infinite loops caused by cycles in an undirected graph, we maintain a hash map
 *   `clonedNodes: Map<Node, Node>` mapping original nodes to their cloned counterparts.
 * - For each node:
 *   1. If it has already been cloned, return the cloned instance from the map.
 *   2. Otherwise, create a new `Node(node.val)`, register it in the map, and recursively clone all
 *      its neighbors, appending them to `clone.neighbors`.
 *
 * Complexity:
 * - Time Complexity:  O(V + E) where V is the number of vertices (nodes) and E is the number of edges.
 *   Every node and edge is traversed once.
 * - Space Complexity: O(V) to store the hash map and the DFS recursion call stack.
 */
public class LC0133_CloneGraph {

    // Definition for a Node in the graph
    public static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            this.val = 0;
            this.neighbors = new ArrayList<>();
        }

        public Node(int val) {
            this.val = val;
            this.neighbors = new ArrayList<>();
        }

        public Node(int val, ArrayList<Node> neighbors) {
            this.val = val;
            this.neighbors = neighbors;
        }
    }

    private Map<Node, Node> clonedNodes;

    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        clonedNodes = new HashMap<>();
        return dfs(node);
    }

    private Node dfs(Node node) {
        if (clonedNodes.containsKey(node)) {
            return clonedNodes.get(node);
        }

        Node clone = new Node(node.val);
        clonedNodes.put(node, clone);

        for (Node neighbor : node.neighbors) {
            clone.neighbors.add(dfs(neighbor));
        }

        return clone;
    }

    public static void main(String[] args) {
        LC0133_CloneGraph solver = new LC0133_CloneGraph();

        // Build a 4-node square graph:
        // 1 -- 2
        // |    |
        // 4 -- 3
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);

        n1.neighbors.addAll(Arrays.asList(n2, n4));
        n2.neighbors.addAll(Arrays.asList(n1, n3));
        n3.neighbors.addAll(Arrays.asList(n2, n4));
        n4.neighbors.addAll(Arrays.asList(n1, n3));

        Node cloned = solver.cloneGraph(n1);

        System.out.println("Original node 1 ref != Cloned node 1 ref: " + (n1 != cloned));
        System.out.println("Cloned node 1 val: " + cloned.val);
        System.out.println("Cloned node 1 neighbors count: " + cloned.neighbors.size());
        System.out.print("Cloned node 1 neighbor values: ");
        for (Node nei : cloned.neighbors) {
            System.out.print(nei.val + " ");
        }
        System.out.println();

        // Edge case: null node
        System.out.println("Clone of null: " + solver.cloneGraph(null));

        // Edge case: single node without neighbors
        Node single = new Node(42);
        Node clonedSingle = solver.cloneGraph(single);
        System.out.println("Single node clone val: " + clonedSingle.val + ", neighbors: " + clonedSingle.neighbors.size());
    }
}
