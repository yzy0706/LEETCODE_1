package HashTable.hashMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SubdomainVisitCount {
    //做法:
    // 1. 先把给的String[]的每一个string用 “ ” 分割开次数和网址
    // 2. 把网址根据‘.’出现的idex慢慢把domain用idex和domain的长度substring成subdomain, 并把他们在map里面的次数根据当前s的次数更新
    // 3. 根据map的key和value拼接string并且加到res里并且return

    //Runtime: O(mn), m是cpdomains里面每一个string里面的网址有几个部分, Space: O(mn)
    public List<String> subdomainVisits(String[] cpdomains) {
        if(cpdomains.length < 1) return new ArrayList<>();
        HashMap<String, Integer> freq = new HashMap<>();

        for(String s : cpdomains){
            String[] twoParts = s.split(" ");
            String timeStr = twoParts[0];
            int times = Integer.parseInt(timeStr);
            String domain = twoParts[1];
            int idex = domain.indexOf(".");
            while(idex != -1){ //把各个部分都加到map里
                freq.putIfAbsent(domain, 0);
                freq.put(domain, freq.get(domain) + times);
                idex = domain.indexOf('.');
                // System.out.println(domain);
                domain = domain.substring(idex + 1, domain.length());
            }
        }

        // System.out.println(freq.get(cpdomains[0]));

        List<String> res = new ArrayList<>();
        for(String address : freq.keySet()){
            StringBuilder cur = new StringBuilder();
            int times = freq.get(address);
            String timeStr = String.valueOf(times);
            cur.append(timeStr);
            cur.append(" ");
            cur.append(address);
            res.add(cur.toString());
        }

        return res;
    }
}
