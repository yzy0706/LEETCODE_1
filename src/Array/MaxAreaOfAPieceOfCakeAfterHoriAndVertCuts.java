package Array;

import java.util.Arrays;

public class MaxAreaOfAPieceOfCakeAfterHoriAndVertCuts {
    // 做法: 这题还是跟array有关的, 主要就是去计算最宽的垂直间隔和水平间隔(包括到蛋糕的起始点和终点的), 就分别是最大的切块的高和宽, 然后再乘起来就行了.
    // 注意: 1. 这种题涉及到比较大的数都用long来表示;
    //      2. 好像mod在最后一步用Math.pow(10, 9) + 7来表示比1e9+7复杂度低一些
    // Runtime: O(nlog(n)), Space: O(1);

    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        long mH = 0, mW = 0;
        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);
        for(int i = 0; i <= horizontalCuts.length; i++){
            if(i == 0) mH = Math.max(mH, horizontalCuts[i]);
            else if(i == horizontalCuts.length) mH = Math.max(mH, h - horizontalCuts[i-1]);
            else mH = Math.max(mH, horizontalCuts[i] - horizontalCuts[i-1]);
        }
        for(int i = 0; i <= verticalCuts.length; i++){
            if(i == 0) mW = Math.max(mW, verticalCuts[i]);
            else if(i == verticalCuts.length) mW = Math.max(mW, w - verticalCuts[i-1]);
            else mW = Math.max(mW, verticalCuts[i] - verticalCuts[i-1]);
        }
        return (int)((mH * mW) % (Math.pow(10, 9) + 7));
    }
}
