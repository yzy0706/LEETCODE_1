package Array.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class SearchSuggestionSystem {
    // 做法: 第一遍我是用的sort + HashMap<String, List<String>>, 因为想节省一点时间
            // 1. 先sort给的products里面的所有String, 建立一个cnt代表已经找到了3个product的list有多少个
            // 2. foreach loop整个products, 现在浏览的顺序是lexicographically
            //     a. 如果cnt == searchWord.length(), 所有的list都已经找齐了, 直接break;
            //     b. 先把searchWord从后往前删除char, 一直到当前s.indexOf(pre.toString()) == 0;
            //     c. 现在的pre就是当前符合条件的s的prefix, 一步步往前删除最后一个char, 找到map里对应的list, 如果list的长度小于3, 且加上当前的s长度 == 3, 那么cnt ++;
            // 3. 根据从前往后searchWord的长度, 把对应的list加到res里, 如果list长度为0加入new ArrayList<>();
    // Runtime: O(nlog(n)), Space: O(n)

    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        HashMap<String, List<String>> map = new HashMap<>();
        Arrays.sort(products);
        int len = searchWord.length(), cnt = 0;
        for(String s : products){
            if(cnt == len) break;
            StringBuilder pre = new StringBuilder(searchWord);
            while(s.indexOf(pre.toString()) != 0){
                pre.deleteCharAt(pre.length() - 1);
            }
            while(pre.length() > 0){
                map.putIfAbsent(pre.toString(), new ArrayList<>());
                List<String> list = map.get(pre.toString());
                if(list.size() < 3){
                    list.add(s);
                    if(list.size() == 3){
                        cnt ++;
                    }
                }
                pre.deleteCharAt(pre.length() - 1);
            }
        }
        List<List<String>> res = new ArrayList<>();
        for(int i = 1; i <= searchWord.length(); i++){
            String pre = searchWord.substring(0, i);
            List<String> l = map.getOrDefault(pre, new ArrayList<>());
            res.add(l);
        }
        return res;
    }
}
