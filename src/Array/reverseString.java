package Array;

public class reverseString {
    public void reverseString(char[] s) {
        if(s.length % 2 != 0) reverse(s,(s.length-1)/2,(s.length-1)/2);
        if(s.length % 2 == 0) reverse(s,s.length/2-1,s.length/2);

    }

    public void reverse(char[] s, int l, int r){
        while(l>=0&&r<s.length){
            char a = s[l];
            char b = s[r];
            s[l]=b;
            s[r]=a;
            l--;
            r++;
        }
    }
}
