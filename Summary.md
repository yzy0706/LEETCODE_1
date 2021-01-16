# LEETCODE_1
Sort:
1. Sort的几大基础算法：
   
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
   
   
   Quick Sort:
   做法：挖坑原理， 首先假如l > r直接return，否则把key设置为arr[l]； 
   然后设置i， j分别等于l， r； 然后折翼一个i < j的whileloop， 从右边看起，j或者i向中间移动，比较j，i位置的数
   是不是比key大， 是的话就拿对方置换自己并且再向中间移动一位， 这样对方就变成一个坑了， 当whileloop结束的
   时候i肯定等于j，那么当前的位置应该就要是当前key应该处于最后sort好的数列的位置， 因为他的左右两边相对于他
   已经是左小右大了，所以结束了以后再把不包括自己的左右两部分继续recursion， 直到l==r为止
   Runtime: 分割左右的数n次， O(nlog(n)), space O(1)

       public void quickSort(int[] nums, int l, int r){
       if(l >= r) return; 
       int i = l, j = r, key = nums[l];
       while(i < j){
        while (i < j && nums[j] > nums[i]){
           j--;
        }
        if(i < j && nums[j] < nums[i]){
           nums[j--] = nums[i];
        }
        while(i < j && nums[i] <= nums[j]){
           i++
        }
        if(i < j && nums[i] >= nums[j]){
            nums[i++] = nums[j];
       }
      }
       nums[i] = key;
       quickSort(nums, l, i-1);
       quickSort(nums, i+1, r);
    }
  
  Merge Sort
  做法： 首先和quick sort一样如果l >= r就直接return了， 
  
    public static void mergeSort(int[] arr, int l, int r){
     if(l < r){
     int m = (l+r)/2;
     mergeSort(arr, l, m);
    mergeSort(arr, m+1, r); //把两边都分割并排序成只有一个的情况
    merge(arr, l, r); //两边都处理完以后再把两边都merge
    }
    }
   

    public static void merge(int[] arr, int l, int r){
        int i = l, m = (l+r)/2, j = m+1, k = 0;
        int[] temp = new int[arr.length];
        //标准的合并两个有序数列的过程
        while(i <= m && j <= r){
            if(arr[i] < arr[j]){
                temp[k++] = arr[i++];
            }
            else{
                temp[k++] = arr[j++];
            }
        }
        while(i <= m){  //把剩下的数都送到temp里面去， 左右肯定有一个队列已经被排完了
            temp[k++] = arr[i++];
        }
        while(j <= r){
            temp[k++] = arr[j++];
        }
        for(int ind = 0; ind < k; ind++){ //这里k是代表着这个temp的长度，那么从l+0开始+ind，最后一位应该是l+k-1, 把temp里的值都放到arr里去
            arr[l+ind] = temp[ind];
        }
  
  SelectionSort
做法： forloop i， 里面再forloop一个j从最右边到i， 每次都把最小的值赋值到i
Runtime： O(n^2), space: O(1)

    public void selectionSort(int[] nums){
        for(int i = 0; i < nums.length; i++){
            int minIndex = i;
            for(int j  = nums.length-1; j >= i; j--){
                if(nums[j] < nums[i]) minIndex = j;
            }
            if( minIndex != i){
            int temp = nums[i];
            nums[i] = nums[minIndex];
            nums[minIndex] = nums[i];
            }
        }
    }


   BucketSort
做法： 用一个有限的bucket来限制所有的数， 分别按照最后一个位数和不同的位数放到cnt里面两次（一共两位数）， 排列的过程中按照相同的
位数把位数相同的所有的数分在一个位置上， 用int[] cnt来记录他们的数量， 然后再叠加cnt上的数量， 则这个数当前的cnt的大小就他在这个数列上应该处于的位置
他应该在这个数列里所处的位置， 从所有数的最后一位开始一一分配

    public void bucketSort(int[] nums, int digit){
        int num = nums.length, remainderAgent = 1;
        int[] temp = new int[nums.length];
        for(int i = 0; i < digit; i++, remainderAgent *= 10){
            int[] cnt = new int[10];
            for(int i = 0; i < num; i++){
                cnt[nums[i]/remainderAgent % 10]++;
            }
            for(int i = 1; i < 10; i++){
                cnt[i] += cnt[i-1];
            }
            for(int j = num-1; j >= 0; j--){
                cnt[nums[j]/remainderAgent % 10] --;
                temp[cnt[nums[j]/remainderAgent % 10]] = nums[j];
            }
            for(int i = 0; i < temp.length; i++){
                nums[i] = temp[i];
            }
        }
    }

2. 几种题型的解法： 
   
a. 有关于interval的题：
    
1. 一般要sort一堆interval， 那么我们根据interval[0]来比较就好

2. 比较两个interval我们一般用右边比较左边, 接下来这个取自insert interval：
     


            
                while (insert[0] > intervals[i][1]){
                    res.add(interval);
                    i++;
                }
                while (insert[0] <= intervals[i][1]){
                    insert[0] = Math.min(intervals[i][0], insert[0]);
                    insert[1] = Math.max(intervals[i][1], insert[1]);
                    i++;
                }
                res.add(insert);
                while (i < intervals.length){
                    res.add(intervals[i]);
                }

b.  Anagram, String之类sort之后比较char或者string的题:

1. 内容一样但顺序不一样的东西sort一下再比较就可以了， 比如如果要比较两个anagram直接可以把他们变成char[]然后sort一下再比较。

2. 两个单纯用数字组成的String可以直接compareTo：

        Comparator<String> com = (o1, o2) -> {
            String s1 = o1 + o2;  //o1放前面
            String s2 = o2 + o1;  //o2放前面
            return s2.compareTo(s1);
         };

c.  有负数让你sort平方以后的情况的题：

   1. 应该要结合two pointer和merge sort(例子来自SortTransformedArray), 如果是正系数平方可能要从大往小开始排:
      
               ...
               int l = 0, r = nuns.length-1;
               int StartIndex = a >= 0? l : r;
               while(l <= r){
                  if(a >= 0) temp[startIndex--] = calculate(nums[l]) >= calculate(nums[r])? nums[l++] : nums[r--];
                  if(a < 0) temp[startIndex++] = calcalate(nums[l]) >= calculate(nums[r])? nums[r--] : nums[l++];
               }
            
      
    
   
   
   
   

   

   

     
   
   
        