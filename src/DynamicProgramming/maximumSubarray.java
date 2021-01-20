package DynamicProgramming;

public class maximumSubarray {
    public int maxSubArray(int[] nums) {
        if(nums.length < 1) return 0;
        int res = nums[0];
        int cur = nums[0];
        for(int i =1; i < nums.length ; i++){
            cur = cur + nums[i];
            if(nums[i]>cur){
                cur=nums[i];
            }
            if(cur>res) res=cur;
        }

        return res;
    }
}
