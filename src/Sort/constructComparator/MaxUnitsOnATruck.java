package Sort.constructComparator;

import java.util.Arrays;

public class MaxUnitsOnATruck {
    // 做法: 用的sort和greedy, 把unit最多的box都优先装到truck里
    // Runtime: O(nlog(n)), Space: O(nlog(n));

    public int maximumUnits(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, (a, b) -> (b[1] - a[1]));
        int res = 0, i = 0;
        while(truckSize > 0 && i < boxTypes.length){
            int[] type = boxTypes[i++];
            if(type[0] >= truckSize){
                res += truckSize * type[1];
                break;
            }
            else{
                res += type[0] * type[1];
                truckSize -= type[0];
            }
        }
        return res;
    }
}
