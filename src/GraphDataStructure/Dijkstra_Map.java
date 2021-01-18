package GraphDataStructure;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Dijkstra_Map {
    public static void main (String[] args){
        int MAX = Integer.MAX_VALUE;
        HashMap<Integer, List<int[]>> map = new HashMap<>();\
        List<int[]> cur0 = new ArrayList<>();
        cur0.add(new int[]{1, 1});
        cur0.add(new int[]{2, 12});
        map.put(0, cur0);

        List<int[]> cur1 = new ArrayList<>();
        cur1.add(new int[]{1, 0});
        cur1.add(new int[]{2, 9});
        cur1.add(new int[]{3, 3});
        map.put(1, cur1);


        List<int[]> cur2 = new ArrayList<>();
        cur2.add(new int[]{2, 0});
        cur2.add(new int[]{4, 5});
        map.put(2, cur2);


        List<int[]> cur3 = new ArrayList<>();
        cur3.add(new int[]{2, 4});
        cur3.add(new int[]{3, 0});
        cur3.add(new int[]{4, 13});
        cur3.add(new int[]{5, 15});
        map.put(3, cur3);


        List<int[]> cur4 = new ArrayList<>();
        cur4.add(new int[]{4, 0});
        cur4.add(new int[]{5, 4});
        map.put(4, cur4);


        List<int[]> cur5 = new ArrayList<>();
        cur5.add(new int[]{5, 0});
        map.put(5, cur5);

        int start = 0;
        System.out.println();
    }


    public static int solution(Map<Integer, List<int[]>> map, int start){
        List<int[]> distances = map.get(start);
        boolean[] visited = new boolean[map.get(start).size()];

        PriorityQueue<Integer> curStops = new PriorityQueue<Integer>(Comparator.comparing(i -> distances.get(i)[1]));
        curStops.offer(0);

        while (!curStops.isEmpty()){

            int curStart = curStops.poll();

            int curStop = 0;
            int minDistance = Integer.MAX_VALUE;



            //找目前最短的stop
            for(int i = curStart; i < distances.size(); i++){
                if(i != curStart && !visited[i] && distances.get(i)[1] < minDistance) {
                    minDistance = distances.get(i)[1];
                    curStop = distances.get()
                }
            }




        }




    }
}
