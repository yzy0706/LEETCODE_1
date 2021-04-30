package Temp;

import java.util.List;

public class PackagingAutomation {
    //就是sort一下然后简单比较一下 arr[n] 和 arr[n-1]+1哪个更小就用哪个
    //Runtime: 因为sort了又浏览了n次所以是O(nlog(n)), space是O(n), 就是
    public int packagingAutomation(int numGroups, List<Integer> arr){
        arr.sort((a, b) -> b - a);
        if(arr.get(0) != 1) return 0;
        int[] l = new int[numGroups];

        int cnt = 0;
        for(Integer i : arr){
            l[cnt] = i;
            cnt++;
        }

        for(int i = 1; i < arr.size(); i++){
            int cur = arr.get(i);
            cur = Math.min(arr.get(i-1)+1, arr.get(i));
            l[i] = cur;
            }

            return l[numGroups-1];

    }
}
