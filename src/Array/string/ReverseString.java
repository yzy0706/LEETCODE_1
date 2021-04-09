package Array.string;

public class ReverseString {
    // 做法 : 就是一个简单的反向pointer然后互相swap
    // Runtime: O(n), Space: O(1)
    public void reverseString(char[] s) {
        int l = 0, r = s.length - 1;
        while(l < r){
            Character temp = s[l];
            s[l] = s[r];
            s[r] = temp;
            l ++;
            r --;
        }
    }
}
