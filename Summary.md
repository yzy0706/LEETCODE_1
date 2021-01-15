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
   
   Heap Sort:
   做法： 从最后一个父亲节点开始maxHeapify它所对应的左右叶子节点以及之下的树， 一直到0， 
         然后再用一个forloop从最后一个节点开始swap(0, i)并再做一次范围递减的maxHeapify, 一直到1为止
   Runtime： 我们heapify一个二叉树是深度是log(n)， n个点每个点我们都有可能交换到树的最底下， 所以是O(nlog(n)), Space: O(1)
   
   public void heapSort(int[] nums){
     int lastIndex = nums.length - 1, lastFather = nums.length/2 - 1;
     for(int i = lastFather; i > 0; i--){
        maxheapify(nums, i, lastIndex);
     }
     for(int j = lastIndex; j > 0; j--){
        swap(j, 0);
        maxHeapify(nums, 0, j-1);
     }
   }
   
   public void swap(int i, int j, int[] nums){
     int temp  = nums[i];
     nums[i] = nums[j];
     nums[j] = temp;
     }
     
   public void maxheapify(int[] nums, int start, int lastIndex){
     int lSon = start*2+1, rSon = lSon+1, curMaxSon = lSon;
     if(lSon > lastIndex) return;
     if(rSon < lastIndex && nums[rSon] > nums[lSon]) curMaxSon = lSon;
     if(nums[curMaxSon] > nums[start]){
       swap(curMaxSon, start, nums);
       maxheapify(nums, curMaxSon, lastIndex);
       }
     }
     
   
   
        