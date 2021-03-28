package Math.algebra;

public class ValidSquare {
    //做法: 计算当前所有的点的距离, 放在一个dists里
    // 1. forloop所有的dist, 大的就是diag, 小的就是side, 把diag和side求出来
    // 2. 再forloop一遍所有的dist, 如果dist == diag, 增加一次diag出现的次数, 如果当前dist != diag, 如果也不等于side, 那就return false;

    // Runtime: O(n), Space: O(n);
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        long side = Integer.MAX_VALUE, diag = -1;
        long[] dists = new long[]{calDistance(p1, p2), calDistance(p1, p3), calDistance(p1, p4), calDistance(p2, p3), calDistance(p2, p4), calDistance(p3, p4)};
        for(long dist : dists){
            side = Math.min(side, dist);
            diag = Math.max(diag, dist);
        }
        int numDiag = 0;
        for(long dist : dists){
            if(dist == diag) numDiag ++;
            else if(dist != side) return false;
        }
        // System.out.println(side);
        return numDiag == 2;
    }

    public long calDistance(int[] p1, int[] p2){
        return (long)Math.pow(p1[0] - p2[0], 2) + (long)Math.pow(p1[1] - p2[1], 2);
    }
}
