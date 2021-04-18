package DynamicProgramming;

public class climbingChairs {
    //dp解法主要就是用一个一维dp来存储当前的解法有多少个
    public int climbStairs(int n) {
        int[] dp = new int[n+1];
        return find(n, 0, dp);
    }

    public int find(int n, int i, int[] dp){
        if(i > n) return 0;
        if(i == n) return 1;
        if(dp[i] > 0) return dp[i];
        dp[i] = find(n, i+1, dp) + find(n, i+2, dp);
        return dp[i];
    }








    //recursion 解法stack overflow
    public int climbStairs_1(int n) {
        if(n==0) return 0;
        int[] res = new int[1];
        int cur = n;
        check(res,cur);
        return res[0];
    }

    public void  check(int[] res, int cur){
        if(cur == 0) {
            int cnt = res[0] ;
            cnt++;
            res[0] = cnt;
        }
        if(cur < 0) return;
        else{
            check(res,cur-1);
            check(res,cur-2);
        }
    }

//递增dp法


//    public int climbStairs(int n) {
//        int[] list = new int[n + 1];
//        check(list, n);
//    }
//
//    public int check(int[] list, int n){
//        if(n<=2) list[n] = n;
//        if(list[n] == 0) list[n] = check(list,n-1) + check(list, n-2);
//        return list[n];
//
//}







// discussion上来的解法
  public int discussionSolution1(int n){
      int[] memory = new int[n+1];
      return climb(n, memory);
  }

    public int climb(int n, int[] memory){
        if(n <= 2){
            return n;
        }
        if(memory[n] != 0){
            return memory[n];
        }
        memory[n] = climb(n-1, memory) + climb(n-2, memory);
        return memory[n];
    }
}




