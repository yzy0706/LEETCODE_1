package Sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class groupAnagrams {
    // xlog(x)*n
    //用map的解法，主要是把每一个string浏览的时候都把他们先变成char[]再sort一下，这样valueOf(cl)以后的key就是一样了
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        if(strs.length < 1) return res;
        Map<String,List<String>> map = new HashMap<>();
        for(String s: strs){
            char[] cl = s.toCharArray();
            Arrays.sort(cl);
            String key = String.valueOf(cl);
            if(!map.containsKey(key)) map.put(key, new ArrayList<>());
            map.get(key).add(s);
    }
        return new ArrayList<>(map.values());
    }
}
