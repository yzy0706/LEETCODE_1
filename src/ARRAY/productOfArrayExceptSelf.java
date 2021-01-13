package ARRAY;
public class productOfArrayExceptSelf {
    //solution给的解法就是建一个product先储存从左边乘到这里的积，再从右边开始乘到这里的积，最后return这个product
    public int[] productExceptSelf(int[] nums) {
        int[] product = new int[nums.length];
        product[0] = 1;
        for(int i = 1; i < nums.length; i++){
            product[i] = product[i-1] * nums[i-1];
        }
        int r = 1;
        for(int i = nums.length-1; i >= 0; i--){
            product[i] *= r;
            r *= nums[i];
        }
        return product;
    }

    //第一遍自己写的，就是每次把除了自己乘一遍
    public int[] productExceptSelf(int[] nums) {
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
