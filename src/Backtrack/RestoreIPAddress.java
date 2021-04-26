package Backtrack;

import java.util.ArrayList;
import java.util.List;

public class RestoreIPAddress {
    // 做法: 这题用的基本的backtrack(dfs)的方法, 只是因为StringBuilder是引用传导比较麻烦, 我后来看答案改成了用string表示当前的string
    // 注意: s.startsWith(String head)可以检查这个s的头是不是什么特定的head
    // Runtime: O(n), Space: O(1);

    List<String> res;
    public List<String> restoreIpAddresses_string(String s) {
        res = new ArrayList<>();
        backtrack(s, "", 0, 0);
        return res;
    }

    private void backtrack(String s, String cur, int pos, int num){
        if(num == 4 && pos == s.length()){
            res.add(cur);
            return;
        }
        if(num >= 4 || pos == s.length()){
            return;
        }
        for(int i = pos + 1; i <= s.length() && i <= pos + 3; i++){
            String ns = s.substring(pos, i);
            if(ns.length() > 1 && ns.startsWith("0") || Integer.parseInt(ns) > 255) continue;
            if(i < s.length()) ns += ".";
            backtrack(s, cur.concat(ns), i, num + 1);
        }
    }


//    List<String> res;
    public List<String> restoreIpAddresses(String s) {
        res = new ArrayList<>();
        backtrack(s, new StringBuilder(), 0, 0);
        return res;
    }

    private void backtrack(String s, StringBuilder sb, int pos, int num){
        if(num == 4 && pos == s.length()){
            // System.out.println(sb.toString());
            sb.deleteCharAt(sb.length() - 1);
            res.add(sb.toString());
            return;
        }
        if(num >= 4 || pos == s.length()){
            return;
        }
        StringBuilder origin = new StringBuilder(sb);
        if(s.charAt(pos) == '0'){
            sb.append("0" + ".");
            backtrack(s, sb, pos + 1, num + 1);
        }
        else{
            for(int i = pos + 1; i <= s.length(); i++){
                String ns = s.substring(pos, i);
                double nDouble = Long.parseLong(ns);
                // int nInt = (int)nDouble;
                if(nDouble > 255) break;
                // System.out.println(nInt);
                sb.append(String.valueOf(nDouble) + ".");
                backtrack(s, new StringBuilder(sb), i, num + 1);
                sb = origin;
            }
        }
    }
}
