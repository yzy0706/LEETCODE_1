package OA2;

public class BreakAPalidrome {
    public String breakPalindrome(String palindrome) {
        char[] cl = palindrome.toCharArray();
        int length = cl.length;
        for(int i = 0; i < length/2; i++){
            if(cl[i] != 'a') {
                cl[i] = 'a';
                return String.valueOf(cl);
            }
        }
        cl[length-1] = 'b';
        return length < 2? "" : String.valueOf(cl);
    }
}
