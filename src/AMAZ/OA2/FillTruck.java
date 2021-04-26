package AMAZ.OA2;

import java.util.*;

public class FillTruck {
    //刀说的先sort再写
    long getMaxUnit(int num, ArrayList<Integer> boxes, int unitSize, ArrayList<Integer> unitsPerBox, long truckSize) {
        List<int[]> products = new ArrayList<>();
        for (int i = 0; i < num; i++) products.add(new int[]{boxes.get((i)), unitsPerBox.get(i)});
        products.sort((a, b) -> b[1] - a[1]);
        long res = 0;
        for (int[] p : products) {
            if (truckSize >= p[0]) {
                res += p[0] * p[1];
                truckSize -= p[0];
            } else {
                res += truckSize * p[1];
                break;
            }
        }

        return res;

    }


    public int fillTheTruck(int num, int[] boxes, int unitSize, int[] unitsPerBox, long truckSize) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]); //maxHeap
        for (int i = 0; i < num; i++) {
            pq.offer(new int[]{boxes[i], unitsPerBox[i]});
        }
        int res = 0;
        while (truckSize > 0 && !pq.isEmpty()) {
            int[] cur = pq.poll();
            long carry = Math.min(truckSize, cur[0]); //找当前剩下的boxes 和 truckSize哪个更小
            truckSize -= carry;
            res += carry * cur[1];
        }
        return res;
    }
}





//    //第一遍自己写的， 用pq去排序箱子的名字， 依据是他们的容量， 然后每次根据truckSize我们用掉一个容量最多的箱子的个数（peek再--）， 如果这个箱子只剩下0了我们就从pq里
//    // remove掉他
//    // Runtime: 如果n是truckSize的话就是O(nlog(k))，最坏的情况箱子用不完所以所有的箱子都在pq里， k是箱子的个数， space是O(klog(k))
//    private static int fillTheTruck(int num, int[] boxes, int unitSize, int[] unitsPerBox, int truckSize){
//        Queue<Integer> boxSortByStorage = new PriorityQueue<Integer>((a, b) -> unitsPerBox[b] - unitsPerBox[a]);
//        int res = 0;
//        for(int i = 0; i < num; i++){
//            boxSortByStorage.offer(i);
//        }
//        while(truckSize > 0 && !boxSortByStorage.isEmpty()){
//            int curBox = boxSortByStorage.peek();
//
//            if(boxes[curBox] > 0){
//                boxes[curBox] --;
//                if(boxes[curBox] == 0) boxSortByStorage.remove(curBox);
//                res += unitsPerBox[curBox];
//                truckSize--;
//            }
//            else{
//                continue;
//            }
//        }
//
//        return res;
//    }
//
//
//
//    //lc有个人给的解法， 感觉不是很喜欢
//    long getMaxUnit(int num, ArrayList<Integer> boxes, int unitSize, ArrayList<Integer> unitsPerBox, long truckSize)
//    {
//        long cnt = (long) 0;
//
//        PriorityQueue<Integer> unitsMaxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());
//
//        // for each product
//        for (int i = 0; i < num; i++) {
//            // for each box count
//            for (int j = 0; j < boxes.get(i); j++) {
//                unitsMaxHeap.add(unitsPerBox.get(i));
//            }
//        }
//
//        while (truckSize > 0 && !unitsMaxHeap.isEmpty()) {
//            int units = unitsMaxHeap.poll(); // get max units count
//            cnt += (long) units;
//            truckSize--;
//        }
//
//        return cnt;
//    }

