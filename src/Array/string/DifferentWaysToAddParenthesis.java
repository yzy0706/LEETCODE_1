package Array.string;

import java.util.ArrayList;
import java.util.List;

public class DifferentWaysToAddParenthesis {

    // 做法: 用了divide into subproblem的做法
    // 1. 先把所有的数和运算符号都放到一个list里
    // 2. 建立一个操作list上从l到r那一段的helper
    //   a. 如果l == r, 那么当前操作的这一段只剩下一个数字的string了, 因为我们是按照运算符号分割开的, 所以直接把这个数放到res里return, 这就是运算到最后面的情况
    //   b. 如果能走到这一步, 则从list里的l+1个string开始 += 2 forloop到r-1, 根据每一个运算符号把左边和右边分割开的部分所计算return回来的list: List<Integer> left, right, 按照当前第i个运算符号两两随机组合运算, 放到当前的res里再return
    // 注意: 这个题就是从只有一个数的情况一步步往上叠加每个运算符号左右所有的运算结果的可能, 然后加到res里

    //Runtime: O(nlog(n)), 对于每一个运算符号都会计算log(n)次(分为左右两部分), Space: O(n)

    public List<Integer> diffWaysToCompute_subProblem(String expression) {
        int len = expression.length();
        if(len < 1) return new ArrayList<>();
        List<String> list = new ArrayList<>();
        for(int i = 0; i < len; i++){
            int r = i + 1;
            while(r < len && Character.isDigit(expression.charAt(r))) r++;
            list.add(expression.substring(i, r)); // 加入integer
            if(r == len) break;
            list.add(expression.substring(r, r+1)); // 加入运算符号
            i = r;
        }
        return cal(list, 0, list.size() - 1);

    }

    private List<Integer> cal(List<String> list, int l, int r){
        List<Integer> res = new ArrayList<>();
        if(l == r){
            res.add(Integer.parseInt(list.get(l))); //只剩下一个数
            return res;
        }
        for(int i = l + 1; i < r; i += 2){ //根据每个运算符号来分割
            List<Integer> left = cal(list, l, i - 1), right = cal(list, i + 1, r);
            for(int ln : left){
                for(int rn : right){
                    if(list.get(i).equals("+")) res.add(ln + rn);
                    else if(list.get(i).equals("-")) res.add(ln - rn);
                    else if(list.get(i).equals("*")) res.add(ln * rn);
                }
            }
        }
        // for(int i : res) System.out.println(i);
        return res;
    }










    // 第一遍自己写的解法， 一直处理String很容易出现format的问题， 可能还是要先转换成List<String>
    List<Integer> res = new ArrayList<>();
    public List<Integer> diffWaysToCompute(String expression) {
        permute(expression);
        return res;
    }


    private void permute(String s){
        int l = 0, len = s.length();
        if(len < 1) return;
        String origin = s;
        for(int m = 1; m < len; m++){
            Character cur = s.charAt(m);
            if(cur == '-' || cur == '*' || cur == '+'){
                int left = Integer.parseInt(s.substring(l, m));
                if(m == len - 1) return;
                int r = m + 1;
                while(r < len && Character.isDigit(s.charAt(r))) r++;
                int right = Integer.parseInt(s.substring(m+1, r));
                int res = cal(left, right, cur);
                permute(s.replaceFirst(s.substring(l, r), String.valueOf(res)));
                l = m + 1;
            }
        }
        if(l == 0) res.add(Integer.parseInt(s));
    }

    private int cal(int left, int right, Character cur){
        if(cur == '-') return left - right;
        if(cur == '+') return left + right;
        return left * right;
    }
}
