package BinarySearchAlgorithm;

public class BinarySearch_whileLoop {
    //做法：这题也只是简单的在whileloop里更新start和end， 而不是在recursion里更新start和end， 因为我们每次比较新的区间比如说[start, mid]的时候
    //并且计算mid的时候都是没有把start和end这个位置包括进去， 所以我们最后要考虑start = mid的情况来考虑最后一点是不是 arr[start] == target
    // Runtime: 也是O(log(n)), 除了没有用recursion意外其他的原理都是一样的， Space: O(1);
    public int binarySearch(int[] arr, int target){
        int start = 0, end = arr.length-1;
        while(start <= end){ //start是可以 == end的， 特别注意
            int mid = start + (end-start)/2;
            if(arr[mid] == target) return mid;
            if(arr[mid] > target) end = mid;
            else if(arr[mid] < target) start = mid+1;
        }
        return -1;
    }
}
