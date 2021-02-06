package BinarySearch.search;

import java.util.List;

public class MostLeftColumnWithOne {
    interface BinaryMatrix {
        public default int get(int row, int col) {}
        public  List<Integer> dimensions{}
    };


    //做法: 直接做一个binary search, 每次检查mid这一个column里有没有1
    // 1. 有1的话更新一下res, 把end也变成mid-1
    // 2. 当前mid的column里没有1, 直接把start变成mid+1看之后有没有1

    // Runtime: O(log(l)*w),在整个matrix的长度的基础上做binary search, space: O(1);
    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        List<Integer> dimen = binaryMatrix.dimensions();
        int m = dimen.get(0), n = dimen.get(1);
        int left = 0, right = n - 1, ans = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (existOneInColumn(binaryMatrix, m, mid)) {
                ans = mid;          // record as current ans
                right = mid - 1;    // try to find in the left side
            } else {
                left = mid + 1;     // try to find in the right side
            }
        }
        return ans;
    }

    boolean existOneInColumn(BinaryMatrix binaryMatrix, int m, int c) {
        for (int r = 0; r < m; r++) if (binaryMatrix.get(r, c) == 1) return true;
        return false;
    }


    //第二种做法： 直接从右上角开始往下， 如果碰到0直接往下找1， 碰到1往左找1
    //Runtime: O(w+l), space: O(1)

    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        List<Integer> dimen = binaryMatrix.dimensions();
        int m = dimen.get(0), n = dimen.get(1);
        int ans = -1, r = 0, c = n - 1;
        while (r < m && c >= 0) {
            if (binaryMatrix.get(r, c) == 1) {
                ans = c; // record as current ans
                c--;
            } else {
                r++;
            }
        }
        return ans;
    }



//第二遍改的
    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        List<Integer> dimension = binaryMatrix.dimensions();
        int w = dimension.get(0), l = dimension.get(1);
        int res = -1;
        int start = 0, end = l - 1;
        while(start <= end){
            int mid = start + (end - start)/2;
            if(existOneInCol(l, mid, binaryMatrix)){
                res = mid;
                end = mid - 1;
            }
            else{
                start = mid + 1;
            }
        }
        return res;
    }


    public boolean existOneInCol(int l, int mid, BinaryMatrix binaryMatrix){
        for(int i = 0; i < l; i++){
            if(binaryMatrix.get(i, mid) == 1) return true;
        }
        return false;
    }




    //第一遍自己做的
    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        List<Integer> dimension = binaryMatrix.dimensions();
        int w = dimension.get(0), l = dimension.get(1);
        int res = Integer.MAX_VALUE;

        for(int i = 0; i < w; i++){
            int start = 0;
            int end = l - 1;
            while(start <= end){
                int mid = (start + end)/2;
                if(binaryMatrix.get(i, mid) == 1 && (mid == 0 || l == 1 || binaryMatrix.get(i, mid-1) == 0)){
                    res = Math.min(res, mid);
                    break;
                }
                else if(binaryMatrix.get(i, mid) == 0){
                    start = mid+1;
                }
                else if(binaryMatrix.get(i, mid) == 1 && (binaryMatrix.get(i, mid-1) == 1)){
                    end = mid-1;
                }
                // System.out.println(mid);
            }
        }

        return res == Integer.MAX_VALUE ? -1 : res;
    }
}
