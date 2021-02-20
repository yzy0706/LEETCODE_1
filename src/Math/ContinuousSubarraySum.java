package Math;

import java.util.HashMap;

public class ContinuousSubarraySum {

    //做法: 这题主要运用了一个数学原理, (a + (n % x)) % x = a % x, 所以如果一直到当前位置j的和与k的remainder在之前出现过, 证明从之前那个同样的remainder的位置i开始到当前的位置j, 中间的数的和正好是n * k, 这样i和j的位置的remainder才会相等
    // 1. 建立一个map, 用一个sum记录所有随着i的移动的不同的和与k的remainder(如果k是0就不用sum % k), 把remainder和当前的位置i放进这个map里
    // 2. 每到一个i, 如果当前的remainder出现过, 且i到之前出现的位置 > 1, return true, 否则就把当前sum和当前的位置(或者如果之前出现过这个remainder且距离 == 1的话就把之前那个位置)放回去

    // Runtime: O(n), Space: O(k) , 最多有k个余数
    public boolean checkSubarraySum_map(int[] nums, int k) {
        int sum = 0;
        HashMap<Integer, Integer> sameRemainder = new HashMap<>();
        sameRemainder.put(0, -1);
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
            sum = k == 0 ? sum : sum % k;
            if(sameRemainder.containsKey(sum) && i - sameRemainder.get(sum) > 1) return true;
            sameRemainder.put(sum, sameRemainder.getOrDefault(sum, i)); //如果 j - i == 1的话是要把之前那个位置放回去的
        }
        return false;
    }




    //第一遍想用2D的dp去做，储存空间不够
    public boolean checkSubarraySum(int[] nums, int k) {
        if(nums.length < 2) return false;
        int[][] dp = new int[nums.length][nums.length];
        for(int l = nums.length - 2; l >= 0; l--){
            dp[l][l] = nums[l];
            for(int r = l + 1; r < nums.length; r++){
                dp[r][r] = nums[r];
                dp[l][r] = l != r-1 ? dp[l+1][r-1] + nums[l] + nums[r] : nums[l] + nums[r];
                if(dp[l][r] == 0 && k == 0 || k != 0 && dp[l][r] % k == 0) return true;
            }
        }
        return false;
    }
}
