package binarySearch;
//做法: 这题主要是拿一个tails[nums.length]来储存每到i个index, 也就是subsequence为i+1的长度的时候, 最小的最后一个数.、
// 主要方法就是对每一个数foreach loop, 在这个loop里用binarySearch找跟这个数n最近的tails里的数并赋值,
// 假设说这个n正好到了当前以size为界的最后一个数, 那么就size++
//Runtime: O(nlogn). space O(n)

public class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums){
        int[] tails =  new int[nums.length];
        int longestLength  = 0;
        for(int n : nums){
            int i  = 0, j = longestLength;
            while(i != j){
                int m = (i+j)/2;
                if(tails[m] < n){ //如果小于n进入右边的区间
                    i = m+1;
                }
                else{
                    j = m; //如果 >= 进入左边的区间
                }
            }
            tails[i] = n; //i == j, 意味着在tail上找到和n最接近的数了
            if(i == longestLength) longestLength++; //如果正好找到最后一位， 那么最长的长度就++

        }

    }
}
