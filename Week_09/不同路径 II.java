class Solution {

    public int uniquePathsWithObstacles1(int[][] obstacleGrid) {
        if (null == obstacleGrid || 0 == obstacleGrid.length || 0 == obstacleGrid[0].length) return 0;
        int rows = obstacleGrid.length;
        int cols = obstacleGrid[0].length;
        int[][] dp = new int[rows][cols];
        for (int i = 0; i<rows; i++) {
            if (obstacleGrid[i][0] == 1) break;
            dp[i][0] = 1;
        }
        for (int j = 0; j<cols; j++) {
            if (obstacleGrid[0][j] == 1) break;
            dp[0][j] = 1;
        }
        for (int i = 1; i<rows; i++) {
            for (int j = 1; j<cols; j++) {
                dp[i][j] = (obstacleGrid[i][j] == 1) ? 0 : (dp[i-1][j] + dp[i][j-1]);
            }
        }
        return dp[rows-1][cols-1];
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (null == obstacleGrid || 0 == obstacleGrid.length || 0 == obstacleGrid[0].length) return 0;
        int rows = obstacleGrid.length;
        int cols = obstacleGrid[0].length;
        int[] f = new int[cols];

        f[0] = obstacleGrid[0][0] == 1 ? 0 : 1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (obstacleGrid[i][j] == 1) {
                    f[j] = 0;
                    continue;
                }
                if (j > 0 && obstacleGrid[i][j-1] == 0) {
                    f[j] += f[j-1];
                }
            }
        }
        return f[cols -1];
    }
}
