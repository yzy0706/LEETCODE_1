package Math.algebra;

public class RectangleOverlap {
    //做法: 就是把interval是否重合的方法运用进来: l, r, b, t分别是每个长方形的左边、右边、底边、顶边的常数函数
    //1. 如果[l1, r1]到[l2, r2]不重合, 或者[b1, t1]到[b2, t2]不重合, 则不相交, return false;
    //2. 反之, 如果两个interval都重合证明这两个长方形重合
    // Runtime: O(n), Space: O(1);

    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        int l1 = rec1[0], r1 = rec1[2], b1 = rec1[1], t1 = rec1[3];
        int l2 = rec2[0], r2 = rec2[2], b2 = rec2[1], t2 = rec2[3];
        if(Math.min(r1, r2) <= Math.max(l1, l2) || Math.min(t1, t2) <= Math.max(b1, b2)) return false;
        return true;
    }
}
