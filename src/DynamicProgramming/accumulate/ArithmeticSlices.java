package DynamicProgramming.accumulate;

public class ArithmeticSlices {
    //做法: 做法其实就是从index为2开始检查前两个区间的差是不是相等, 是的话cnt++, 代表从i-2到i是递增的, cnt就代表当前这个区间能组成的递增组合的数量
    //然后每往后一个数如果还是递增的cnt再++, 因为其实是借助前面三个比如说 1, 2, 3是递增的, 我现在到了4. 那么1, 2, 3, 4肯定是成立的,
    // 因为1, 2, 3递增所以2, 3, 4肯定也递增, 这就是为什么cnt也要++, 然后每一步res += cnt

    //Runtime: O(n), space: O(1)

    public int numberOfArithmeticSlices(int[] A) {
        if(A.length < 3) return 0;
        int[] dp = new int[A.length];
        int res = 0;
        int cnt = 0; //记录当前连续递增的数字能组成几个连续递增的区间
        for(int i = 2; i < A.length; i++){
            if(A[i] - A[i-1] == A[i-1] - A[i-2]){
                cnt++;
                res += cnt; //
            }
            else{
                cnt = 0;
            }
        }

        return res;
    }

}
