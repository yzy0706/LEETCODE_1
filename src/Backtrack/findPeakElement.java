package Backtrack;

public class findPeakElement {
//    public int backTrack.findPeakElement(int[] nums) {
//        int pos = 1;
//        Boolean result= false;
//
//        for (int i = 1; i < nums.length - 2; i = i++) {
//            if(nums[i]> nums[i+1]){
//                if(nums[i])
//            }
//            if(nums[i])
//            pos = i;
//            break;
//        }
//        return pos;
//    }


    public int findPeakElement(int[] nums) {
        return search(nums, 0, nums.length - 1);
    }
    public int search(int[] nums, int l, int r) {
        if (l == r)
            return l;
        int mid = (l + r) / 2;
        if (nums[mid] > nums[mid + 1])
            return search(nums, l, mid);
        return search(nums, mid + 1, r);
    }

}






