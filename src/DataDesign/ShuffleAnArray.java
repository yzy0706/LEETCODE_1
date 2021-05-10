package DataDesign;

import java.util.Random;

public class ShuffleAnArray {
    // 做法: 这题主要是新建立了一个Random random;
    // reset: 直接return原来nums的clone()
    // shuffle : 在shuffle的时候, 从i = 1开始forloop, j = random.nextInt(i + 1), 然后按照正常的array上兑换方法兑换i和j位置
    // Runtime: O(n), Space: O(n);

    int[] original;
    int[] nums;
    Random random;
    public void Solution(int[] nums) {
        original = nums.clone();
        this.nums = nums;
        random = new Random();
    }

    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return original;
    }

    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        for(int i = 1; i < nums.length; i++){
            int j = random.nextInt(i + 1);
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
        return nums;
    }
}
