package DynamicProgramming.longestSubsequence;

public class MaxLengthOfRepeatedSubarray {
    //做法: 用二维dp做的, dp[i+1][j+1]代表到i, j这个位置最长的连续相等的数列.
    //1. 不同的是因为我们要求完全连续的subseq, 所以A[i] != B[j]的时候直接默认为0
    //2. 而且因为dp上来就是默认为都是0的所以没必要去特意设置dp[0][j]或者dp[i][0] = 0;
    //Runtime: O(mn), Space: O(mn)

    public int findLength(int[] A, int[] B) {
        int len1 = A.length, len2 = B.length;
        int[][] dp = new int[len1+1][len2+1];
        int res = 0;
        for(int i = 0; i < len1; i++){
            for(int j = 0; j < len2; j++){
                if(A[i] == B[j]){
                    dp[i+1][j+1] = dp[i][j] + 1;
                    res = Math.max(dp[i+1][j+1], res);
                }
            }
        }
        return res;

    }
}
