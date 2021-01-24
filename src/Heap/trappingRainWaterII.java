package Heap;

public class pair{
    int i , j ,val;
    public pair(int i,int j, int val){
        this.i = i;
        this.j = j;
        this.val = val;
    }
}

class com implements Comparator<pair>{
    public int compare(pair a, pair b){
        return a.val - b.val;
    }
}



    public int trapRainWater(int[][] heightMap) {

        if(heightMap == null||heightMap.length < 1 || heightMap[0].length < 1) return 0;

        int m = heightMap.length, n = heightMap[0].length,res = 0;
        boolean[][] visited = new boolean[m][n];

        PriorityQueue<pair> queue = new PriorityQueue<>(m*n,new com());

        for(int i = 0 ; i < m ; i++){
            visited[i][0] = true;
            visited[i][n-1] = true;
            queue.offer(new pair(i,0,heightMap[i][0]));
            queue.offer(new pair(i,n-1,heightMap[i][n-1]));
        }

        for(int j = 0 ; j < n ; j++){
            visited[0][j] = true;
            visited[m-1][j] = true;
            queue.offer(new pair(0,j,heightMap[0][j]));
            queue.offer(new pair(m-1,j,heightMap[m-1][j]));
        }

        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while(!queue.isEmpty()){

            pair cur = queue.poll();
            for(int[] dir: dirs){
                int i = cur.i + dir[0];
                int j = cur.j + dir[1];
                if(i >= 0 && i < m && j >= 0 && j < n && !visited[i][j]){
                    visited[i][j] = true;
                    res += Math.max(0,cur.val-heightMap[i][j]);
                    queue.offer(new pair(i,j,Math.max(cur.val,heightMap[i][j])));
                }
            }
        }

//     public int find(int[][] heightMap,int m, int n, PriorityQueue<pair> queue,int res,boolean[][] visited){

//         while(!queue.isEmpty()){
//             pair cur = queue.poll();
//             int[][] dirs = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};

//             for(int[] dir: dirs){
//                 int i = cur.i + dir[0];
//                 int j = cur.j + dir[1];
//                 if(i >= 0 && i < m && j >= 0 && j < n && !visited[i][j]){
//                     visited[i][j] = true;
//                     res += Math.max(0,cur.val-heightMap[i][j]);
//                     queue.offer(new pair(i,j,Math.max(cur.val,heightMap[i][j])));
//                 }
//                 // find(i+1,j,heightMap,m,n,queue,res,visited);
//                 // find(i-1,j,heightMap,m,n,queue,res,visited);
//                 // find(i,j+1,heightMap,m,n,queue,res,visited);
//                 // find(i,j-1,heightMap,m,n,queue,res,visited);
//             }
//         }
//         return res;
//     }
        return res;
    }
}
