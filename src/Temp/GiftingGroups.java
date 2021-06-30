package Temp;

import java.util.Arrays;
import java.util.List;

public class GiftingGroups {

    public static int findConnectedGroups(String input) {
        String[] elements = input.split(",");
        List<String> arrayList = Arrays.asList(elements);
        int[][] isConnected = new int[arrayList.size()][arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            String row = arrayList.get(i);
            for (int j = 0; j < row.length(); j++) {
                isConnected[i][j] = Integer.parseInt(Character.toString(row.charAt(j)));
            }
        }
        int count = 0;
        boolean[] isReached = new boolean[isConnected.length];
        for (int i = 0; i < isConnected.length; i++) {
            if (!isReached[i]) {
                alignedGroups(isConnected, isReached, i);
                count++;
            }
        }
        return count;
    }

    private static void alignedGroups(int[][] isConnected, boolean[] isReached, int v) {
        isReached[v] = true;
        for (int i = 0; i < isConnected.length; i++) {
            if (isConnected[v][i] == 1 && !isReached[i]) {
                alignedGroups(isConnected, isReached, i);
            }
        }
    }


    // 做法: discussion给的做法,
    // 1. 用一个boolean[] visited来记录每一个人是不是访问过, 然后forloop i来以每个人为起点, 进行dfs
    // 2. dfs里forloop j, 检查他认识的每一个人(j), 如果(M[i][j] == 1 && !visited[j]), 就dfs(M, visited, j), 检查j认识的所有人
    // Runtime: O(n), Space: O(n)

    int res, n;
    public int findCircleNum_reviewed(int[][] M){
        n = M.length;
        res = 0;
        boolean[] visited = new boolean[n];
        for(int i = 0; i < n; i++){
            if(!visited[i]){
                res ++;
                dfs(M, visited, i);
            }
        }
        return res;
    }

    public void dfs(int[][] M, boolean[] visited, int i){
        visited[i] = true;
        for(int j = 0; j < n; j ++){
            if(M[i][j] == 1 && !visited[j]){
                dfs(M, visited, j);
            }
        }
    }





    // 自己后来做的dfs解法, dfs过程中每次都把每个是1的位置变成0, 而且M里所有跟当前位置i或者j相等, 且值是1的位置进行新的dfs
    // Runtime: O(n^2), Space: O(1)

    public int findCircleNum(int[][] M){
        n = M.length;
        res = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j ++){
                if(M[i][j] == 1){
                    dfs(M, i, j);
                    res ++;
                }
            }
        }
        return res;
    }

    public void dfs(int[][] M, int i, int j){
        if(i < 0 || i >= n || j < 0 || j >= n || M[i][j] == 0) return;
        M[i][j] = 0;
        for(int a = 0; a < n; a ++){
            if(M[i][a] == 1) dfs(M, i, a);
            if(M[a][i] == 1) dfs(M, a, i);
        }
    }
}
