package Math.equalArrayElements;

import java.util.Arrays;

public class MinMovesToEqualArrayElementsII {
    //做法: 第一个做法主要是先sort, 然后用two pointer从左右两端开始 res += 左右两端的差, 其实就是增加左右两个端点到median的距离的和
    //Runtime: O(nlogn), Space: O(1)
    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int i = 0, j = nums.length - 1;
        int count = 0;
        while(i < j){
            count += nums[j] - nums[i]; //两边到median的距离
            j--;
            i++;
        }
        return count;
    }



    //还有一个quicksort的解法

    public int minMoves2_quickSelect(int[] A) {
        int sum = 0, median = quickselect(A, A.length/2+1, 0, A.length-1);
        for (int i=0;i<A.length;i++) sum += Math.abs(A[i] - median);
        return sum;
    }

    public int quickselect(int[] A, int k, int start, int end) {
        int l = start, r = end, pivot = A[(l+r)/2];
        while (l<=r) {
            while (A[l] < pivot) l++;
            while (A[r] > pivot) r--;
            if (l>=r) break;
            swap(A, l++, r--);
        }
        if (l-start+1 > k) return quickselect(A, k, start, l-1);
        if (l-start+1 == k && l==r) return A[l];
        return quickselect(A, k-r+start-1, r+1, end);
    }

    public void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
}
