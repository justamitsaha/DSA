package leetcode.R_GraphTheory;

import java.util.*;

/**
 * LeetCode 743: Network Delay Time
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/network-delay-time/
 *
 * Algorithm:
 * - Dijkstra's Shortest Path Algorithm (Single-Source Shortest Path with Non-Negative Weights)
 *
 * Concepts:
 * - Given a network of `n` nodes (labeled 1 to n) and a list of travel times `times[i] = (u, v, w)`,
 *   where `u` is the source node, `v` is the target node, and `w` is the signal travel time.
 * - We send a signal from a given node `k`. Return the minimum time it takes for ALL `n` nodes to receive the signal.
 *   If it is impossible for all `n` nodes to receive the signal, return -1.
 * - Strategy:
 *   1. Build adjacency list of directed weighted edges.
 *   2. Maintain a `dist` array initialized to infinity, with `dist[k] = 0`.
 *   3. Use a min-priority queue ordered by path distance: `(node, currentDist)`.
 *   4. Greedily relax outgoing edges. If a shorter path to neighbor `v` is found, update `dist[v]` and enqueue.
 *   5. The final answer is the maximum distance among all nodes from 1 to n. If any node remains unreachable (infinity), return -1.
 *
 * Complexity:
 * - Time Complexity:  O(E log V) where E is the number of edges and V is the number of nodes (using a binary heap).
 * - Space Complexity: O(V + E) for the adjacency list and priority queue.
 */
public class LC0743_NetworkDelayTime {

    // Helper representation for directed weighted edge
    private static class Edge {
        int to;
        int weight;

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    public int networkDelayTime(int[][] times, int n, int k) {
        // Build adjacency list (1-indexed nodes)
        List<List<Edge>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] time : times) {
            int u = time[0];
            int v = time[1];
            int w = time[2];
            adj.get(u).add(new Edge(v, w));
        }

        // Distance array
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[k] = 0;

        // Min-heap storing int[]{node, currentDistance}
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[]{k, 0});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int u = curr[0];
            int d = curr[1];

            // If we found a longer path than already recorded, skip
            if (d > dist[u]) {
                continue;
            }

            for (Edge edge : adj.get(u)) {
                int v = edge.to;
                int weight = edge.weight;

                if (dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    pq.offer(new int[]{v, dist[v]});
                }
            }
        }

        // Find the maximum time to reach any node
        int maxDelay = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                return -1; // Unreachable node
            }
            maxDelay = Math.max(maxDelay, dist[i]);
        }

        return maxDelay;
    }

    public static void main(String[] args) {
        LC0743_NetworkDelayTime solver = new LC0743_NetworkDelayTime();

        // Scenario 1: Standard network
        // 2 -> 1 (w=1), 2 -> 3 (w=1), 3 -> 4 (w=1), start at k=2
        int[][] times1 = {{2, 1, 1}, {2, 3, 1}, {3, 4, 1}};
        System.out.println("Max delay 1: " + solver.networkDelayTime(times1, 4, 2)); // Expected: 2

        // Scenario 2: Node 2 unreachable
        int[][] times2 = {{1, 2, 1}};
        System.out.println("Max delay 2: " + solver.networkDelayTime(times2, 2, 2)); // Expected: -1

        // Scenario 3: Single node network
        int[][] times3 = {};
        System.out.println("Max delay 3: " + solver.networkDelayTime(times3, 1, 1)); // Expected: 0
    }
}
