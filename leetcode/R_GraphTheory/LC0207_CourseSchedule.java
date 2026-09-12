package leetcode.R_GraphTheory;

import java.util.*;

/**
 * LeetCode 207: Course Schedule
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/course-schedule/
 *
 * Algorithm:
 * - Topological Sort / Directed Cycle Detection
 * - Approach 1: 3-State Depth-First Search (DFS)
 * - Approach 2: Kahn's Algorithm (BFS using Indegree Array)
 *
 * Concepts:
 * - There are a total of `numCourses` courses you have to take, labeled from `0` to `numCourses - 1`.
 * - `prerequisites[i] = [a, b]` means to take course `a`, you must first take course `b` (directed edge: `b -> a`).
 * - Taking all courses is possible if and only if the dependency graph contains NO directed cycles (i.e. is a Directed Acyclic Graph / DAG).
 * - 3-State DFS:
 *   - State 0: Unvisited
 *   - State 1: Visiting (currently in the active recursion call stack)
 *   - State 2: Visited (completely explored, no cycles reachable from this node)
 *   - If DFS encounters a node with State 1, a back-edge (cycle) exists!
 *
 * Complexity:
 * - Time Complexity:  O(V + E) where V = numCourses, E = prerequisites.length.
 * - Space Complexity: O(V + E) for adjacency list and recursion stack / indegree storage.
 */
public class LC0207_CourseSchedule {

    // Approach 1: 3-State DFS
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] pre : prerequisites) {
            int course = pre[0];
            int prereq = pre[1];
            adj.get(prereq).add(course); // prereq -> course
        }

        // 0 = unvisited, 1 = visiting, 2 = visited
        int[] state = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            if (state[i] == 0) {
                if (hasCycle(adj, state, i)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean hasCycle(List<List<Integer>> adj, int[] state, int node) {
        state[node] = 1; // Visiting

        for (int neighbor : adj.get(node)) {
            if (state[neighbor] == 1) {
                return true; // Found a back-edge (cycle)
            }
            if (state[neighbor] == 0 && hasCycle(adj, state, neighbor)) {
                return true;
            }
        }

        state[node] = 2; // Visited
        return false;
    }

    // Approach 2: Kahn's Algorithm (BFS)
    public boolean canFinishKahn(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj = new ArrayList<>();
        int[] indegree = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] pre : prerequisites) {
            int course = pre[0];
            int prereq = pre[1];
            adj.get(prereq).add(course);
            indegree[course]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        int processed = 0;
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            processed++;

            for (int neighbor : adj.get(curr)) {
                indegree[neighbor]--;
                if (indegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        return processed == numCourses;
    }

    public static void main(String[] args) {
        LC0207_CourseSchedule solver = new LC0207_CourseSchedule();

        // Scenario 1: Linear sequence 0 <- 1 (take 1 then 0) -> Possible
        int[][] pre1 = {{0, 1}};
        System.out.println("Can finish pre1 (DFS): " + solver.canFinish(2, pre1));      // Expected: true
        System.out.println("Can finish pre1 (Kahn): " + solver.canFinishKahn(2, pre1));  // Expected: true

        // Scenario 2: Direct cycle 0 <-> 1 -> Impossible
        int[][] pre2 = {{1, 0}, {0, 1}};
        System.out.println("Can finish pre2 (DFS): " + solver.canFinish(2, pre2));      // Expected: false
        System.out.println("Can finish pre2 (Kahn): " + solver.canFinishKahn(2, pre2));  // Expected: false

        // Scenario 3: Diamond DAG (no cycle)
        int[][] pre3 = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        System.out.println("Can finish pre3: " + solver.canFinish(4, pre3));            // Expected: true
    }
}
