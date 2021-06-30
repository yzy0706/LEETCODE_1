package Backtrack;

import java.util.ArrayList;
import java.util.List;

public class LetterCombinationsOfAPhoneNumber {
    // 做法: 用的简单的backtrack, 而且因为这里是根据digits里的每一个数字对应的按键上的string来进行forloop, 而不是一般的在同一个list上一直组合, 所以我们不需要在helper里面考虑当前的位置i和forloop的关系, 反正每一个string都要从头forloop

    // Runtime: O(nk), Space: O(n), n是digits的长度, k是最长的string的长度

    List<String> res;
    int len;
    public List<String> letterCombinations(String digits) {
        String[] strings = new String[10];
        strings[2] = "abc";
        strings[3] = "def";
        strings[4] = "ghi";
        strings[5] = "jkl";
        strings[6] = "mno";
        strings[7] = "pqrs";
        strings[8] = "tuv";
        strings[9] = "wxyz";
        len = digits.length();
        if(len == 0) return new ArrayList<>();
        res = new ArrayList<>();
        permutation(digits, strings, 0, new StringBuilder());
        return res;
    }

    public void permutation(String digits, String[] strings, int i, StringBuilder cur){
        if(cur.length() == len){
            res.add(cur.toString());
            return;
        }
        int num = Character.getNumericValue(digits.charAt(i));
        String button = strings[num];
        for(int j = 0; j < button.length(); j++){
            cur.append(button.charAt(j));
            permutation(digits, strings, i + 1, cur);
            cur.deleteCharAt(cur.length() - 1);
        }
    }
}
