package Heap.Queue.backtrackFindMinSum;

import java.util.PriorityQueue;

public class kSmallestElementInMatrix {
    public int kthSmallest(int[][] matrix, int k) {
        int w = matrix.length, l = matrix[0].length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> matrix[a[0]][a[1]] - matrix[b[0]][b[1]]);
        for(int i = 0; i < w && i < k; i++){
            pq.offer(new int[]{i, 0});
        }
        int[] res = new int[2];
        int cnt = 0;
        while(!pq.isEmpty() && cnt < k){
            int[] cur = pq.poll();
            res = cur;
            if(cur[1] < l - 1) pq.offer(new int[]{cur[0], cur[1]+1});
            cnt++;
        }
        return matrix[res[0]][res[1]];
    }
    }

