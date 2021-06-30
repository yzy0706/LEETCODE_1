package Heap.queue.contructNewDataStructure;

import java.util.PriorityQueue;

public class KSmallestElementInMatrix {
    // 做法: 这题相当于拿pq储存各个位置的坐标, 然后一直把pq poll() k次, 每次都把当前坐标的右边一位放到pq里
    // Runtime: O(k), Space: O(k)
    public int kthSmallest_reviewed(int[][] matrix, int k) {
        int m = matrix.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (matrix[a[0]][a[1]] - matrix[b[0]][b[1]]));
        for(int i = 0; i < m; i++) pq.offer(new int[]{i, 0});
        int cnt = 0;
        int[] res = new int[2];
        while(!pq.isEmpty() && cnt < k){
            int[] cur = pq.poll();
            res = cur;
            if(cur[1] < m - 1) pq.offer(new int[]{cur[0], cur[1] + 1});
            cnt ++;
        }
        return matrix[res[0]][res[1]];
    }



    public int kthSmallest(int[][] matrix, int k) {
        int w = matrix.length, l = matrix[0].length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> matrix[a[0]][a[1]] - matrix[b[0]][b[1]]);
        for (int i = 0; i < w && i < k; i++) {
            pq.offer(new int[]{i, 0});
        }
        int[] res = new int[2];
        int cnt = 0;
        while (!pq.isEmpty() && cnt < k) {
            int[] cur = pq.poll();
            res = cur;
            if (cur[1] < l - 1) pq.offer(new int[]{cur[0], cur[1] + 1});
            cnt++;
        }
        return matrix[res[0]][res[1]];
    }

}

