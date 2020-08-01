class Solution {
    /**
    1.subproblems opt[i,j] = opt[i-1, j] + opt[i, j-1];
    2.DP array dp[i][j]
    3.DP 方程 dp[i][j] = dp[i-1][j] + dp[i][j-1]
    **/
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i=0; i<m; i++) dp[i][0]=1;
        for (int j=0; j<n; j++) dp[0][j]=1;
        for (int i=1; i<m; i++) {
            for (int j=1; j<n; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }
}