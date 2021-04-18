package TwoPointer.diffrentDirection;

import java.util.ArrayList;
import java.util.LinkedList;
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
            if(x - arr[mid] > arr[mid+k] - x) l = mid + 1;
            else r = mid;
        }
        List<Integer> res = new ArrayList<>();
        for(int i = l; i < l + k; i++)  res.add(arr[i]);
        return res;
    }


    // 做法： 第一种是我自己写的two pointer的方法， 就是找到离x最近的那个位置以后分别用l和r来标记x前后的位置， 然后一直比较l和r到x的差来确定x左右离它最近的点
    // 然后我建立了一个LinkedList来分别储存比x小和大的距离最近的值， 这样就避免了最后还要sort
    // Runtime: O(n), Space: O(n)
    public List<Integer> findClosestElements_twoPointer(int[] arr, int k, int x) {
        LinkedList<Integer> res = new LinkedList<>();
        if(arr.length == 1 && k == 1){
            res.add(arr[0]);
            return new ArrayList<>(res);
        }
        int closest = 0;
        for(int i = 1; i < arr.length; i++){
            if(Math.abs(arr[i] - x) < Math.abs(arr[closest] - x)) closest = i;
        }
        res.add(arr[closest]);
        k--;
        int l = closest - 1, r = closest + 1;
        while(k > 0){
            if(l < 0){
                res.addLast(arr[r++]);
            }
            else if(r >= arr.length){
                res.addFirst(arr[l--]);
            }
            else{
                if(Math.abs(arr[r] - x) < Math.abs(arr[l] - x)){
                    res.addLast(arr[r++]);
                }
                else{
                    res.addFirst(arr[l--]); //如果两边的pointer的距离相等的话优先选左边的
                }
            }
            k --;
        }
        return new ArrayList<>(res);
    }
}
