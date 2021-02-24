package Array.maxSubarray;

import java.util.HashMap;
import java.util.HashSet;

public class LongestFibonacciSubsequence {

// 做法: 看到longest subsequence, 又是array我们都应该想到2D dp, dp[l][r]代表以l, r两个位置为结尾的Fibonacci数列的长度
    // 1.  建立一个map, 把当前arr上每个数的值和对应的index都放进去
    // 2.  用r来forloop整个arr, l来forloop从0到r
    //      a. 如果arr[r] - arr[l]存在在map里, 且这个值比arr[l]小, 那代表着有一个连续的数列, dp[l][r] = dp[veryLeftIdx][l] + 1;
    //      b. 如果不存在的话或者比arr[l]大的话, 直接先默认dp[l][r] = 2, 反正最后res <= 2都是要被清零的
    //      c. 随时更新res和dp[l][r], 如果最后res还是2就直接return 0

    // Runtime : O(n^2), Space: O(n)
    public int lenLongestFibSubseq_dp(int[] arr) {
        if(arr.length < 3) return 0;
        int res = 0;
        HashMap<Integer, Integer> pos = new HashMap<>();
        for(int i = 0; i < arr.length; i++) pos.put(arr[i], i);
        int[][] dp = new int[arr.length][arr.length];

        for(int r = 0; r < arr.length; r++){
            for(int l = 0; l < r; l++){
                int leftOfL = arr[r] - arr[l];
                if(pos.containsKey(leftOfL) && leftOfL < arr[l]){
                    //如果当前l,r是连续的Fibonacci数列的后两个, 就更新dp[l][r];
                    int veryLeftIdx = pos.get(leftOfL);
                    dp[l][r] = dp[veryLeftIdx][l] + 1;
                }
                else{
                    dp[l][r] = 2;
                    //如果当前l,r不是连续的Fibonacci数列的最后两个, 就直接假设当前的数列长度是2, 反正不到3是不会算数的
                }
                res = Math.max(res, dp[l][r]);
            }
        }

        return res < 3 ? 0 : res;

    }

    // 做法: 简单的two pointer方法, 检查l + r存不存在, 存在就继续更新
    // Runtime : O(n^2log(maxNumber)), Space: O(n)
    public int lenLongestFibSubseq_twoPointer(int[] arr) {
        HashSet<Integer> nums = new HashSet<>();
        for(int i : arr) nums.add(i);
        int res = 0;
        for(int l = 0; l < arr.length; l++){
            for(int r = l + 1; r < arr.length; r++){
                int lNum = arr[l], rNum = arr[r], len = 2;
                while(nums.contains(lNum + rNum)){
                    rNum = lNum + rNum;
                    lNum = rNum - lNum; //这里rNum已经变成了lNum + rNum, 所以要减去lNum
                    len++;
                    res = Math.max(res, len);
                }
            }
        }
        return res;
    }
}
