package DynamicProgramming.matrix;

public class BombingEnemy {
    // 做法: 就是建立两个matrix分别代表fromBottom和fromLeft的不受阻隔的enemy个数,
    //如果不是W且是'E'就从旁边那个+1(考虑边界情况).
    //如果是'W'就直接 = 0, 代表断了

    // 再建立两个int[]分别代表fromLeft和fromUp的Enemy个数,
    //如果碰到不是0
    //且不是'W': 就是当前这一行或者这一列的当前遇到所有个数+1
    //是‘W': 当前的遇到的所有个数归零
    //如果碰到是0, 直接四个值相加, 更新res能杀最多敌人的个数

    //Runtime: O(mn). Space: O(mn);
    public int maxKilledEnemies(char[][] grid) {
        int w = grid.length;
        if(w == 0) return 0;
        int l = grid[0].length;

        int[][] fromBottom = new int[w][l];
        int[][] fromRight = new int[w][l];

        for(int i = w-1; i >= 0; i--){
            for(int j = l-1; j >= 0; j--){
                int addEnemy = grid[i][j] == 'E'? 1 : 0;
                if(grid[i][j] != 'W'){
                    fromBottom[i][j] = (i == w-1) ? addEnemy : fromBottom[i+1][j] + addEnemy;
                    fromRight[i][j] = (j == l-1) ? addEnemy : fromRight[i][j+1] + addEnemy;
                }
                else{
                    fromBottom[i][j] = 0;
                    fromRight[i][j] = 0;
                }
            }
        }

        int[] fromUp = new int[l];
        int[] fromLeft = new int[w];
        int res = 0;

        for(int i = 0 ; i < w; i++){
            for(int j = 0; j < l; j++){
                if(grid[i][j] != '0'){
                    fromLeft[i] = grid[i][j] == 'W' ? 0 : fromLeft[i] + 1;
                    fromUp[j] = grid[i][j] == 'W' ? 0 : fromUp[j] + 1;
                }
                else{
                    res = Math.max(fromBottom[i][j] + fromRight[i][j] + fromLeft[i] + fromUp[j], res);
                }
            }
        }

        return res;
    }
}
