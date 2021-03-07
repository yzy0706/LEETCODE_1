package Sort.selectionSort;

import java.util.ArrayList;
import java.util.List;

public class PancakeSorting{
    //做法: 跟selection sort有点像
    // 1. 首先这个arr的最大值肯定是这个arr的长度x, 当x > 1时x一直--, 相当于每次都把x放到先翻到第一位, 再翻到最后一位
    // 2. 对于每一个x的值, 我们都有一个i从0开始一直到arr[i] = x, 然后找到以后把包含当前x的这一条arr整个翻转一下, res.add(i+1); 然后再把x长度
    public List<Integer> pancakeSort(int[] arr) {
        List<Integer> res = new ArrayList<>();
        if(arr.length < 1) return res;
        for(int x = arr.length; x > 1; x--){ //x是每一轮的最大值
            int i = 0;
            while(arr[i] != x) i++;
            reverse(arr, i+1);
            res.add(i+1);
            reverse(arr, x); //从最大值的长度reverse一遍
            res.add(x);
        }
        return res;
    }

    public void reverse(int[] arr, int len){
        int i = 0, j = len - 1;
        while(i < j){
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
    }
}
