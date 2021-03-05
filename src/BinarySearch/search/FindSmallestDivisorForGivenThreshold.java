package BinarySearch.search;

public class FindSmallestDivisorForGivenThreshold {
    //做法: 用binary search来找到最小的divisor:
    // 1. left是1， right是（int） 1e6, 分别是最小和最大的divisor的可能，正常的while loop, 然后求出m以后, 用nums上每一个数i求(i + m - 1)/ m, 加到临时的sum里面
    // 2.  a.如果最后求出来的sum <= threshold(就算 == 了divisor也还有可能有变小的空间), 证明divisor还能变小, right = m; (这里不等于m-1是因为之后如果找不到更小的divisor这个m可能还会重新被当成答案)
    //     b.如果求出来的sum > threshold, 证明divisor太小了, left = m + 1
    // Runtime: O(n), Space: O(1);
    public int smallestDivisor_discussion(int[] nums, int threshold) {
        int left = 1, right = (int) 1e6;
        while(left < right){
            int m = (left + right) /2;
            int sum = 0;
            for(int i : nums){
                sum += (i + m - 1)/m;
            }
            if(sum <= threshold) right = m; // m需要变小
            else left = m + 1;
        }
        return left;
    }



    public int smallestDivisor_slefr(int[] nums, int threshold) {
        int left = 1, right = (int) 1e6;
        while(left < right){
            int m = (left + right) /2;
            int sum = 0;
            for(int i : nums){
                if(i % m == 0) sum += i/m;
                else sum += i/m + 1;
            }
            if(sum <= threshold) right = m;
            else left = m + 1;
        }
        return left;
    }
}
