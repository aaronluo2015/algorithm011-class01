class Solution {

    public int climbStairs(int n) {
        return this.climbStairs2(n);
    }
    public int climbStairs1(int n) {
        int p = 0, q = 0, r = 1;
        for (int i=1; i<=n; i++) {
            p = q;
            q = r;
            r = p + q;
        }
        return r;
    }

    //f(n) = f(n-1) + f(n-2);
    public int climbStairs2(int n) {
        int[] memo = new int[n];
        return f(n, memo);
    }
    private int f(int n, int[] memo) {
        if (n <= 2) {
            memo[n-1] = n;
            return n;
        }
        if (0 == memo[n-1]) {
            memo[n-2] = f(n-1, memo);
            memo[n-3] = f(n-2, memo);
            return memo[n-2] + memo[n-3];
        }
        return memo[n-1];
    }
}