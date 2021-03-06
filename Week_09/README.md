# 不同路径 II 动态规划转移方程
## 动态规划方程
- dp[i][j]表示的是走到第i行j列的时候，总共有多少种走法。
- 每个位置只能从左边走过来，或者从上边走过来，只有这两个方向。如果从上边走过来，走法有dp[i-1][j]种。如果是从左边走过来，走法是dp[i][j-1]种，总共走法是dp[i-1][j]+dp[i][j-1]。
所以状态转移方程是 **dp[i][j] = dp[i - 1][j] + dp[i][j - 1];**
- 边界条件，因为在obstacleGrid[0][0]等于0的时候，dp[1][1]=1,所以这里
**要么dp[0][1] = 1，**
**要么dp[1][0] = 1，这两个都是可以的。**
具体java代码：
```java
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
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
```
## 动态规划方程优化
上面二维数组遍历的时候只使用了每个位置左边的值和上边的值，其他的都用不到，所以我们可以考虑把二维数组转化为一位数组，同时需要考虑障碍物的情况，所以状态转移方程为：
- 当obstacleGrid[i][j]==1时，dp[j] = 0；
- 当obstacleGrid[i][j]==0时，同时需要考虑前一列(i, j-1)是否也是障碍物，因为如果前面(i, j-1)也是障碍物是到达不了(i, j)
dp[j] = dp[j] + dp[j - 1]
 具体java代码：
 ```java
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
```
