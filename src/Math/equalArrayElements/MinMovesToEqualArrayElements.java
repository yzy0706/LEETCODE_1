package Math.equalArrayElements;

public class MinMovesToEqualArrayElements {
    //做法: 这是个数学问题, sum + move * (len - 1) = target * len, target = min + move, 所以move + len * min = sum
    // 1. 先求出最小值和所有数的和
    // 2. return sum - nums.length * min;

    //Runtime: O(n), Space: O(1);
    public int minMoves(int[] nums) {
        int min = Integer.MAX_VALUE;
        int sum = 0;
        for(int i : nums){
            min = Math.min(min, i);
            sum += i;
        }
        return sum - nums.length * min;
    }
}
