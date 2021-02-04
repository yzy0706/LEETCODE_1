package Array;

import java.util.Arrays;

public class firstMissingPositive {
    public int firstMissingPositive(int[] nums) {
        int res = 1;
        Arrays.sort(nums);
        for(int i = 0; i < nums.length ; i++){
            int cur = nums[i];
            if(cur < res){
                continue;
            }
            else if(cur == res){
                res ++;
                continue;
            }
            else if(cur > res){
                if(i==0) break;
                if(nums[i-1] < res || i == 0){
                    break;
                }
            }

        }
        return res;
    }
}
