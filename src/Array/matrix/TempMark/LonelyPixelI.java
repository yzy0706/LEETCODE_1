package Array.matrix.TempMark;

public class LonelyPixelI {
    // 做法: 我第一遍自己的做的时候是类似于temp mark的方法, 就是做matrix的题目的时候一种标记状态的方法
    // 1. 建立一个int[] rows 和 int[] cols来记录每一行和每一列的状态
    // 2. forloop matrix上的所有位置,
    //      a. 如果当前row[i]和col[j]都是0 , res ++; row[i]和col[j]都标记为1, 代表这一行和这一列现在只有一个‘B’, 当前再出现一个‘B'对答案是有影响的
    //      b. 如果当前row[i]或者row[j] == 1, 代表当前这个'B'的出现给结果带来了影响, 那么分别对于rows[i] == 1 或者 cols[i] == 1的情况都要扣除一次: res --.
    //      所以就分别顺着当前的row或者col往前, 把前一个出现的B的位置的对应的rows[i]和cols[j]修改成2, 代表这些行或者列再出现‘B'也没事了, 因为这一个‘B'已经不算入结果了

    // Runtime: O(n^3), Space: O(n)
    public int findLonelyPixel_1(char[][] picture) {
        int m = picture.length, n = picture[0].length;
        int[] rows = new int[m];
        int[] cols = new int[n];
        int res = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(picture[i][j] == 'B'){
                    if(rows[i] == 0 && cols[j] == 0){
                        res ++;
                        rows[i] = 1;
                        cols[j] = 1;
                        continue;
                    }
                    if(rows[i] == 1){
                        for(int c = j; c >= 0; c--){
                            if(picture[i][c] == 'B'){
                                cols[c] = 2;
                            }
                        }
                        res --;
                    }
                    if(cols[j] == 1){
                        for(int r = i; r >= 0; r--){
                            if(picture[r][j] == 'B'){
                                rows[r] = 2;
                            }
                        }
                        res --;
                    }
                    rows[i] = 2;
                    cols[j] = 2;
                }
            }
        }
        return res;
    }


    // 做法: 这个是discussion上给的, 单纯的forloop整个matrix两遍
    // 1. forloop第一遍, 记录每一个row和col的'B'的个数
    // 2. forloop第二遍, 每一个是'B'的地方, 而且rows[i]和cols[j]都是1, 那么res++;
    // Runtime: O(mn), Space: O(n+m)

    public int findLonelyPixel_2(char[][] picture) {
        int m = picture.length, n = picture[0].length;
        int[] rows = new int[m];
        int[] cols = new int[n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(picture[i][j] == 'B'){
                    rows[i] ++;
                    cols[j] ++;
                }
            }
        }
        int res = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(picture[i][j] == 'B' && rows[i] == 1 && cols[j] == 1){
                    res ++;
                }
            }
        }
        return res;
    }
}
