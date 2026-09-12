package leetcode.R_GraphTheory;

/**
 * LeetCode 997: Find the Town Judge
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/find-the-town-judge/
 *
 * Algorithm:
 * - Directed Graph Degree Counting (Indegree / Outdegree or Net Degree Score)
 *
 * Concepts:
 * - In a town of `n` people labeled `1` to `n`, there is a rumor that one of these people is secretly the town judge.
 * - The town judge must satisfy two properties:
 *   1. The town judge trusts nobody (`outdegree == 0`).
 *   2. Everybody else trusts the town judge (`indegree == n - 1`).
 *   3. There is exactly one person that satisfies properties 1 and 2.
 * - Strategy:
 *   Maintain a single `netTrustScore` array of size `n + 1`:
 *   - When person `a` trusts person `b`:
 *     - Person `a` gives trust: `netTrustScore[a]--` (outdegree increased).
 *     - Person `b` receives trust: `netTrustScore[b]++` (indegree increased).
 *   - The judge will have exactly `netTrustScore[judge] == n - 1`.
 *
 * Complexity:
 * - Time Complexity:  O(T + n) where T is trust.length and n is the number of people.
 * - Space Complexity: O(n) for the degree counting array.
 */
public class LC0997_FindTheTownJudge {

    public int findJudge(int n, int[][] trust) {
        // netScore[i] = indegree[i] - outdegree[i]
        int[] netScore = new int[n + 1];

        for (int[] relation : trust) {
            int from = relation[0];
            int to = relation[1];
            netScore[from]--;
            netScore[to]++;
        }

        for (int i = 1; i <= n; i++) {
            if (netScore[i] == n - 1) {
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        LC0997_FindTheTownJudge solver = new LC0997_FindTheTownJudge();

        // Scenario 1: Person 2 is the judge
        int[][] trust1 = {{1, 2}};
        System.out.println("Town Judge (n=2, [[1,2]]): " + solver.findJudge(2, trust1)); // Expected: 2

        // Scenario 2: Person 3 is the judge
        int[][] trust2 = {{1, 3}, {2, 3}};
        System.out.println("Town Judge (n=3, [[1,3],[2,3]]): " + solver.findJudge(3, trust2)); // Expected: 3

        // Scenario 3: Cycle of trust (no judge)
        int[][] trust3 = {{1, 3}, {2, 3}, {3, 1}};
        System.out.println("Town Judge (n=3, cycle): " + solver.findJudge(3, trust3)); // Expected: -1
    }
}
