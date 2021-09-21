package DynamicProgramming.doubleForLoop.longestSubsequence;

public class ShortestWayToFormString {
    // 做法: 这题其实我是forloop了整个target, 并用dp来记录了target上每一个char在source上的位置, 其实因为每次新建subsequence我都到最前面去, 所哦咦也可以看作一个greedy的问题
    // 1. forloop整个target, 每一个当前的character记为cur
    //      a. 如果当前i == 0 或者当前cur在source上的最后的位置都 <= dp[i-1], 当前就重新开始找新的subsequece, 那么当前dp[i]肯定越靠前越好, dp[i] = source.indexOf(cur); res++;
    //      b. 如果当前cur在source的最后一位 > dp[i-1], 那么当前的位置肯定可以继续在当前的subsequence里, 但我们不能直接截取当前这个cur在source上的最后的位置, 因为可能在上一个字母在source里的位置之后, 当前这个字母在source里的最后一个位置之前还可以找到一个更靠前的位置, 所以我们要从上一个字母的后面截取整个source, 然后找到当前cur在这个截取了的source里面最靠前的位置, 加上上一个字母在source里的位置 + 1, dp[i] = dp[i-1] + 1 + afterLast.indexOf(cur);

    // Runtime: O(n), Space: O(n), n是target的长度
    public int shortestWay(String source, String target) {
        int sLen = source.length(), tLen = target.length();
        int[] dp = new int[tLen];
        int res = 0;
        for(int i = 0; i < tLen; i++){
            Character cur = target.charAt(i);
            if(source.indexOf(cur) == -1){
                return -1;
            }
            // if(i == 3) System.out.println(source.lastIndexOf(cur) + " " + dp[i-1]);
            if(i == 0 || source.lastIndexOf(cur) <= dp[i-1]){
                res ++;
                dp[i] = source.indexOf(cur);
            }
            else{
                // dp[i] = source.indexOf(cur);
                String afterLast = source.substring(dp[i-1] + 1);
                dp[i] = afterLast.indexOf(cur) + dp[i-1] + 1;
            }
        }
        return res;
    }
}
