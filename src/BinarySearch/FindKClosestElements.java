package BinarySearch;

import java.util.ArrayList;
import java.util.List;

public class FindKClosestElements {
    //  做法: 第二种做法是binary search的做法, 核心是去找到那个l, 让x - arr[l] <= arr[l+k] - x, 这样就能找到arr上的连续k个数, 他们都离x最近
    // 1. 设置l = 0, r = arr.length - k;
    // 2. 正常binary search的whileloop, 根据x - arr[mid] <= arr[mid+k] - x来判断下一段是l到mid还是 mid+1到r
    // Runtime: O(log(n)), Space: O(1)

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int l = 0, r = arr.length - k;
        while(l < r){
            int mid = (l + r)/2;
            if(x - arr[mid] > arr[mid + k] - x) l = mid + 1;
            else r = mid;
        }
        List<Integer> res = new ArrayList<>();
        for(int i = l; i < l + k; i++)  res.add(arr[i]);
        return res;
    }
}
