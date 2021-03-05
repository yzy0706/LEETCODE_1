package LinkedList;

public class RemoveLinkedListElements {
    //做法: dp[i][j] 代表当前这一步的状态下从起始点到当前这个位置的可能性, 从0到K-1每一步都用到了一个temp的matrix来承接dp下一步变化, 不然会出现dp[prevI][prevJ]已经是被修改过的状态
    // 1. forloop step 从0到k-1步, 每一步新建一个double[][] temp来记录当前dp的变化
    // 2. forloop i和j, forloop表示移动的dirs, temp[i][j] += dp[prevI][prevJ] * 0.125; forloop结束以后dp = temp;

    // Runtime: O(N^2*K*8), Space: O(N^2)

    public double knightProbability(int N, int K, int r, int c) {
        int[][] dirs = new int[][]{{2,1}, {2, -1}, {-2, 1}, {-2, -1}, {1, 2}, {1, -2}, {-1, -2}, {-1, 2}};
        double[][] dp = new double[N][N];
        dp[r][c] = 1;
        double res = 0;
        for(int step = 0; step < K; step++){
            double[][] temp = new double[N][N];
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    for(int[] dir : dirs){
                        int prevI = i - dir[0];
                        int prevJ = j - dir[1];
                        if(prevI >= 0 && prevI < N && prevJ >= 0 && prevJ < N){
                            temp[i][j] += dp[prevI][prevJ] * 0.125;
                        }
                    }
                }
            }
            dp = temp;
        }
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                res += dp[i][j];
            }
        }

        return res;
    }
}
