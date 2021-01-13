package graph.prim;

public class cheapestFlightWithinKStops {
    //Prim的解法， 主要就是前半部分建立map和狄杰斯特拉一样，而在建立pq时里面每个stop单元int[] curStop应该是三维的
    //curStop[0]应该当前的下一个中转站（邻居），curStop[1]应该是一个一直在叠加的price， 而curStop[2]应该是当前叠加的已经经过的中转站的数量
    //跟dijkstra之类的方法不同的是我们不是在更新start的最短路径， 所以我们不用建立一个dist[]来比较src到各个点的距离，我们也不需要一直用松弛
    //方法去更新src到各个点的距离， 反而我们要做的是去叠加每一个邻居对应的不同的叠加price， 并且检查已经经过的stop有没有>= K+1; 这个一般是单向的
       public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        Map<Integer, List<int[]>> map = new HashMap<>();
        for(int[] flight : flights){
            int str = flight[0];
            int des = flight[1];
            int price = flight[2];
            int[] cur = new int[]{des, price};
            if(!map.containsKey(str)) map.put(str, new ArrayList<int[]>());
            map.get(str).add(cur);
        }

        PriorityQueue<int[]> stops = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        if(!map.containsKey(src)) return -1;
        for(int[] cur : map.get(src)){
            int[] tmp = new int[]{cur[0], cur[1], 0};
            stops.offer(tmp);
        }
        if(stops.isEmpty()) return -1;

        while(!stops.isEmpty()){
            int[] curStop = stops.poll();
            int des = curStop[0], price = curStop[1], numStop = curStop[2];
//            if(numStop >= K+1) continue;
            if(numStop > K) continue;
            if(des == dst) return price;
            for(int[] neighbor : map.get(des)){
                int next = neighbor[0], nextPrice = neighbor[1];
                stops.offer(new int[]{next, price + nextPrice, numStop+1});
            }
        }
        return -1;

    }
}
