package Graph.unionFind.ValidTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IsValidTree {
//    public static void main(String[] args){
//        String s = "(A,B) (A,C) (B,D) (D,C)";
//        System.out.println(isValidTree(s));
//    }


     public static class UNF{
         int[] parents;

         public UNF (){
            this.parents = new int[26];
            for(int i = 0; i < parents.length; i++){
                parents[i] = i;
            }
        }

        public int find(int n){

             while(n != parents[n]){
                 parents[n] = parents[parents[n]];
                 n = parents[n];
             }
             return n;
        }

        public void merge(int a, int b){
             int rootA = find(a);
             int rootB = find(b);
             if (rootA != rootB){
                  parents[rootB] = parents[rootA];
             }
        }

        public boolean isSameTree(int a, int b){
             return find(a) == find(b);
        }

    }


    //(A,B) (A,C) (B,D) (D,C)
    public static String isValidTree(String s){
         Map<Integer, List<Integer>> map = new HashMap<>();
         char[] cl = s.toCharArray();
         UNF unf  = new UNF();
         for(int i = 0; i < cl.length; i++){
             if(cl[i] == ','){
                 int last = cl[i-1] - 'A';
                 int next = cl[i+1] - 'A';
                 if(!map.containsKey(last)) map.put(last, new ArrayList<Integer>());
                 if(map.get(last).contains(next)) return"E2";
                 map.get(last).add(next);
                 unf.merge(last, next);
             }
         }

         int root = -1;
         for(int i : map.keySet()) {
             if (map.get(i).size() > 2) return "E1";
             int curRoot = unf.find(i);
             if (root == -1 && curRoot == i) root = curRoot;
             if (root != -1 && curRoot == i) return "E3";
             if (root != -1 && curRoot != root) return "E4";
         }

         return " ";
         }



    }

