package Greedy.Array;


import java.util.HashSet;
import java.util.Set;

public class FindTheCelebrity {
    // 做法: 最简单的做法就是先找出那个大家都认识的人, 然后再看这个人是不是认识别人或者是不是有人不认识他
    // Runtime: O(n^2), Space: O(n)
    public int findCelebrity_array(int n) {
        int cele = 0;
        for(int i = 1; i < n; i++){
            if(knows(cele, i)) cele = i;
        }
        for(int i = 0; i < n; i++){
            if((i != cele && knows(cele, i)) || !knows(i, cele)) return -1;
        }
        return cele;
    }




    // 做法: 自己写的hashset解法, 就是先找出0认识的所有人放在hashset里, 再一步步根据所有人不认识的人或者什么人认识其他人去去除hashset里面潜在的名人,  有点复杂
    // Runtime: O(n^2), Space: O(n)
    public int findCelebrity_hashset(int n) {
        Set<Integer> celes = new HashSet<>();
        for(int i = 0; i < n; i++){
            if(knows(0, i)) celes.add(i);
        }
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(!knows(i, j)){
                    if(celes.contains(j)) celes.remove(j);
                }
                if(i != j && knows(i, j)){
                    if(celes.contains(i)) celes.remove(i);
                }
            }
        }
        if(celes.size() == 0 || celes.size() > 1) return -1;
        return celes.iterator().next();
    }






    public int findCelebrity(int n) {
        UNF unf = new UNF(n);
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(i != j && knows(i, j)) unf.merge(i, j);
            }
        }
        int cele = -1;
        for(int i = 0; i < n; i++){
            if(cele == -1 && unf.parents[i] == i) cele = i;
        }
        return cele;
    }

    class UNF{
        int[] parents;

        public UNF(int n){
            this.parents = new int[n];
            for(int i = 0; i < n; i++){
                parents[i] = i;
            }
        }

        public int find(int a){
            while(a != parents[a]){
                parents[a] = parents[parents[a]];
                a = parents[a];
            }
            return a;
        }

        public void merge(int a, int b){

        }
    }

    public boolean knows(int a, int b){
        return false;
    }
}
