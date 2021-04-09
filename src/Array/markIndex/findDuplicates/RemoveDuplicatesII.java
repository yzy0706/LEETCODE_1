package Array.markIndex.findDuplicates;

public class RemoveDuplicatesII {
    //做法: 还是跟类似的那个remove duplicates一样采取标记的办法, 只是加了一个boolean same来确定当前是不是已经排列了两个连续一样的数在要用的nums片段上
    //Runtime: O(n), Space: O(1)
    public int removeDuplicates(int[] nums) {
        if(nums.length == 1) return 1;
        int cnt = 0;
        boolean same = false;
        for(int i = 1; i < nums.length; i++){
            if(nums[i] == nums[cnt] && !same){
                nums[++cnt] = nums[i];
                same = true;
            }
            else if(nums[i] > nums[cnt]){
                nums[++cnt] = nums[i];
                // System.out.println(nums[4]);
                same = false;
            }
        }
        return cnt+1;
    }
}
