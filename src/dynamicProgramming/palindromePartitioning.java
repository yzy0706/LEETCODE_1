package dynamicProgramming;

import java.util.ArrayList;
import java.util.List;

public class palindromePartitioning {
    //用一个boolean[][] pair来判断一个左右坐标的char是不是一样的， 然后用一个List<List<String>>[] dp来随时更新每个坐标上对应的几队队列
    //首先给dp[0]建立一个空的队列， 队列里面的第一个字符串也是空的， 然后forloop整个String s, 每到一个坐标i， 他后面的那个位置dp[i+1]建立一个
    //新的队列， 然后再从左边建立一个pointer left一直forloop到<=当前这个位置， 假如charAt(left) == charAt(i)并且（left+1 >= i || pair[left+1][i-1])
    //也就是他们是字符相等并且他们相连或重合或者他们往中心走的两个字符也是对称的, 那么现在我们就截取l到i， subString(l, i+1), 并且foreach loop一下
    // dp[left] 里面每一个list， 把每个list都加上当前截取出来的string并且加到dp[i+1]上， 因为dp[i+1]代表从0到i所有能找到的对称字符串
    public List<List<String>> partition(String s){
        int length = s.length();
        List<List<String>>[] dp = new List[length+1];
        boolean[][] pair = new boolean[length][length];
        dp[0] = new ArrayList<List<String>>();
        dp[0].add(new ArrayList<String>());

        for(int i = 0; i < length; i++){
            dp[i+1] = new ArrayList<List<String>>();
            for(int l = 0; l <= i; l++){
                if(s.charAt(l) == s.charAt(i) && (l + 1 >= i || pair[l+1][i-1])){
                    String cur = s.substring(l, i+1);
                    pair[l][i] = true;
                    //把left坐标上的所有list都加上当前这个string并且搬到dp[i+1]去
                    for(List<String> listOfleft : dp[l]){
                        List<String> move = new ArrayList<>(listOfleft);
                        move.add(cur);
                        dp[i+1].add(move);
                    }
                }
            }

        }
        return dp[length];
    }
}
