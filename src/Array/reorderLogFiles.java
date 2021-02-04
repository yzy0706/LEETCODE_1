package Array;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class reorderLogFiles {
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
