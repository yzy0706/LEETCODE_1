package Temp;

import java.util.LinkedList;
import java.util.List;

public class LRUCacheMisses {
    // 直接用LinkedList来做
    // Runtime: O(n), Space: O(k)

    public static int lruCacheMisses(int num, List<Integer> pages, int maxCacheSize) {
        LinkedList<Integer> l = new LinkedList<>();
        int res = 0;
        for(int i  = 0; i < num; i++){
            int cur = pages.get(i);
            if(!l.contains(cur)) res ++;
            if(l.size() == maxCacheSize) l.pollFirst();
            l.add(cur);
        }
        return res;
    }
}
