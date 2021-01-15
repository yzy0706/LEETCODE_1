package Sort;

import java.util.Arrays;

public class WiggleSort {
    //第二遍用了discuss上类似于dp的解法， if and only if 当前这个数是奇数位而且比前面那个数小的时候我们swap这个数和前面那个数

    //第一遍自己想用类似于merge two sorted lists的方法写， 好像错了
    public void wiggleSort(int[] nums) {
        Arrays.sort(nums);
        int i = 0, m = (i+ nums.length-1)/2, j = m+1;
        int k = 0;
        int[] temp = new int[nums.length];
        while(i <= m && j <= nums.length){
            if(k % 2 == 0) temp[k++] = nums[i++];
            else temp[k++] = nums[j++];
        }
        nums = temp;
    }
}
