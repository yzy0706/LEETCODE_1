package DynamicProgramming.divideToSubproblems;

public class ChampagneTower {

//做法: 因为是query_row和query_glass都是0 indexed, 所以用一个double[query_row+1][query_glass + 2]来表示能影响到我要求的杯子的上面的杯子
    //1. 对于每一行来说, 我们forloop行从0到query_row-1, 这些都是可以影响我们目标位置的; 然后我们forloop j从0到query_glass, 因为query_glass会影响下面和右下的杯子
    //2. 每一个杯子, 如果能把自己装满, 就会倾倒出当前流到这个杯子剩下的香槟: double remain = Math.max(0.0, (dp[i][j] - 1.0) / 2.0);
    //然后下面dp[i+1][j]和右下角的杯子dp[i+1][j+1]都会接到当前流出来的remain
    //3. 最后 return Math.min(1.0, dp[query_row][query_glass])

    // Runtime: O(RC), Space: O(RC), R是query_row, C是query_glass
    public double champagneTower_2d(int poured, int query_row, int query_glass) {
        double[][] dp = new double[query_row+1][query_glass+2];
        dp[0][0] = poured;
        for(int i = 0; i < query_row; i++){
            for(int j = 0; j <= query_glass; j++){
                double remain = Math.max(0.0, (dp[i][j] - 1.0) / 2.0);
                dp[i+1][j] += remain;
                dp[i+1][j+1] += remain;
            }
        }
        return Math.min(1.0, dp[query_row][query_glass]);
    }



    //这个lee的做法就是把一个2d转换成了1d

    public double champagneTower_lee_1d(int poured, int query_row, int query_glass) {
        double[] res = new double[query_row + 2];
        res[0] = poured;
        for(int row = 1; row <= query_row; row ++){
            for(int i = row; i >= 0; i--){
                res[i] = Math.max(0.0, (res[i] - 1)/2); //res[i]现在还是代表上一层的，然后 (res[i]-1)/2就表示留给下一层的remain了
                res[i+1] += res[i]; //然后res[i+1]先提前加上这个remain
            }
        }
        return Math.min(1.0, res[query_glass]);
    }








    //第一遍自己想用纯数学解答
    public double champagneTower(int poured, int query_row, int query_glass) {
        double beforeRow = (1 + query_row) * query_row / 2;
        double thisRow = beforeRow + query_row + 1;
        if(poured <= beforeRow) return 0.0;
        if(poured >= thisRow) return 1.0;
        if(poured - beforeRow >= query_row){
            if(query_glass == 0 || query_glass == query_row){
                return (poured - beforeRow - query_row + 1 - 2)/2;
            }
            else{
                return 1.0;
            }
        }
        if(poured - beforeRow < query_row){
            if(query_glass == 0 || query_glass == query_row){
                return (poured - beforeRow) * 1/((query_row-1)*2+2);
            }
            else{
                return (poured - beforeRow) * 2/((query_row-1)*2+2);
            }
        }
        return 0;
    }
}
