package DynamicProgramming.adjacent;

public class LongestTurbulentSubarray {
    //做法: 这题就是一个一维的dp, (只是把一维的int[] dp换成了curLen来节省空间), 来记录index i之前连续的turbulent subarray,
    // 1. forloop整个arr, 每一个位置都记录当前和之前的数的差, 注意这里arr[i]和arr[i-1]必须用compareTo而不是相减, 避免他们的差太大没法判断curDiff * diff 大于还是小于0
    //  a. 如果curDiff * diff < 0, 证明当前满足turbulent的条件, curLen ++;
    //  b. 如果curDiff == 0, 证明当前的数跟之前相等, 当前位置的turbulent array只能算他自己这个数也就是1
    //  c. 如果curDiff != 0, 之前又通过else知道了 curDiff * diff > 0了, 所以当前位置的dp记录为2

    // Runtime: O(n), Space: O(1)
    public int maxTurbulenceSize_1d(int[] arr) {
        int len = arr.length;
        if(len == 1) return 1;
        // int[] dp = new int[len];
        // Arrays.fill(dp, 1);
        int curLen = 1;
        int res = 1;
        int diff = 0;

        for(int i = 1; i < len; i++){
            int curDiff = Integer.compare(arr[i], arr[i-1]);
            if(curDiff * diff < 0){
                curLen ++;
            }
            else if(curDiff != 0){
                curLen = 2;
            }
            else{
                curLen = 1;
            }
            res = Math.max(res, curLen);
            diff = curDiff;
        }
        return res;
    }





    public int maxTurbulenceSize(int[] arr) {
        int len = arr.length;
        int[][] dp = new int[len][len];
        int res = 1;
        for(int j = 0; j < len; j++){
            dp[j][j] = 1;
            int diff = 0;
            for(int i = j - 1; i >= 0; i--){
                if(arr[i] == arr[i+1]){
                    break;
                }
                else{
                    if(i == j-1){
                        diff = arr[j] - arr[i];
                        dp[i][j] = 2;
                        // if(j == 6) System.out.println(diff);
                    }
                    else{
                        // if(j == 6) System.out.println(diff);
                        if((arr[i+1] - arr[i]) * diff < 0){
                            dp[i][j] = dp[i+1][j] + 1;
                            diff = arr[i+1] - arr[i];
                        }
                        else{
                            break;
                        }
                    }
                }
                res = Math.max(res, dp[i][j]);
            }
        }
        return res;
    }
}
