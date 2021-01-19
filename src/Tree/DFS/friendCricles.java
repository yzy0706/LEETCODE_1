package Tree.DFS;

public class friendCricles {
    //solution里面的DFS, 就是建立一个int[M.length] visited来记录哪一行浏览过， 然后顺着i浏览每一行，如果这行没有被浏览过
    //则进入以这行行数为i值的dfs并forloop浏览每一列的列数j值，如果跟j值一样的i行没有被浏览过，则visited[j] = 1（也就是第j行）
    // 也要被浏览一遍，然后再以这个j值为根据进入下一个recursion

    public int findCircleNum_dfs(int[][] M){
        int w = M.length;
        if(w < 1) return 0;
        int res = 0;
        int[] visited = new int[w];

        for(int i = 0; i < w; i++){
            if(visited[i] == 0){
                dfs(M, visited, i);
                res++;
            }
        }

        return res;
    }

    public void dfs(int[][] M, int[] visited, int i){
        for(int j = 0; j < M.length; j++){
            if(visited[j] == 0 && M[i][j] == 1){
                visited[j] = 1;
                dfs(M, visited, j);
            }
        }
    }
    //第一遍用map和dfs写的，有点太复杂了
    public int findCircleNum(int[][] M) {
        int w = M.length;
        if(w < 1) return 0;
        int l = M[0].length;
        Map<int[], List<int[]>> map = new HashMap<>();

        for(int i = 0; i < w; i++){
            for(int j = 0; j < l; j++){
                if(M[i][j] == 1){
                    int[] friend = new int[2]{j, i};
                    int[] cur = new int[2]{i, j};
                    if(!map.containsKey(friend)){
                        map.put(cur, new int[2]);
                    }
                    else{
                        map.replace(friend, map.get(friend), cur);
                        expand(M, i, j, map, cur);
                    }
                }

            }
        }
    }

    public void expand(int[][] M, int i, int j, Map<int[], int[]> map, int[] cur){
        if(M[i][j] == 0) return;
        if(M[i][j] == )
    }
}
