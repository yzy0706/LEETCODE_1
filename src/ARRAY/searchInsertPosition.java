package ARRAY;

public class searchInsertPosition {
    public int searchInsert(int[] nums, int target) {
        int res = 0;
        for(int i = 0 ; i < nums.length ; i++){
            if(target <= nums[i]){
                res = i;
                break;
            }
            if(i == nums.length-1 && target > nums[i]){
                res = i+1;
                break;
            }
        }
        return res;



    }
}
