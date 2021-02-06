package Greedy;

import java.util.ArrayList;
import java.util.List;

public class partitionLabels {
    public List<Integer> partitionLabels(String S) {
        List<Integer> res = new ArrayList<>();
        if (S.length() < 1) return res;
        int[] l = new int[26];
        char[] cl = S.toCharArray();

        for (int i = 0; i < cl.length; i++) {
            l[cl[i] - 'a'] = i;
        }

        int curMaxLast = 0, anchor = 0;

        for (int i = 0; i < cl.length; i++) {
            curMaxLast = Math.max(curMaxLast, l[cl[i] - 'a']);
            if (curMaxLast == i) {
                res.add(curMaxLast - anchor + 1);
                anchor = i + 1;
            }
        }

        return res;
    }

}
