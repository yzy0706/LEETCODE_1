package Math;

public class AddStrings {
    //做法: 从最后一位往前两两相加, 再加上cnt,
    //int digit1 = i >= 0 ? num1.charAt(i) - '0' : 0;
    //int digit2 = j >= 0 ? num2.charAt(j) - '0' : 0;
    //1. 如果cnt > 0, cnt --
    //2. 如果digit >= 10, digit %= 10, cnt ++;
    //3. sb.append(digit);
    //最后在return反过来的string, 能写在一起的forloop不要分开来写, 顶多在里面加一个判断

    //Runtime: O(n), space: O(n)
    public String addStrings(String num1, String num2) {
        int len1 = num1.length(), len2 = num2.length();
        int cnt = 0;
        StringBuilder sb = new StringBuilder();
        int i = len1 - 1, j = len2 - 1;
        while(i >= 0 || j >= 0){
            int digit1 = i >= 0 ? num1.charAt(i) - '0' : 0;
            int digit2 = j >= 0 ? num2.charAt(j) - '0' : 0;
            int digit = digit1 + digit2 + cnt;
            if(cnt > 0){
                cnt --;
            }
            if(digit >= 10){
                cnt ++;
                digit %= 10;
            }
            sb.append(digit);
            i--;
            j--;
        }
        if(cnt != 0){
            sb.append(cnt);
        }
        sb.reverse();
        return sb.toString();
    }
}
