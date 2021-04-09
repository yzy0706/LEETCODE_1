package Sort.sortThenCompareString;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ReorderLogFiles {
    // 做法: 基本上就是一个sort string的题, 唯一要注意的是 s.split(" ", 2); split的个数是可以确定的.
    // Runtime: O(n), Space: O(n);

    public String[] reorderLogFiles_Reviewed(String[] logs){
        String[] res = new String[logs.length];
        List<String> digits = new ArrayList<>();
        List<String> letters = new ArrayList<>();
        for(String s : logs){
            int space1 = s.indexOf(" ");
            if(s.charAt(space1 + 1) >= '0' && s.charAt(space1 + 1) <= '9'){
                digits.add(s);
            }
            else{
                letters.add(s);
            }
        }
        letters.sort((a, b) -> {
            String[] split1 = a.split(" ", 2);
            String[] split2 = b.split(" ", 2);
            if(split1[1].compareTo(split2[1]) == 0) return split1[0].compareTo(split2[0]);
            return split1[1].compareTo(split2[1]);
        });
        int cnt = 0;
        for(String s : letters){
            res[cnt++] = s;
        }
        for(String s:  digits){
            res[cnt++] = s;
        }
        return res;
    }




    public String[] reorderLogFiles(String[] logs){
        if(logs == null || logs.length < 1) return logs;
        List<String> letters = new ArrayList<>();
        List<String> digits = new ArrayList<>();
        String[] res = new String[logs.length];

        for(String s : logs){
            int ind = s.indexOf(" ");
            char c = s.charAt(ind + 1);
            if(c >= '0' && c <= '9'){
                digits.add(s);
            }
            else letters.add(s);
        }

        Comparator <String> com = (o1 , o2) -> {
            String[] split1 = o1.split(" ", 2);
            String[] split2 = o2.split(" ", 2);

            if(split1[1].compareTo(split2[1]) == 0){
                return split1[0].compareTo(split2[0]);
            }
            return split1[1].compareTo(split2[1]);

        };

        letters.sort(com);
        letters.addAll(digits);
        for(int i = 0 ; i < letters.size() ; i ++){
            res[i] = letters.get(i);
        }

        return res;

    }
}
