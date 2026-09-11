class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> output = new ArrayList<>();
        int m = matrix.length;
        int n = matrix[0].length;
        
        int top = 0, bottom = m - 1, left = 0, right = n - 1;
        int direction = 0;
        
        while (top <= bottom && left <= right) {
            if (direction == 0) {
                for(int j = left; j <= right; j++) {
                    output.add(matrix[top][j]);
                }
                top++;
                direction = 1;
            }
            else if (direction == 1) {
                for(int i = top; i <= bottom; i++) {
                    output.add(matrix[i][right]);
                }
                right--;
                direction = 2;
            }
            else if (direction == 2) {
                for(int j = right; j >= left; j--) {
                    output.add(matrix[bottom][j]);
                }
                bottom--;
                direction = 3;
            }
            else if (direction == 3) {
                for(int i = bottom; i >= top; i--) {
                    output.add(matrix[i][left]);
                }
                left++;
                direction = 0;
            }
        }
        
        return output;
    }
}
