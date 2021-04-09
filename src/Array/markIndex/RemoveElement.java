package Array.markIndex;

import java.util.Arrays;

public class RemoveElement {
    // 做法: 这题就用一个ind表示当前安排不是val的数的位置, 因为到了最后一个不是val的数安排完了以后ind还会++, 所以最后直接return ind;
    // 就是有效的长度
    // Runtime: O(n), Space: O(1);

    public int removeElement_reviewed(int[] nums, int val) {
        if(nums.length == 0) return 0;
        int ind = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] != val) nums[ind++] = nums[i];
        }
        return ind;
    }



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
