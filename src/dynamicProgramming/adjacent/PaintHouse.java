package dynamicProgramming.adjacent;

public class PaintHouse {
    //这题因为matrix的每个row的长度是确定三个, 也就是颜色的种类, 所以我们不需要考虑别的, 只要每次直接拿这个costs的每一个row forloop, 加上上一行不同的两个颜色的最小cost就好了
    //Runtime: O(n), 每一个unit访问了一次, Space(O(1)), 并没有借助别的数据结构
    public int minCost(int[][] costs) {
        int w = costs.length;
        if(w < 1) return 0;
        for(int i = 1; i < w; i++){
            costs[i][0] += Math.min(costs[i-1][1], costs[i-1][2]);
            costs[i][1] += Math.min(costs[i-1][0], costs[i-1][2]);
            costs[i][2] += Math.min(costs[i-1][0], costs[i-1][1]);
        }
        return Math.min(costs[w-1][0], Math.min(costs[w-1][1], costs[w-1][2]));
    }
}
