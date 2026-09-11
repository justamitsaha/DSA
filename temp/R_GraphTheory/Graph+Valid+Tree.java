class Solution {
    public boolean validTree(int n, int[][] edges) {
        List<List<Integer>> adjacencyList = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            adjacencyList.add(new ArrayList<>());
        }
        
        for(int[] edge: edges) {
            adjacencyList.get(edge[0]).add(edge[1]);
            adjacencyList.get(edge[1]).add(edge[0]);
        }
        
        Set<Integer> visited = new HashSet<>();
        
        if (!dfs(adjacencyList, visited, 0, -1)) {
            return false;
        }
        
        return visited.size() == n;
        
    }
    
    public boolean dfs(List<List<Integer>> adjacencyList, Set<Integer> visited, int node, int parent) {
        visited.add(node);
        for(int neighbor: adjacencyList.get(node)) {
            if (neighbor == parent) continue;
            
            if (visited.contains(neighbor)) {
                return false;
            }
            
            if (!dfs(adjacencyList, visited, neighbor, node)) {
                return false;
            }
        }
        return true;
    }
}
