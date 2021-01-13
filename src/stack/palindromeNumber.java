package stack;

public class palindromeNumber {
    //stack做法
    public boolean isPalindrome(int x) {
        if(x == 0) return true;
        if(x < 1) return false;
        Stack<Integer> stack = new Stack<>();
        List<Integer> list = new ArrayList<>();


        while(x >= 1){
            int digit = x % 10;
            stack.push(digit);
            list.add(digit);
            x /= 10;
        }

        for(int i = 0 ; i < list.size() ; i++){
            if(list.get(i) == stack.pop()) continue;
            else return false;
        }

        return true;




    }







    public boolean isPalindrome(int x) {
        Boolean res= false;
        if(x<0){
            return res;
        }
        else{
            String str = Integer.toString(x);
            int l = 0;
            int r = str.length()-1;
            while(r>=l){
                if(str.charAt(l)==str.charAt(r)){
                    res=true;
                    r--;
                    l++;
                }
                else{
                    res=false;
                    break;

                }
            }
        }
        return res;
    }

    public boolean isPalindrome(int x) {
        if(x<0 || (x!=0 && x%10==0))
            return false;
        int res = 0;
        while(x>res){
            res = res*10 + x%10;
            x = x/10;
        }
        return (x==res || x==res/10);
    }



}
