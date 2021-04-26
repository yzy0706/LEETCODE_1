package Sort.quickSort;

import java.util.Arrays;

public class KClosestToOrigin {

    // 做法: 这个解法用到了quicksort的概念
    // 1. 先用two pointer l和r来浏览整个points
    // 2. 对于每一对l和r, 我们都去quickSort(points, l, r); return 一个points[l]应该在整个points里面的位置 : keyID
    //      a. 我们知道keyID包括它的左边位置都是比它小的数, 所以如果keyID == K - 1; 那么前K个数都已经sort好了, 直接break
    //      b. 如果keyID < K-1, 那么证明keyID右边还有需要sort的数, l = keyID + 1;
    //      c. 如果keyID > K-1, 那么只是keyID左边的数都比它小, 并不代表它左边都sort好了, 所以r = keyID - 1, 继续sort keyID的左边

    // Runtime: O(n), Space: O(1);


    public int[][] kClosest_quickSort(int[][] points, int K) {
        int len = points.length, l = 0, r = len-1;
        while(l <= r){
            int keyID = quickSort(points, l, r);
            if(keyID == K-1) break;
            if(keyID < K-1) l = keyID + 1;
            else r = keyID - 1;
        }
        int[][] res = new int[K][2];
        for(int i = 0; i < K; i++) res[i] = points[i];
        return res;
    }

    private int quickSort(int[][] points, int l, int r){
        int[] key = points[l];
        if(l >= r) return l;
        while(l < r){
            while(compare(points[r], key) >= 0 && r > l) r--;
            if(r > l) points[l++] = points[r];
            while(compare(points[l], key) < 0 && l < r) l++;
            if(l < r) points[r--] = points[l];
        }
        points[l] = key; //最后一个l == r的时候就是key应该在的位置
        return l;
    }

    private int compare(int[] a, int[] b){
        return a[0] * a[0] + a[1] * a[1] - (b[0] * b[0] + b[1] * b[1]);
    }


    // 做法： 第一遍做， 最简单的建立comparator的做法
    // Runtime: O(nlog(n)), Space: O(1)
    public int[][] kClosest(int[][] points, int K) {
        Arrays.sort(points, (a, b) -> ((a[0] * a[0] + a[1] * a[1]) - (b[0] * b[0] + b[1] * b[1])));
        int[][] res = new int[K][2];
        for(int i = 0; i < K; i++) res[i] = points[i];
        return res;
    }
}
