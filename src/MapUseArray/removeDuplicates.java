package MapUseArray;

public class removeDuplicates {
    public int removeDuplicates(int[] nums) {
    if(nums==null||nums.length<1) return 0;
    int res = 1;
    for(int i = 1; i< nums.length; i++){
        if(nums[i]!=nums[i-1]) {
            nums[res]=nums[i];
            res++;
        }
    }
    return res;


}
}
