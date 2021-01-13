package ARRAY;

import java.util.Arrays;

public class removeElement {
    public int removeElement(int[] nums, int val) {
        int res=0;
        Arrays.sort(nums);
        for(int i=0;i<nums.length;i++){
            if(nums[i]!=val){
                nums[res]=nums[i];
                res++;
            }
            continue;

        }

        return res;

    }
}
