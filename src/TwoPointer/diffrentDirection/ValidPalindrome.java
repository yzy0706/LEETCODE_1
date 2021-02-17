package TwoPointer.diffrentDirection;

public class ValidPalindrome {
    //做法: 简单的反向two pointer, 要注意的是数字也算作string的一部分所以要用isLetterOrDigit而不是isAlphabet
    //Runtime: O(n), Space: O(1)
    public boolean isPalindrome(String s) {
        int l = 0, r = s.length() - 1;

        while(l < r){
            Character left = s.charAt(l), right = s.charAt(r);
            if(Character.isLetterOrDigit(left) && Character.isLetterOrDigit(right)){
                if(Character.toLowerCase(left) != Character.toLowerCase(right)){
                    return false;
                }
                l++;
                r--;
            }
            else{
                if(!Character.isLetterOrDigit(left)){
                    l++;
                }
                if(!Character.isLetterOrDigit(right)){
                    r--;
                }
            }
        }

        return true;
    }
}
