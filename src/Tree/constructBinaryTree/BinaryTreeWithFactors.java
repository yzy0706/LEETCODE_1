package Tree.constructBinaryTree;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class BinaryTreeWithFactors {
    // 做法: 用的hashmap做的, 类似于dp的做法, 叠加之前算出来的以每个数为root能组成的bst的个数
    // 1. sort整个arr
    // 2. forloop所有的Integer, cnt = (long)1 每个forloop里再往前map里所有已经浏览的数, if(cur % j == 0 && map.containsKey(cur / j)) cnt += map.get(j) * map.get(cur / j);

    // Runtime: O(n^2), Space: O(n)
    public int numFactoredBinaryTrees_hashmap(int[] arr) {
        int len = arr.length;
        if(len <= 1) return len;
        long res = (long)0;
        Arrays.sort(arr);
        HashMap<Integer, Long> map = new HashMap<>();
        for(int i = 0; i < len; i ++){
            int cur = arr[i];
            Long cnt = (long)1;
            for(int j : map.keySet())
                if(cur % j == 0 && map.containsKey(cur / j))
                    cnt += map.get(j) * map.get(cur / j);
            res += cnt;
            map.put(cur, cnt);
        }
        return (int) (res % (1e9 + 7));
    }


    // lee的解法， 跟第一个差不多， 只是forloop的不是map的keyset()而是往前forloop
    public int numFactoredBinaryTrees_Lee(int[] A) {
        long res = 0L, mod = (long)1e9 + 7;
        Arrays.sort(A);
        HashMap<Integer, Long> dp = new HashMap<>();
        for (int i = 0; i < A.length; ++i) {
            dp.put(A[i], 1L);
            for (int j = 0; j < i; ++j)
                if (A[i] % A[j] == 0)
                    dp.replace(A[i], (dp.get(A[i]) + dp.get(A[j]) * dp.getOrDefault(A[i] / A[j], 0L)) % mod);
            res = (res + dp.get(A[i])) % mod;
        }
        return (int) res;
    }



    //第一遍写的
    public int numFactoredBinaryTrees_1(int[] arr) {
        int len = arr.length;
        if(len <= 1) return len;
        int res = len;
        Arrays.sort(arr);
        HashSet<Integer> set = new HashSet<>();
        for(int i = len - 1; i >= 0; i --){
            int cur = arr[i];
            if(set.contains(cur * cur)) res ++;
            for(int j = i + 1; j < len; j ++){
                if(set.contains(cur * arr[j])) res += 2;
            }
            set.add(cur);
        }
        return res;
    }
}
