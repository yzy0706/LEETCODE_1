package DynamicProgramming.accumulate;

public class PushDominos {
    //做法:
    //  1. 用一个int[] dp代表每一个点离左边最近的‘R’的距离,
    //  2. 碰到L的话就往左浏览
    //      a. 碰到‘.’就直接变成L,
    //      b. 碰到到R距离大于到L距离肯定就变成‘L’, 因为向左向右倒是同时发生的
    //      c. 碰到到R距离等于到L距离的就变回‘.’, 代表L、R到这个‘.’的距离一样, 那他们就根本不会倒

    // Runtime: O(n) Space: O(n)
    public String pushDominoes(String dominoes) {
        int len = dominoes.length();
        if (len == 0) return "";
        int[] dp = new int[len];
        char[] cl = dominoes.toCharArray();
        for(int i = 0; i < len; i++){

            if(cl[i] == 'L'){
                int cnt = 1;
                if(i == 0) continue;
                for(int j = i - 1; j >= 0; j--){
                    if(cl[j] == '.') cl[j] = 'L';
                    else if(dp[j] == cnt){
                        cl[j] = '.';
                    }
                    else if(dp[j] > cnt){
                        cl[j] = 'L';
                    }
                    else if(dp[j] < cnt || cl[i] == 'R'){ //如果cl[j] < j代表R的距离比L过来的距离近, 或者cl[j] 就是R, 那么这里就应该是R, 直接break
                        break;
                    }
                    cnt ++;
                }
            }

            else if(cl[i] == 'R'){
                if(i == cl.length - 1) break;
                for(int j = i + 1; j < len; j++){
                    if(cl[j] == '.'){
                        dp[j] =  j - i;
                        cl[j] = 'R';
                    }
                    else{
                        break;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(char c : cl){
            sb.append(c);
        }

        return sb.toString();
    }
}
