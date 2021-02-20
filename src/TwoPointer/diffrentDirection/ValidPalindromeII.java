package TwoPointer.diffrentDirection;

public class ValidPalindromeII {
    // 做法: 用recursion和two pointer, 假如当前l和r不一致就检查l+1,r或者l,r-1这个区间是不是完全对称
    // Runtime: O(n), Space: O(1)

    public boolean validPalindrome(String s) {
        int l = 0, r = s.length() - 1;
        while(l <= r){
            if(s.charAt(l) == s.charAt(r)){
                l++;
                r--;
            }
            else{
                return check(s, l+1, r) || check(s, l, r-1);
            }
        }

        return true;
    }

    private boolean check(String s, int l, int r){
        while(l <= r){
            if(s.charAt(l) != s.charAt(r)){
                return false;
            }
            l++;
            r--;
        }
        return true;
    }
}
