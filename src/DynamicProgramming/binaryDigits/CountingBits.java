package DynamicProgramming.binaryDigits;

public class CountingBits {
    //做法: 这道题主要是运用到了一个数学原理就是一个数的二进制的1的数量应该和i/2一样， 因为一个数乘以二就是他所有binary digit往左边移动一个
    // 除了整除的部分以外就是i%2剩下的那个尾巴再加上就行了， 所以dp[i] = dp[i/2] + i % 2;
    //Runtime: O(n), space: O(n)
    public int[] countBits(int num) {
        int[] dp = new int[num+1];
        dp[0] = 0;
        for(int i = 1; i <= num; i++){
            dp[i] = dp[i/2] + i % 2;
        }
        return dp;
    }
}
