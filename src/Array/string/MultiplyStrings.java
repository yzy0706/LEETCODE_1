package Array.string;

public class MultiplyStrings {
    // 做法: 用的是把每两位的digit, num1.charAt(i), num2.charAt(j)的数字乘起来, 影响 i+j 和 i+j+1两位的数字
    // 1. 建立一个res = new int[len1 + len2];
    // 2. 分别从num1, num2的最后一位往前forloop
    //      1. 当前进位以后的数 mul = digit1 * digit2 + res[i + j + 1];
    //      2. 进位了以后, 十位数就是mul/10, 所以res[i+j] += mul/10;
    //      3. 个位数是mul%10, 特别注意那么个位数这个位置就不是相加而是替换了 res[i+j+1] = mul%10;
    // 3. forloop完了以后最开始的位数可能有0 , 所以if(!(i == 0 && sb.length() == 0)) sb.append(i);
    // Runtime: O(mn), Space: O(m+n)

    public String multiply(String num1, String num2) {
        if(num1.equals("0") || num2.equals("0")) return "0";
        int len1 = num1.length(), len2 = num2.length();
        int[] res = new int[len1 + len2];
        for(int i = len1 - 1; i >= 0; i--){
            for(int j = len2 - 1; j >= 0; j--){
                int last1Digit = i + j + 1, last2Digit = i + j;
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0') + res[last1Digit]; //要进位
                res[last2Digit] += mul/10;
                res[last1Digit] = mul%10;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i : res){
            if(!(i == 0 && sb.length() == 0)) sb.append(i);
        }
        return sb.toString();
    }
}
