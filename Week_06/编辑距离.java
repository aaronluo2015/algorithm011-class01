class Solution {
    public int minDistance(String word1, String word2) {
        int rows = word1.length() + 1, cols = word2.length() + 1;
        int[][] dp = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = (i == 0) ? j : i;
                    continue;
                }
                int left = dp[i-1][j] + 1;
                int down = dp[i][j-1] + 1;
                int leftdown = dp[i-1][j-1];
                if (word1.charAt(i-1) != word2.charAt(j-1)) {
                    leftdown += 1;
                }
                dp[i][j] = Math.min(left, Math.min(down, leftdown));
            }
        }
        return dp[rows-1][cols-1];
    }
}