package Backtrack;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MaxLenOfConcatenatedStringWithUniqueChar {
    // 做法: 用的基础的backtack去让每一个string和所有接下来的string组合
    // 主要是要注意这个引用传导在backtrack使用的时候一定要注意新建一个set或者去删除, 因为这里不好删除所以我用的是一个temp来记录进入forloop之前set的值
    // Runtime: O(n*n!), Space: O(n)
    int res = 0;
    public int maxLength(List<String> arr) {
        if(arr.size() == 1) return arr.get(0).length();
        backtrack(arr, 0, new HashSet<>());
        return res;
    }

    private void backtrack(List<String> arr, int i, Set<Character> set){
        if(i == arr.size()){
            res = Math.max(res, set.size());
            return;
        }
        Set<Character> temp = new HashSet<>(set);
        for(int j = i; j < arr.size(); j++){
            String next = arr.get(j);
            boolean unique = true;
            for(Character c : next.toCharArray()){
                if(!set.add(c)) unique = false;
            }
            if(unique) backtrack(arr, j + 1, new HashSet<>(set));
            else backtrack(arr, j + 1, new HashSet<>(temp));
            set = new HashSet<>(temp);
        }
    }
}
