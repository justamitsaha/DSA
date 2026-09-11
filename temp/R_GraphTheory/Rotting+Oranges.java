class Solution {
    public int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0) return -1;
        int m = grid.length;
        int n = grid[0].length;
        
        int freshOrange = 0;
        Queue<int[]> queue = new LinkedList<>();
        
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    freshOrange++;
                }
                else if (grid[i][j] == 2) {
                    queue.add(new int[]{i, j});
                }
            }
        }
        
        int minutes = 0;
        
        while (!queue.isEmpty() && freshOrange > 0) {
            int size = queue.size();
            
            for(int i = 0; i < size; i++) {
                int[] cell = queue.poll();
                
                int[][] direction = { {1, 0}, { -1, 0 }, {0, 1}, {0, -1}};
                for(int k = 0; k < direction.length; k++) {
                    int x = cell[0] + direction[k][0];
                    int y = cell[1] + direction[k][1];
                    
                    if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1) {
                        grid[x][y] = 2;
                        queue.add(new int[]{x, y});
                        freshOrange--;
                    }
                }
            }
            minutes++;
        }
        
        if (freshOrange == 0) return minutes;
        else return -1;
    }
}
