package Graph.BFS.topologicalSort;

import java.util.*;

public class isValidTree {
//    public class isValidTree {
//        public static void main(String[] args){
//            String s = "(A,B) (A,C) (B,D) (E,F) (F,G) (F,H)";
//            System.out.println(isValidTree(s));
//        }

        public static String isValidTree(String s){
            char[] cl = s.toCharArray();
            if(cl.length < 1) return "E5";
            int[] indegree = new int[26];
            Map<Integer, List<Integer>> map = new HashMap<>();
            int totalNode = 0;
            Set<Integer> checkedNodes = new HashSet<>();
            for(int i = 0; i < cl.length; i++){
                if(cl[i] == ','){
                    if(i-1 < 1) return "E5";
                    if(i+1 >= cl.length) return "E5";
                    int prevID = cl[i-1] - 'A';
                    int nextID = cl[i+!] - 'A';
                    if(checkedNodes.add(prevID)) {
                        totalNode++;
                    }
                    if(!checkedNodes.add(nextID)) {
                        totalNode++;
                    }
                    if(!map.containsKey(prevID)) map.put(prevID, new ArrayList<>());
                    if(map.get(prevID).contains(nextID)) return "E2";
                    if(map.get(prevID).size() == 2) return "E1";
                    map.get(prevID).add(nextID);
                    indegree[nextID]++;
                }
            }
            Queue<Integer> bfs = new LinkedList<>();
            for(int i : map.keySet()){
                if(indegree[i] == 0) bfs.offer(i);
            }
            if(bfs.size() > 1) return "E4";

            int nodeCheck = 1;

            while(!bfs.isEmpty()){
                int cur = bfs.poll();
//                if(!map.containsKey(cur)) return "E4";
                List<Integer> sons = map.get(cur);
                for(int i : sons){
                    indegree[i]--;
                    if(indegree[i] == 0){
                        bfs.offer(i);
                        nodeCheck++;
                    }
                }
            }

            if(nodeCheck == totalNode) return " ";


            return "E3";


        }
    }

}
