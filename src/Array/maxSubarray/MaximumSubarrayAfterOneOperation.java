package Array.maxSubarray;

public class MaximumSubarrayAfterOneOperation {
    //做法：  自己写的kadane解法， 没有用int[][] dp, 而是用了一个opNotHappend代替dp[i][0], 用一个opHappend代替dp[i][1];
    //主要的区别是在进入forloop以后我们不再分成有没有发生opration来分别更新dp[i][0], dp[i][1]， 直接找max
    //而是判断之前没有发生op的情况叠加起来暂时的和是不是 <= 0，
    // 如果是的话就不需要再去考虑之前没有发生的总和（opNotHappend)；opHappend = Math.max(nums[i] * nums[i], opHappend + nums[i]);
    // 如果不是的话就需要考虑opNotHappend: opHappend = Math.max(opNotHappend + nums[i] * nums[i], opHappend + nums[i]);

    //Runtime: O(n), space: O(1)
    public int maxSumAfterOperation_kadane(int[] nums) {
        if(nums.length < 1){
            return 0;
        }
        int res = Integer.MIN_VALUE;
        int opHappend  = nums[0] * nums[0], opNotHappend = nums[0];
        if(nums.length == 1){
            return opHappend;
        }
        for(int i = 1; i < nums.length; i++){
            if(opNotHappend <= 0){
                opHappend = Math.max(nums[i] * nums[i], opHappend + nums[i]);
                opNotHappend = nums[i];
            }
            else{
                opHappend = Math.max(opNotHappend + nums[i] * nums[i], opHappend + nums[i]);
                opNotHappend += nums[i];
            }
            res = Math.max(opHappend, res);
        }

        return res;
    }









    //做法: 这题主要是用一个double array的思维去记录到当前这个位置为止有没有发生过opration,
    // 然后去比较各个情况的可能, 并且要考虑kadane里面的要不要加上前面的总和， 一共有两种大情况四种小情况

    //1. 如果到当前为止都没发生过opration, 那么他的当前结果也没法跟res比较,
    // 因为必须发生operation : dp[i][0] = Math.max(dp[i-1][0] + nums[i], nums[i]);

    //2. 如果到当前为止已经发生过operation了, 我们要考虑三种情况:
    //  a. 当前就是发生opration的地方, 且前面的和不用加上去 : dp[i][1] = nums[i] * nums[i];
    //  b. 当前就是发生opration的地方, 但要加上前面的和: dp[i][1] = dp[i-1][0] + nums[i] * nums[i];
    //  c. 当前不是发生operation的地方, 且前面的和肯定是要加上去的, 因为答案里必须包括opration的位置: dp[i][1] = dp[i-1][1] + nums[i];


    //3. 然后我们再每次更新res和dp[i][1], 最后return res;

    //Runtime: O(n), space: O(n)

    public int maxSumAfterOperation_2dDP(int[] nums) {
        if(nums.length < 1){
            return 0;
        }
        int res = Integer.MIN_VALUE;
        int[][] dp = new int[nums.length][2];
        dp[0] = new int[]{nums[0], nums[0] * nums[0]};
        if(nums.length == 1){
            return dp[0][1];
        }
        for(int i = 1; i < nums.length; i++){
            dp[i][0] = Math.max(dp[i-1][0] + nums[i], nums[i]);
            //前面包括当前位置一直没发生过operation, 没有数变平方
            dp[i][1] = Math.max(Math.max(dp[i-1][1] + nums[i], dp[i-1][0] + nums[i] * nums[i]), nums[i] * nums[i]);
            //前面包括当前位置发生过operation,
            //比较:
            //1. 前面发生平方且前方的总和dp[i-1][1] > 0: dp[i-1][1] + nums[i]
            //2. 当前发生平方且前方的总和dp[i-1][0] > 0: dp[i-1][0] + nums[i] * nums[i]
            //3. 当前发生平方且前面的总和dp[i-1][0] < 0: nums[i] * nums[i]
            res = Math.max(res, dp[i][1]);
        }

        return res;
    }





    public int maxSumAfterOperation(int[] nums) {
        int maxAbs = 0;
        for(int i = 0; i < nums.length; i++){
            if(Math.abs(nums[i]) > Math.abs(nums[maxAbs])){
                maxAbs = i;
            }
        }
        nums[maxAbs] = nums[maxAbs] * nums[maxAbs];
        int maxBefore = nums[0];
        int res = Integer.MIN_VALUE;
        for(int i = 1; i < nums.length; i++){
            if(maxBefore < 0){
                maxBefore = 0;
            }
            maxBefore += nums[i];
            if(maxBefore > res){
                res = maxBefore;
            }
        }
        return res;
    }
}
