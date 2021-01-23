package BinarySearch;

public class rangeRotatedSortedArray {
    //主要就是分别去找区间的最左边那个点和最右边那个点，找最右边那个点的时候只要nums[mid] > target就行，然后注意edgecase
    public int[] searchRange_leetcode(int[] nums, int target) {
        int[] res = {-1,-1};
        if(nums.length < 1) return res;
        if(nums[0]==target && nums[nums.length-1] == target) return new int[]{0,nums.length-1};
        int leftFirst = findRange(nums,target,true);
        if(leftFirst == nums.length || nums[leftFirst] != target) return res;
        res[0] = leftFirst;
        if(nums[nums.length-1] == target) res[1]= nums.length-1;
        else res[1] = findRange(nums,target,false)-1;
        return res;
    }

    public int findRange(int[] nums,int target, boolean left){
        int l = 0;
        int r = nums.length -1;
        while(l<r){
            int mid = (l+r)/2;
            if(nums[mid] > target ||(left && nums[mid]==target)) r = mid;
            else l = mid+1;
        }
        return l;
    }






    //第一遍自己写的
    public int[] searchRange(int[] nums, int target) {


        return search(nums,target,0,nums.length-1);
    }

    public int[] search(int[] nums,int target, int l, int r){
        int mid = (l+r)/2;
        if(l > r) return new int[]{-1,-1};
        if(nums[l] == target && nums[r] == target){
            int len = r-l+1;
            int[] res = new int[len];
            for(int i = 0; i < len; i++){
                res[i] = target;
            }
            return res;
        }
        else if(nums[mid] >= nums[l]){
            if(target >= nums[mid] && target <= nums[r]) return search(nums,target,mid,r);
            else return search(nums,target,l,mid);
        }
        if(target >= nums[mid] && target <= nums[r]) return search(nums,target,mid,r);
        else return search(nums,target,l,mid);
    }
}
