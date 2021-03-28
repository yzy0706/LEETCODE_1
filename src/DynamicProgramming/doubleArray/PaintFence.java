package DynamicProgramming.doubleArray;

public class PaintFence {
    // 准备两个dp, same和diff, 一个代表same[i]个步骤和i-1的步骤的颜色是一样的, diff[i]表示第i个步骤和i-1个步骤的颜色是不一样的,
    // 两个的第一步都是k, 然后从1开始forloop, same[i]肯定等于diff[i-1]， 因为最多两个连续一样， 而diff[i-1]记载的是跟他前面的不一样
    //的时候的分配个数，而diff[i] = (k-1) * diff[i-1] + (k-1) *same[i-1]因为如果i不一样的话要么就跟前面的不一样， 要么就跟前面的前面不一样
    // Runtime: O(n), space是两个int[]也是O(n)
    public int numWays(int n, int k) {
        if(n < 1 || k < 1) return 0;
        if(n == 1) return k;
        int[] same = new int[n];
        int[] diff = new int[n];
        same[0] = k;
        same[1] = k;
        diff[0] = k;
        diff[1] = k* (k-1);
        for(int i = 1; i < n; i++){
            // the i-th in same should be equal the previous one in diff since only two consectutive same are allowed
            same[i] = diff[i-1];
            // the i-th in diff should be either different from its previous one or from the one
            // before the previous one
            diff[i] = (k-1) * diff[i-1] + (k-1) *same[i-1];
        }
        return same[n-1] + diff[n-1];

    }
}
