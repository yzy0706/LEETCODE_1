package 赋值修改int数列;

public class removeElement {
     public int removeElement(int[] nums, int val){
         if(nums.length<1){
             return 0;
         }
         int res = 0;
         for(int i = 0; i < nums.length ; i++){
             if(nums[i]!= val){
                 nums[res] = nums[i];
                 res ++;
             }
         }
         return res;
     }
}