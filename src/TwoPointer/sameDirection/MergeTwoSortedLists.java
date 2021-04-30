package TwoPointer.sameDirection;

public class MergeTwoSortedLists {
    // 做法： 跟那个list node的merge two sorted lists一样
    // Runtime:  O(n), Space: O(1)

    public int[] merge(int[] nums1, int m, int[] nums2, int n) {
        int cnt = m + n - 1;
        int[] res = new int[cnt + 1];
        int i = m - 1, j = n - 1;
        while(cnt >= 0){
            if(j == 0 || nums1[i] >= nums2[j]) res[cnt--] = nums1[i--];
            else res[cnt--] = nums2[j--];
        }
        return res;
    }

}
