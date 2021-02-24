package Backtrack;

import java.util.HashSet;
import java.util.Set;

public class BeautifulArrangement {

    // 做法: backtrack, 题目问的所有符合条件的可能的个数, 我第一个想到了backtrack
    // 这里我的做法就是用一个hashset记录每一个permutation之前哪些元素被用过了, 基于permutation就是尝试 -> 去recursion -> 删除之前的尝试的原则
    //1. 我们建立一个helper permute(int n, int index, Set<Integer> used), 最开始used里面是空的
    //2. 在每次permutate的方程里, 我们不断尝试从1到n之间没有用过(!used.contains(i))的数, 符合整除的条件的话我们进入下一次recursion, 然后当前used里面删除掉刚刚用过的这个, 重新组合下一个数

    // Runtime: O(n^n), Space: O(n)
    private int res;

    public int countArrangement(int n) {
        res = 0;
        Set<Integer> used = new HashSet<>();
        permute(n, 1, used);
        return res;
    }

    public void permute(int n, int index, Set<Integer> used){
        if(index == n+1){
            res ++;
            return;
        }
        Set<Integer> before = used;
        for(int i = 1; i <= n; i++){
            if(!used.contains(i)){
                if(i % index == 0 || index % i == 0){
                    used.add(i);
                    permute(n, index+1, used);
                    used.remove(i);
                    // System.out.println(used.isEmpty());
                }
            }
        }
    }




    // discussion的做法， 用 used[i] = 1； 来代表用过了， 比 HashSet<>()要省时间一点
    public int countArrangement_array(int n) {
        res = 0;
        permute(n, 1, new int[n+1]);
        return res;
    }

    public void permute(int n, int index, int[] used){
        if(index == n+1){
            res ++;
            return;
        }
        for(int i = 1; i <= n; i++){
            if(used[i] == 0){
                if(i % index == 0 || index % i == 0){
                    used[i] = 1;
                    permute(n, index+1, used);
                    used[i] = 0;
                }
            }
        }
    }
}
