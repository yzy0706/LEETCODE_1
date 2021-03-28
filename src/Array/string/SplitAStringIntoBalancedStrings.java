package Array.string;

public class SplitAStringIntoBalancedStrings {
    //做法: 我直接forloop的, 只要l和r的个数相等了就证明碰到一段新的相等个数的字符串了, 然后res++
    //Runtime: O(n), Space: O(1)
    public int balancedStringSplit(String s) {
        if(s.length() < 1) return 0;
        int r = 0, l = 0, res = 0;
        for(Character c : s.toCharArray()){
            if(c == 'L')l++;
            if(c == 'R') r++;
            if(l == r) res++;
        }
        return res;
    }


    public int balancedStringSplit_addminus1(String s) {
        if(s.length() < 1) return 0;
        int bal = 0, res = 0;
        for(Character c : s.toCharArray()){
            bal += c == 'L' ? 1 : -1;
            if(bal == 0) res++;
        }
        return res;
    }
}
