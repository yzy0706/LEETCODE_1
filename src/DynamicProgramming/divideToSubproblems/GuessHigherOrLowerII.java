package DynamicProgramming.divideToSubproblems;

public class GuessHigherOrLowerII {
    //做法: 这个题有些tricky, dp是一个int[n+2][n+2], dp[i][j]就是在[i,j]范围内我猜到结果所需要的最少的钱, 我们要求dp[1][n];
    //1. 范围diff最小是1, 从1到n-1forloop
    //2. 猜的起始值l可以从1到n-diff forloop
    //3. 猜的终点值r = l + diff, 预设dp[l][r]需要Integer.MAX_VALUE的钱
    //4. 我们猜的每一种可能guess可以从l一直到r, guess是输了要付的钱, dp[l][r] = Math.min(dp[l][r], guess + Math.max(dp[l][guess-1], dp[guess+1][r]));

    //Runtime: O(n^3), space: O(n^2);
    public int getMoneyAmount(int n) {
        int[][] dp = new int[n+2][n+2];
        for(int diff = 1; diff < n; diff ++){ //遍历猜的数的范围大小, 从1到到n-1都有可能
            for(int l = 1; l + diff <= n; l++){ //遍历猜的范围最小值
                int r = l + diff; //最大值就是l + diff
                dp[l][r] = Integer.MAX_VALUE;
                for(int guess = l; guess <= r; guess++){
                    dp[l][r] = Math.min(dp[l][r], guess + Math.max(dp[l][guess-1], dp[guess+1][r])); //根据各个guess的可能更新dp[l][r], 因为每个guess可以把dp[l][r]分成 guess错了要付guess的钱, 然后dp[l][guess-1], dp[guess+1][r]的两种可能的最小值是另外要付的钱, 或者正好就是dp[l][r]
                }
            }
        }
        return dp[1][n]; //返回从1猜到n所需要的最少的钱
    }
}
