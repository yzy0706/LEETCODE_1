package Array.markIndex.findDuplicates;

import java.util.Arrays;

public class RemoveDuplicates {
    //做法: 还是在整个array上面操作, cnt从0开始, 表示当前找到的单独的按大小排的数的位置
    // 1. forloop整个nums, 每找到num[i] > nums[cnt] nums[++cnt] = nums[i]
    // 2. return cnt + 1; 因为cnt是zero indexed的坐标
    // Runtime: O(n), Space: O(1)

    public int removeDuplicates_reviewed(int[] nums) {
        if(nums.length == 0) return 0;
        int cnt = 0;
        for(int i = 1; i < nums.length; i++){
            if(nums[i] > nums[cnt]) nums[++cnt] = nums[i];
        }
        return cnt + 1;
    }





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
