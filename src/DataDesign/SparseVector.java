package DataDesign;

public class SparseVector {
    //做法: 就设计了一个int[]来储存当前的vector, 然后再直接求他们之间的vector, 没搞懂要考啥
    //Runtime: O(n), Space: O(n)
    int[] store;
    SparseVector(int[] nums) {
        store = nums;
    }

    // Return the dotProduct of two sparse vectors
    public int dotProduct(SparseVector vec) {
        int[] store2 = vec.store;
        int res = 0;
        for(int i = 0; i < store.length; i ++){
            res += store[i] * store2[i];
        }
        return res;
    }
}

// Your SparseVector object will be instantiated and called as such:
// SparseVector v1 = new SparseVector(nums1);
// SparseVector v2 = new SparseVector(nums2);
// int ans = v1.dotProduct(v2);

