package Array.rearrange;

public class MergeSortedArray {
    // 做法: 就是一个反向的三个pointer的merge two sorted lists, 如果跑完了大的whileloop我们就不用管剩下的nums1的pointer了. 因为nums1里面没有分配进去的
    // 肯定是都比nums2当前的数小的, 不然nums1里的数不会被分配到后面去, 所以我们只需要出来以后再forloop完nums2里面剩下的数就行

    // Runtime: O(m+n), Space: O(m+n)
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int tailAll = m + n - 1, tail1 = m - 1, tail2 = n - 1;
        while (tail1 >= 0 && tail2 >= 0) {
            if (nums1[tail1] > nums2[tail2]) {
                nums1[tailAll--] = nums1[tail1--];
            } else {
                nums1[tailAll--] = nums2[tail2--];
            }
        }
        // while(tail1 >= 0){
        //     nums1[tailAll--] = nums1[tail1--];
        // }
        while (tail2 >= 0) {
            nums1[tailAll--] = nums2[tail2--];
        }
    }

    public void merge_wrong(int[] nums1, int m, int[] nums2, int n) {
        int i = 0, j = 0, tmp = 0;
        int[] temp = new int[m+n];

        while(i < m && j < n){
            if(nums1[i] <= nums2[j]){
                temp[tmp++] = nums1[i++];
            }
            else{
                temp[tmp++] = nums2[j++];
            }
        }

        while(i < m){
            temp[tmp++] = nums1[i++];
        }

        while(j < n){
            temp[tmp++] = nums2[j++];
        }

        nums1 = temp;
    }
}
