package heap;

import java.util.Comparator;
import java.util.PriorityQueue;


public class JZ_KthSmallestMatrix {
    class pair{
        int x ,y , val;

        public pair (int x, int y , int val){
            this.x = x ;
            this.y = y;
            this.val = val;
        }

    }

    class com implements Comparator<heap.pair> {
        public int compare(heap.pair a, heap.pair b){
            return a.val - b.val;
        }
    }


    public int kthSmallest(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[] dx = new int[]{0, 1};
        int[] dy = new int[]{1, 0};
        boolean[][] check = new boolean[m][n];
        PriorityQueue<pair> minHeap = new PriorityQueue<pair>(k, new com());
        minHeap.offer(new pair(0,0,matrix[0][0]));

        for(int i = 0 ; i < k - 1; i ++){
            pair cur = minHeap.poll();
            for(int j = 0 ; j < 2 ; j ++){
                int next_x = cur.x + dx[j];
                int next_y = cur.y + dy[j];
                pair next_pair = new pair(next_x,next_y,0);
                if(next_x < m && next_y < n && !check[next_x][next_y]){
                    check[next_x][next_y] = true;
                    next_pair.val = matrix[next_x][next_y];
                    minHeap.offer(next_pair);
                }
            }
        }

        return minHeap.peek().val;
    }




    public class Solution {
        /**
         * @param matrix: a matrix of integers
         * @param k: An integer
         * @return: the kth smallest number in the matrix
         */

        class pair{
            public int x , y , val ;
            public pair (int x , int y, int val){
                this.x = x;
                this.y = y;
                this.val = val;
            }
        }

        class com implements Comparator<pair>{
            public int compare(pair a, pair b){
                return a.val - b.val;
            }
        }




        public int kthSmallest(int[][] matrix, int k) {
            int[] dx = new int[] {0,1};
            int[] dy = new int[] {1,0}; //先查右边那个再查下面那个
            int m = matrix.length;
            int n = matrix[0].length;
            boolean[][] check = new boolean[m][n];

            // Comparator<pair> com = (p1,p2) -> {
            //     return (p1.val).compareTo(p2.val);
            // };

            PriorityQueue<pair> minHeap = new PriorityQueue<pair>(k , new com());
            minHeap.offer(new pair(0,0,matrix[0][0]));

            for(int i = 0 ; i < k - 1 ; i ++ ){
                pair cur = minHeap.poll();
                for(int j = 0 ; j < 2 ; j++){
                    int next_x = cur.x + dx[j];
                    int next_y = cur.y + dy[j];
                    pair next_pair = new pair(next_x,next_y,0);
                    if(next_x < m && next_y < n && !check[next_x][next_y]){
                        check[next_x][next_y] = true;
                        next_pair.val = matrix[next_x][next_y];
                        minHeap.offer(next_pair);
                    }
                }
            }
            return minHeap.peek().val;
        }
    }
}

////第一遍解法

