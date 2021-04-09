package DynamicProgramming.accumulate;

public class FillingBookcaseShelves {
    // 做法: 中心就是用到了一个一维的dp, 用一个双重的forloop去每次更新dp[i] = j == 0 ? Math.min(dp[i], curHeight) : Math.min(dp[i], dp[j-1] + curHeight);
    // 1. 建立一个int[] dp = new int[len-1], 储存到i位置为止最小的高度的和, dp[0]设置为0位置的高度
    // 2. 用i forloop从1到最后一位的所有数, 然后用一个j往前forloop, 记录当前这个分段的height和width, 如果width超过了shelf_width就直接停止;
    // a. 不然的话就根据dp[j-1]的累计高度 + curHeight来更新dp[i];
    // b. 如果j到了0, 那么也不会有dp[j-1], 当前的curHeight就是代表着一层已经包括了从i位置往前的所有的书, 那么积极直接比较curHeight和dp[i]
    //  Runtime: O(n^2), Space: O(n)

    public int minHeightShelves(int[][] books, int shelf_width) {
        int len = books.length;
        int[] dp = new int[len];
        dp[0] = books[0][1];
        for(int i = 1; i < len; i++){
            int curHeight = books[i][1], curWidth = books[i][0];
            dp[i] = dp[i-1] + curHeight;
            for(int j = i - 1; j >= 0; j--){
                curWidth += books[j][0];
                if(curWidth > shelf_width) break;
                curHeight = Math.max(curHeight, books[j][1]);
                dp[i] = j == 0 ? Math.min(dp[i], curHeight) : Math.min(dp[i], dp[j-1] + curHeight);
            }
        }
        return dp[len-1];
    }
}
