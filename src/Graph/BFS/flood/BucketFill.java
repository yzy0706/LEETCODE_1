package Graph.BFS.flood;

public class BucketFill {
    public int bucketFill(String[] picture) {
//        int w = picture.length;
//        if (w < 1) return 0;
//        int l = picture[0].length();
//        char[][] grid = new char[w][l];
//        boolean[][] visited = new boolean[w][l];
//        int res = 0;
//        for (int i = 0; i < w; i++) {
//            String s = picture[i];
//            char[] cl = s.toCharArray();
//            for (int j = 0; j < l; j++) {
//                grid[i][j] = cl[j];
//            }
//        }
//
//        //bfs
//        for (int i = 0; i < w; i++) {
//            for (int j = 0; j < l; j++) {
//                if (!visited[i][j]) {
//                    bfs(i, j, visited, grid);
//                    res++;
//                }
//            }
//        }
//
//        return res;

//    private void expand(int i, int j, char cur, char[][] grid, boolean[][] visited){
//        if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != cur || visited[i][j] ) return;
//        visited[i][j] = true;
//        expand(i+1, j, cur, grid, visited);
//        expand(i-1, j, cur, grid,  visited);
//        expand(i, j+1, cur, grid, visited);
//        expand(i, j-1, cur, grid, visited);
//    }
//
//
//    private void bfs(int i, int j,  boolean[][] visited,  char[][] grid){
//        Queue<int[]> queue = new LinkedList<>();
//        queue.offer(new int[]{i, j});
//        visited[i][j] = true;
//        while(!queue.isEmpty()){
//            int[] cur = queue.poll();
//            int a = cur[0], b = cur[1];
//            int[] vert = new int[]{0, 1, 0, -1};
//            int[] hori = new int[]{1, 0, -1, 0};
//            for(int l = 0; l < 4; l++){
//                int next_a = a + hori[l];
//                int next_b = b + vert[l];
//                if(next_a >= 0 && next_a < grid.length && next_b >= 0 && next_b < grid[0].length && !visited[next_a][next_b] && grid[next_a][next_b] == grid[a][b]) {
//                    queue.offer(new int[]{next_a, next_b});
//                    visited[next_a][next_b] = true;
//                }
//            }
//
//        }
//
//
//    //bfs helper
//
////    private void bfs(int i, int j,  boolean[][] visited,  char[][] grid){
////        Queue<int[]> queue = new LinkedList<>();
////        queue.offer(new int[]{i, j});
////        visited[i][j] = true;
////        while(!queue.isEmpty()){
////            int[] cur = queue.poll();
////            int a = cur[0], b = cur[1];
////            int[] vert = new int[]{0, 1, 0, -1};
////            int[] hori = new int[]{1, 0, -1, 0};
////            for(int l = 0; l < 4; l++){
////                int next_a = a + hori[l];
////                int next_b = b + vert[l];
////                if(next_a >= 0 && next_a < grid.length && next_b >= 0 && next_b < grid[0].length && !visited[next_a][next_b] && grid[next_a][next_b] == grid[a][b]) {
////                    queue.offer(new int[]{next_a, next_b});
////                    visited[next_a][next_b] = true;
////                }
////            }
////
////        }

return -1;
}
}
