package ARRAY;

import java.util.Arrays;

public class removeDuplicates {
    public int removeDuplicates(int[] nums) {
        Arrays.sort(nums);
        int res =1;
        if(nums==null||nums.length<1){
            return 0;
        }

        for(int i=1;i<nums.length;i++){
            if(nums[i]!=nums[i-1]){
                nums[res]=nums[i];
                res++;
            }
            else continue;
        }
        return res;

    }


//    public int removeDuplicates(int[] nums) {
//        Arrays.Sort(nums);
//        int res = 1;
//        if(nums==null||nums.length<1){
//            return 0;
//        }
//
//        for(int i=1;i<nums.length;i++){
//            if(nums[i]==nums[i-1]){
//                res++;
//                nums[i-1]=nums[i];
//            }
//            else {continue;}
//        }
//        return res;
//
//    }

}
