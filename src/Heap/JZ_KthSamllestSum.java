package Heap;

import java.util.Comparator;
import java.util.PriorityQueue;

public class JZ_KthSamllestSum {
    class pair{
        int a,b,val;
        int[] A,B;
        public pair(int a, int b, int val, int[] A, int[] B){
            this.a = a;
            this.b = b;
            this.A = A;
            this.B = B;
            this.val = val; //这里不能盲目的写成 this.val =  A[a] +B[b]; 因为还没有判断， 否则会有runtime error（out of bound）


        }

    }

    class com implements Comparator<pair> {

        public int compare(pair p1 , pair p2){
            return p1.val - p2.val;
        }
    }


    public int kthSmallestSum(int[] A, int[] B, int k) {
        int[] da = new int[]{0,1};
        int[] db = new int[]{1,0};
        int m = A.length;
        int n = B.length;
        boolean[][] check = new boolean[m][n];
        PriorityQueue<pair> minHeap = new PriorityQueue<pair>(k,new com());
        minHeap.offer(new pair(0,0,A[0]+B[0],A,B));

        for(int i = 0 ; i < k-1 ; i++){
            pair cur = minHeap.poll();

            for(int j = 0 ; j < 2 ; j++){
                int next_a = cur.a + da[j];
                int next_b = cur.b + db[j];
                pair next_pair = new pair(next_a, next_b , 0 , A, B);
                if(next_a < m && next_b < n && !check[next_a][next_b]){
                    check[next_a][next_b] = true;
                    next_pair.val = A[next_a] + B[next_b];
                    minHeap.offer(next_pair);
                }
            }
        }

        return minHeap.peek().val;
    }
}
