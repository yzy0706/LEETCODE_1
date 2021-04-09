package Array;
public class ProductOfArrayExceptSelf {
    //做法: 这题主要要考虑一下有一个0和超过一个0的情况:
    // a. 如果一个0, 那就res上除了那个位置不是0, 其它位置都是0
    // b. 如果超过一个0, 那么不管怎么样res都得全是0

    // 1. 建立一个res = new int[nums.length]; 一个indexZero记录0的位置
    // 2. 先forloop一遍所有的nums, 如果发现超过两个0, 那么直接return res, 否则把除了0之外的乘积求出来
    // 3. 如果当前检测到有0, 那么res[zeroIndex] = prod; 直接return//
    // 4. 否则还能跑到当前的一步就证明整个nums里都没有0, 那么对于res的每个位置, res[i] = prod/nums[i];

    // Runtime: O(n), Space: O(1);
    public int[] productExceptSelf_reviewed(int[] nums) {
        int prod = 1;
        int indexZero = -1;
        int[] res = new int[nums.length];
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == 0){
                if(indexZero != -1) return res;
                indexZero = i;
                continue;
            }
            prod *= nums[i];
        }
        if(indexZero != -1){
            res[indexZero] = prod;
            return res;
        }
        for(int i = 0; i < nums.length; i++){
            res[i] = prod/nums[i];
        }
        return res;
    }




    // 左右乘的做法, 先把每个数左边的乘积都放在res[i]上, 然后再把右边的乘积乘在res[i]上
    // Runtime: O(n), Space: O(n)
    public int[] productExceptSelf_leftAndRight(int[] nums) {
        int[] res = new int[nums.length];
        res[0] = 1;
        for(int i = 1; i < nums.length; i++) res[i] = res[i-1] * nums[i-1];
        int r = 1;
        for(int i = nums.length - 1; i >= 0; i--){
            res[i] *= r;
            r *= nums[i];
        }
        return res;
    }

    //第一遍自己写的，就是每次把除了自己乘一遍
    public int[] productExceptSelf_1(int[] nums) {
        int[] product = new int[nums.length];
        for(int j = 0; j < nums.length; j++ ){
            product[j] = 1;
        }
        for(int i = 0; i < nums.length; i++){
            for(int a = 0; a < nums.length; a++){
                if(a == i) continue;
                else product[a] *= nums[i];
            }
        }
        return product;
    }
}
