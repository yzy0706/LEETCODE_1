package Array.markIndex;

public class StringCompression {
    // 做法: 用了一堆的pointer来做,反正就是记录上一段标记结束以后应该从哪个位置开始标记, 如果cnt是多位数就用余数标记一下每个digit
    // Runtime: O(n), Space: O(1)
    public int compress(char[] chars) {
        if(chars.length == 1) return 1;
        int cnt = 1, res = 0, markPos = 0;
        for(int i = 1; i <= chars.length; i++){
            if(i != chars.length && chars[i] == chars[i-1]){
                cnt ++;
            }
            else{
                if(cnt == 1){
                    chars[markPos++] = chars[i-1];
                    res ++;
                    continue;
                }
                else{
                    chars[markPos] = chars[i-1];
                    res ++;
                    int digitLen = 0;
                    if(cnt < 10) digitLen = 1;
                    else if(cnt < 100) digitLen = 2;
                    else if(cnt < 1000) digitLen = 3;
                    else if(cnt < 10000) digitLen = 4;
                    int lastMarkPos = markPos + digitLen;
                    markPos = lastMarkPos + 1;
                    res += digitLen;
                    while(cnt > 0){
                        int digit = cnt % 10;
                        cnt /= 10;
                        chars[lastMarkPos--] = (char)(digit + '0');
                    }
                    cnt = 1;
                }
            }
        }
        return res;
    }
}
