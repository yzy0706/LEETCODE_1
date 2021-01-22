package Math;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class NPeopleSayNumberM {
    //做法： 这题主要就是拿一个linkedList存0到n-1所有的index， 每一个index都更新为数了m-1次以后（加上自己就是报m次） % 整个list的大小的余数， 那么
    //就把当前这个更新了index对应的数删掉， res了加上index对应的数+1， 最后list.size() = 1的时候res.add(list.get(0) + 1)
    //Runtime: O(n), Space: O(n)
    public static List<Integer> circle(int n, int m) {
        List<Integer> res = new ArrayList<>();
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        int index = 0;
        while (list.size() > 1) {
            index = (index + m - 1) % list.size();
            res.add(list.remove(index) + 1);
        }
        res.add(list.get(0) + 1);
        return res;
    }
}
