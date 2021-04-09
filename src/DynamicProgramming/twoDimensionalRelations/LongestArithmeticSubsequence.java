package DynamicProgramming.twoDimensionalRelations;

import java.util.HashMap;

public class LongestArithmeticSubsequence {
    //做法: discussion给了一个 HashMap<Integer, Integer>[] dp 的做法, 对于每一个位置, 都根据不同的间距储存subsequence的长度
    //Runtime: O(n^2), Space: O(n^2)

    public int longestArithSeqLength_HashMap(int[] A) {
        HashMap<Integer, Integer>[] dp = new HashMap[A.length];
        int res = 2;
        for(int j = 0; j < A.length; j++){
            dp[j] = new HashMap<>();
            for(int i = 0; i < j; i++){
                int d = A[j] - A[i];
                dp[j].put(d, dp[i].getOrDefault(d, 1) + 1);
                res = Math.max(res, dp[j].get(d));
            }
        }
        return res;
    }




    //做法: 第一遍用2d的dp做的, 就是dp[j][k]代表这两个数之前啊, 以当前这两个数的差的subsequence的最长长度
    //Runtime: O(n^3), Space: O(n^2)
    public int longestArithSeqLength(int[] A) {
        int[][] dp = new int[A.length][A.length];
        int res = 0;
        for(int k = 1; k < A.length; k++){
            for(int j = k-1; j >= 0; j--){
                dp[j][k] = 2;
                for(int i = j-1; i >= 0; i--){
                    if(A[k] - A[j] == A[j] - A[i]){
                        dp[j][k] = Math.max(dp[j][k], dp[i][j] + 1);
                        res = Math.max(res, dp[j][k]);
                    }
                }
            }
        }
        return res == 0? 2 : res;
    }
}
