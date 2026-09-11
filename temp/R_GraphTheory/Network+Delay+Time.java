class Solution {
    public int[] computeDistance(List<List<Pair<Integer, Integer>>> graph, int k) {
        int[] dist = new int[graph.size()];
        Arrays.fill(dist, Integer.MAX_VALUE);
        PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue<>((a, b) -> a.getValue() - b.getValue());
        pq.offer(new Pair<>(k, 0));
        while (!pq.isEmpty()) {
            int currentDistance = pq.peek().getValue();
            int top = pq.peek().getKey();
            pq.poll();
            if (dist[top] > currentDistance) {
                dist[top] = currentDistance;
                for (Pair<Integer, Integer> edge : graph.get(top)) {
                    int v = edge.getKey();
                    int w = edge.getValue();
                    if (dist[v] > currentDistance + w) {
                        pq.offer(new Pair<>(v, currentDistance + w));
                    }
                }
            }
        }
        return dist;
    }

    public int networkDelayTime(int[][] times, int N, int K) {
        List<List<Pair<Integer, Integer>>> graph = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : times) {
            int u = edge[0] - 1, v = edge[1] - 1, w = edge[2];
            graph.get(u).add(new Pair<>(v, w));
        }

        int[] distance = computeDistance(graph, K - 1);
        int ma = Integer.MIN_VALUE;
        for (int i = 0; i < distance.length; i++) {
            ma = Math.max(ma, distance[i]);
        }
        if (ma == Integer.MAX_VALUE) {
            return -1;
        } else {
            return ma;
        }
    }
}
