package DynamicProgramming.bitMask;

import java.util.Arrays;

public class PartitionToKEqualSumSubsets {
    // 做法: 用bitmask来记录每一种i和j的结合状态, 记录在一个int[] curSum上,  partSum = sum / k;
    // 1. 建立int[] curSum = new int[1 << n]; boolean[] dp = new boolean[1 << n];, dp[0] = true;
    // 2. forloop i从0到1<<n - 1, 如果dp[i]是true的话证明可以加上j, forloop j从0到n-1
    // a. 如果 curSum[i] % partSum + nums[j] <= partSum (partSum是每一个部分的和), dp[i | (1 << j)] = true;
    // b. 如果 curSum[i] % partSum (这里假如在i的位置的和已经是partSum了， 余数就会是0） + nums[j] > partSum; 证明当前这个j不符合条件, 直接break当前j的forloop, 因为后面的比当前大的j也没必要去看了
    // 3. return dp[1 << n - 1];

    // Runtime: O(n * 2^n), 对于二进制来说每个数都对应着2^n种可能; Space: O(2^n)
    public boolean canPartitionKSubsets_bitMask(int[] nums, int k) {
        int sum = 0;
        for(int i : nums){
            sum += i;
        }
        if(sum % k != 0) return false;
        int partSum = sum / k;
        Arrays.sort(nums);
        int n = nums.length;
        if(nums[n-1] > partSum) return false;
        boolean[] dp = new boolean[1 << n];
        dp[0] = true;
        int[] curSum = new int[1 << n];
        for(int i = 0; i < (1<<n); i ++){
            if(dp[i]){
                for(int j = 0; j < n; j ++){
                    int addPos = i | (1 << j); //添加标志位j
                    if(addPos != i){
                        if(partSum - curSum[i] % partSum - nums[j] >= 0){ //如果j number可以加到当前curSum[i]的数列里
                            dp[addPos] = true; // 当前j可以被加入数列
                            curSum[addPos] = curSum[i] + nums[j];
                        }
                        else{
                            break;
                        }
                    }
                }
            }
        }
        return dp[(1 << n) - 1];
    }



    //自己第一遍看了bitmask写的
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for(int i : nums){
            sum += i;
        }
        if(sum % k != 0) return false;
        int partSum = sum / k;
        Arrays.sort(nums);
        int n = nums.length;
        if(nums[n-1] > partSum) return false;
        boolean[] dp = new boolean[1 << n];
        int[] curSum = new int[1 << n];
        for(int i = 0; i < (1<<n); i ++){
            if(dp[i]){
                for(int j = 0; j < n; j ++){
                    int addPos = i | (1 << j); //添加标志位j
                    if(addPos != i){
                        if(partSum - (curSum[i] % partSum) - nums[j] >= 0){ //如果j number可以加到当前curSum[i]的数列里
                            dp[addPos] = true; // 当前j可以被加入数列
                            curSum[addPos] = curSum[i] + nums[j];
                        }
                        else{
                            break;
                        }
                    }
                }
            }
        }
        return dp[(1 << n) - 1];
    }
}
