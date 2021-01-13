package 数据结构.树和图.dataStructure;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class Dijkstra {
    public static void main(String[] args) {
        int MAX = Integer.MAX_VALUE;    // 无法到达时距离设为 Integer.MAX_VALUE
        int[][] weight={
                {0,1,12,MAX,MAX,MAX},
                {MAX,0,9,3,MAX,MAX},
                {MAX,MAX,0,MAX,5,MAX},
                {MAX,MAX,4,0,13,15},
                {MAX,MAX,MAX,MAX,0,4},
                {MAX,MAX,MAX,MAX,MAX,0}
        };
        int start = 0;  // 选择出发点
        System.out.println(Arrays.toString(solution(weight,start)));
    }

    private static int[] solution(int[][] weight, int start){
        int l = weight[0].length;
        boolean[] visited = new boolean[l];
        int[] res = new int[l];

        for(int i = 0; i < l; i++) {
            res[i] = weight[start][i];
        }



        int midStop = 0;
        int minDist = Integer.MAX_VALUE;


        for(int j = 0; j < res.length; j++){
                if(j != start && !visited[j] && res[j] < minDist){
                    minDist = res[j];
                    midStop = j;
                }
            }


        visited[midStop] = true;

        for(int i = 0; i < l; i++){

            if(!visited[i] && res[i] > res[midStop] + weight[midStop][i]){
                res[i] = res[midStop] + weight[midStop][i];
            }
        }
        return res;
    }


    //假设说把单单更新res[1]改成修改所有起点的weight，并用queue的话
    private static int[] solution_bfs(int[][] weight, int start) {
        int l = weight[0].length;
        boolean[] visited = new boolean[l];
        Queue<Integer> curStops = new PriorityQueue<>();
        curStops.offer(start);
        int[] distances = new int[l];
        //先把curStart到所有点的距离记录到distances里面

        for(int i = 0; i < l; i++){
            distances[i] = weight[start][i];
        }


        while (!curStops.isEmpty()){
            int curStart = curStops.poll();

            //找到当前最近的一个点的距离

            int minDistance = Integer.MAX_VALUE;
            int midStop = 0;
            for(int i = 1; i < l; i++){
                if(i != start && !visited[i] && distances[i] < minDistance ){
                    minDistance = distances[i];
                    midStop = i;
                }
    }

    //假设start到这个点的距离大于经过了中间点再到这个点的距离，则更新start到这个点的距离
            for(int i = 0; i < l; i++){
        if(!visited[i] && (distances[midStop] + weight[midStop][i]) < distances[i]){
            distances[i] = distances[midStop] + weight[midStop][i];
            curStops.offer(i);
        }
    }

}

        return distances;
    }






}
