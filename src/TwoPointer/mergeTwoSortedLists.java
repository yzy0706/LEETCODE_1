package TwoPointer;

public class mergeTwoSortedLists {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        for(int i = 0; i < nums2.length ; i++){
            nums1[m]=nums2[i];
            m++;
        }
        Arrays.sort(nums1);
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int cnt = m+n-1;
        m=m-1;
        n=n-1;

        while(m>=0&&n>=0){
            if(nums2[n]>=nums1[m]){
                nums1[cnt]=nums2[n];
                n--;
                cnt--;
            }
            else{
                nums1[cnt]=nums1[m];
                m--;
                cnt--;
            }
        }

        while(n>=0){
            nums1[cnt]=nums2[n];
            cnt--;
            n--;
        }
    }
}
