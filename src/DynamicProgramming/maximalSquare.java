package DynamicProgramming;

public class maximalSquare{
    //dp解法：d if(matrix[i-1][j-1] == '1') dp[i][j] = Math.min(dp[i-1][j],Math.min(dp[i-1][j-1], dp[i][j-1])) + 1;
    public int maximalSquare(char[][] matrix) {
        int w = matrix.length, l = w > 0? matrix[0].length : 0;
        int res = 0;
        int[][] dp = new int[w+1][l+1];
        for(int i = 1; i <= w; i++){
            for(int j = 1; j <= l; j++){
                if(matrix[i-1][j-1] =='1'){
                    dp[i][j] = Math.min(dp[i-1][j],Math.min(dp[i-1][j-1], dp[i][j-1])) + 1;
                    res = Math.max(dp[i][j], res);
                }
            }
        }
        return res * res;
    }

    // 第一遍是用brutal force的解法, 主要就是去找每一个i，j所能找到的右下方最大的square，
    // 方法主要就是假定一个暂时的最大长度curL, 再从现在初始的i，j开始确认到i+curL之前是不是所有对应的[i][j+curL]和[i+curL][j]都对应为1
    public int maximalSquare(char[][] matrix) {
        int w = matrix.length, l = w > 0? matrix[0].length : 0;
        int res = 0;
        for (int i = 0; i< w; i++){
            for(int j = 0; j < l; j++){
                if(matrix[i][j] == '1') {
                    boolean check = true;
                    int curl = 1;
                    while(i + curl < w && j + curl < l && check ){
                        for(int a = i; a <= i + curl; a++) {
                            if (matrix[a][j + curl] == '0') {
                                check = false;
                                break;
                            }
                        }
                        for (int a  = j; a <= j + curl; a++){
                            if (matrix[i+curl][a] == '0'){
                                check = false;
                                break;
                            }
                        }
                        if(check) curl++;
                    }
                    if(res < curl) res = curl;
                }
            }
        }
        return res * res;
    }
}