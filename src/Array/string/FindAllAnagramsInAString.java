package Array.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindAllAnagramsInAString {
    //做法: 这个做法我用的是sliding window
    // 1. 用char[] pl来记录被sort好的p的字符串, 用两个pointer: l = 0, r = p.length来初始sliding window的位置
    // 2. 用一个whileloop移动l和r, 用char[] sl来记录当前被sort好的滑动窗口里的字符串
    //      a.如果当前Arrays.equals(pl, sl), 那么把l加入到res里, 而且用一个while(r < sLen && s.charAt(l) == s.charAt(r))来移动滑动窗口, 因为只要当前pl和sl一样, 而且l位置(开头删除掉的)和r位置(尾巴后一个新加入的, substring要头不要尾)的字符一样, 那么随着窗口的移动, 窗口中间的字符们的集合是不变的
    //      b.结束了whileloop证明当前l和r的字符不一样, 那么当前l到r的substring肯定不和p相等, 那么l++, r++.


    //注： 还有一个人用的sliding window + map记录不同string的频率， 也是可以的
    //Runtime: O(mlog(n)), Space(O(n)), m是s的长度, n是p的长度

    public List<Integer> findAnagrams(String s, String p) {
        int sLen = s.length(), pLen = p.length();
        List<Integer> res = new ArrayList<>();
        if(sLen < pLen) return res;
        char[] pl = p.toCharArray();
        Arrays.sort(pl);
        int l = 0, r = pLen;
        while(r <= sLen){
            String cur = s.substring(l, r);
            char[] sl = cur.toCharArray();
            Arrays.sort(sl);
            if(Arrays.equals(sl, pl)){
                res.add(l);
                while(r < sLen && s.charAt(l) == s.charAt(r)){
                    r++;
                    l++;
                    res.add(l);
                }
            }
            l++;
            r++;
        }
        return res;
    }
}
