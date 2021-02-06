package Greedy.Array;

public class MinStartValueToAlwaysGetBiggerThanOne {
    //做法: res在最外层, 一开始就设定为nums[0] < 0 ? 1 - nums[0] : 1, 设定一个cnt = res, 就是我最开始的时候的startValue
    //随着i的forloop += nums上的每一个数, 如果当前 cnt < 1了就把startValue也就是res升高, whileloop终止于当前的和 cnt == 1为止
    //Runtime: O(n), space: O(1)

    public int minStartValue(int[] nums) {
        int res = nums[0] < 0 ? 1 - nums[0] : 1;
        int cnt = res;
        for(int i = 0; i < nums.length; i++){
            cnt += nums[i];
            while(cnt < 1){
                res ++;
                cnt ++;
            }
        }
        return res;
    }
}
