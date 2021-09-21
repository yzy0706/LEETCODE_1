package DynamicProgramming.doubleForLoop.longestSubsequence;

public class WiggleSubsequence {
    //做法: 设立两个array, up[i]代表在i之前最后一位是上涨的最长连续wiggle, down[i]代表最后一位是下降的最长连续wiggle,
    //求连续的最长数列的情况一般都是用dp来记载当前最长的长度然后再根据尾部来更新长度
    //1. 如果最后一位比倒数第二位大, up[i] = down[i-1] + 1; , down[i] = down[i-1];, down[i-1]是最后一个区间下降的条件下i-1前最长的array
    //2. 如果最后一位比倒数第二位小, down[i] = up[i-1] + 1; , up[i] = up[i-1];,
    //3. 如果最后一位等于倒数第二位, 那down[i]和 up[i]都不会变

    public int wiggleMaxLength(int[] nums) {
        if(nums.length == 0) return 0;
        if(nums.length == 1) return 1;
        int[] up = new int[nums.length];
        int[] down = new int[nums.length];
        up[0] = 1;
        down[0] = 1;

        for(int i = 1; i < nums.length; i++){
            if(nums[i] > nums[i-1]){ //最后一位是上升
                up[i] = down[i-1] + 1;
                down[i] = down[i-1];
            }
            else if(nums[i] < nums[i-1]){//最后一位下降
                down[i] = up[i-1] + 1;
                up[i] = up[i-1];
            }
            else{//最后一位跟之前相等
                up[i] = up[i-1];
                down[i] = down[i-1];
            }
        }

        return Math.max(up[nums.length - 1], down[nums.length - 1]);
    }
}
