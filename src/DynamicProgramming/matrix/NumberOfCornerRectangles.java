package DynamicProgramming.matrix;

public class NumberOfCornerRectangles {
    // 做法: dp[j][k]记录的是这整个matrix浏览到当前位置前, 同一个row里分别以j和k为column数且都为1的边长数量,
    // 1. forloop matrix里所有的i, j位置
    // 2. forloop 从j+1开始在同一个row里还有没有也是1的位置dp[i][k], 有的话答案加上顶边的数量 res += dp[j][k]; dp[j][k]++;
    // Runtime: O(mn^2), Space: O(mn)
    public int countCornerRectangles(int[][] grid) {
        int m = grid.length;
        if(m <= 1) return 0;
        int n = grid[0].length;
        if(n <= 1) return 0;
        int res = 0;
        int[][] dp = new int[n][n]; //列数对列数, 如果同一行不同的j都是1的话, 可以作为一个边使用, 看有多少个这样的边
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 1){
                    for(int k = j + 1; k < n; k++){ //继续沿着j的位置向右移
                        if(grid[i][k] == 1){
                            res += dp[j][k]; //加上以当前这个j和k为界的边的数量
                            dp[j][k]++; //当然又多找到了一个以当前这个j和k为界的边
                        }
                    }
                }
            }
        }
        return res;

    }
}
