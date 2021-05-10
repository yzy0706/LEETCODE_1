package Array.string;

import java.util.ArrayList;
import java.util.List;

public class GroupShiftedStrings {
    // 做法:
    // 1. 用String里面每两个char相减来记录他们的char之间的差(如果差 < 0就 += 26)
    // 2. 用double forloop去一一比对i位置的String上各个char的差和j位置String上各个char的差, 如果差是一样的证明是可以互相转换的, 放到一个list里面并且 visited[i] = true, visited[j] = true;

    // Runtime: O(n^2), Space: O(n);

    public List<List<String>> groupStrings(String[] strings) {
        List<List<String>> res = new ArrayList<>();
        int len = strings.length;
        if(len < 1) return res;
        boolean[] visited = new boolean[len];
        // System.out.println('z' - 'a');
        for(int i = 0; i < len; i++){
            if(visited[i]) continue;
            visited[i] = true;
            String cur = strings[i];
            int curLen = cur.length();
            StringBuilder sb = new StringBuilder();
            if(curLen != 1){
                for(int a = 1; a < curLen; a ++){
                    int curDiff = cur.charAt(a) - cur.charAt(a-1);
                    if(curDiff < 0) curDiff += 26;
                    sb.append(curDiff);
                }
            }
            List<String> temp = new ArrayList<>();
            temp.add(cur);
            for(int j = i + 1; j < len; j++){
                if(visited[j]) continue;
                String jString = strings[j];
                int jLen = jString.length();
                if(curLen == 1){
                    if(jLen == 1){
                        temp.add(jString);
                        visited[j] = true;
                    }
                    continue;
                }
                if(jLen == curLen){
                    StringBuilder sbJ = new StringBuilder();
                    for(int a = 1; a < jLen; a ++){
                        int curDiff = jString.charAt(a) - jString.charAt(a - 1);
                        if(curDiff < 0) curDiff += 26;
                        sbJ.append(curDiff);
                    }
                    if(sbJ.toString().equals(sb.toString())){
                        temp.add(jString);
                        visited[j] = true;
                    }
                }
            }
            res.add(temp);
        }
        return res;
    }
}
