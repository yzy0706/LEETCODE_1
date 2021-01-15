# LEETCODE_1
Sort:
1. Sort的几大算法：
   Bubble Sort:
   做法： 从最后一位开始如果小于前面一位两两置换， 如果本次forloop没有出现置换则直接break；
   Runtime: O(N^2), Space: O(1)
   
   public void bubbleSort(int[] nums){
   boolean flag = false;
   for(int i = 0; i < nums.length; i++){
      for(int j = nums.length - 1; j > 0; j--){
        if(nums[j] < nums[j-1]){
        int temp = nums[j];
        nums[j] = nums[j-1];
        nums[j-1] = temp;
        flag = true;
        }
        if(flag = false) break;
      }
   }
   
   Insert Sort:
   做法： 从当前i+1位用whileloop往前一直跟比他大的互换
   Runtime: O(N^2), Space: O(1)
   
   public void insertSort(int[] nums){
   for(int i = 0; i < nums.length-1; i++){
     int j  = i+1;
     while(j > 0 && nums[j] < nums[j-1]){
        int temp = nums[j];
        nums[j] = nums[j-1];
        nums[j-1] = temp;
     }
    }
   }
    
        