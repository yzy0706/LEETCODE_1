package Array.maxSubarray;

import java.util.TreeSet;

public class MaxSumOfRectangleNoLargetThanK {
    //做法: 跟 MaxSumRectangle大体一样, 但在给了rowSums以后不是用kadane去求maxSumSubarray, 而是用TreeSet去求LargestSubarraySumCloseToK
    //Runtime: O(w*l^2), space: O(l)
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int w = matrix.length, l = matrix[0].length;
        int res = Integer.MIN_VALUE;

        for(int leftCol = 0; leftCol < l; leftCol ++){
            int[] rowSums = new int[w];
            for(int rightCol = leftCol; rightCol < l; rightCol ++){
                for(int i = 0; i < w; i++){
                    rowSums[i] += matrix[i][rightCol];
                }
                int result = getLargestSumCloseToK(rowSums, k);
                res = Math.max(res, result);
            }
        }
        return res;
    }

    public int getLargestSumCloseToK(int[] rowSums, int k){
        int sum = 0, res = Integer.MIN_VALUE;
        TreeSet<Integer> treeSet = new TreeSet<Integer>();
        treeSet.add(0);

        for(int i = 0; i < rowSums.length; i++){
            sum += rowSums[i];
            Integer ceiling = treeSet.ceiling(sum - k);
            if(ceiling != null){
                res = Math.max(res, sum - ceiling);
            }
            treeSet.add(sum);
        }

        return res;
    }
}
