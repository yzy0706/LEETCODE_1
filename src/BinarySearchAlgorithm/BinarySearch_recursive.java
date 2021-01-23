package BinarySearchAlgorithm;

public class BinarySearch_recursive {
    //做法： recursion的做法就是简单的把方程里面的start， end区间根据target在哪个区间更新成[start, mid]或者[mid+1, end]
    //Runtime: 每次一分为二再去寻找区间， 那么我们的总runtime就是这个分为二的次数， 也就是binary search tree的高度 O(log(n)), space就是O(1)

    public int binarySearch_recursive(int[] arr, int start, int end, int target){
        if(start > end) return -1;
        int mid = start + (end-start)/2;
        if(arr[mid] == target) return mid; //最好先比较target和mid
        if(arr[mid] > target) return binarySearch_recursive(arr, start, mid, target);
        return binarySearch_recursive(arr, mid+1, end, target); //要return [mid+1， end]， 而不是[mid, end]
    }
}
