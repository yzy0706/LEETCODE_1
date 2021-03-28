package Array.romanIntegerConvert;

public class IntegerToRoman {
    // 做法: 用了一个int[] nums和一个String[] strs, 分别代表每一个罗马字母和他对应的数
    // 1. forloop int[] nums里所有的数
    // 2. 对于当前的num来说, 就跟二进制每次进位一样, 当当前的数还大于等于nums[i]时,
    // while(num >= nums[i]) num -= nums[i]; sb.append(strs[i]),
    // 一直到当前的数已经比当前字母对应的数小了, 我们就去浏览下一个更小的数

    // Runtime: O(n), Space: O(n);
    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        int[] nums = {1000, 900,500,400,100,90,50,40,10,9,5,4,1};
        String[] strs = {"M" ,"CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};

        for(int i = 0; i < nums.length ; i++){
            while(num >= nums[i]){
                sb.append(strs[i]);
                num -= nums[i];
            }
        }

        return sb.toString();
    }
}
