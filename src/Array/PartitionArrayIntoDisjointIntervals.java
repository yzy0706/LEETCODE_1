package Array;

public class PartitionArrayIntoDisjointIntervals {
    // 做法: 这题我用的two pointer和greedy的结合
    // 1. 用lMax = A[0]记录一开始左边的array的最大值, pos = 0记录l到的位置, 在整个A的范围内forloop l.
    // 2. 每次都用一个临时的pointer r来往左尽可能的移动, 只要A[r]  >= A[l], 就往左移动, 并且用一个curMax记录r移动时碰到的最大值, 但现在还不能更新lMax
    // 3. 如果r直接移动到A.length了, 那证明l右边的数都大于等于它, 所以直接break然后 return pos + 1就行
    // 4. 走到下一步说明没有移动到A.length, 那么现在l就移动到r-1的位置, 为什么是r-1呢, 因为等下进入下一个forloop, l还要++, 然后用curMax更新一下lMax

    // 注意:
    //      1. 一开始没有去记录r移动的时候碰到的最大值(并且不能随时改变lMax), 所以一直在出错
    //      2. 因为l随着r尽可能移动的时候, l本身也在forloop里, forloop里面的跳跃都是跳到要跳的位置的前一位, 因为进入下一个forloop还要再++一次

    // Runtime: O(n), Space: O(1)

    public int partitionDisjoint(int[] A) {
        int lMax = A[0], pos = 0, len = A.length;
        for(int l = 0; l < len; l++){
            pos = l;
            int r = l + 1;
            int curMax = lMax;
            while(r < len && A[r] >= lMax) curMax = Math.max(curMax, A[r++]);
            if(r == len) break;
            lMax = curMax;
            l = r - 1;
        }

        return pos + 1;
    }
}
