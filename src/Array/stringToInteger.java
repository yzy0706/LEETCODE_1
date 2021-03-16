package Array;

public class stringToInteger {

    // 做法: 以后碰到这种阅读string的需要一次性读完一串东西就用whileloop直接一串读完, 然后其他情况再分别讨论, 不然再根据当前的res去讨论
    // Runtime: O(n), Space: O(1)
    public int myAtoi(String str) {
        if( str.length() < 1 || str == null) return 0;
        long res = 0;
        int len = str.length();
        boolean negative = false;
        char[] cl = str.toCharArray();

        for(int i = 0 ; i < len ; i++){
            if(Character.isDigit(cl[i]) || cl[i] == '+' || cl[i] == '-'){
                if(cl[i] == '-'){
                    negative = true;
                    i++;
                }
                else if(cl[i] == '+'){
                    i++;
                }
                while(i < len && Character.isDigit(cl[i])){
                    res = res * 10 + Character.getNumericValue(cl[i]);
                    if(Math.abs(res) >= Integer.MAX_VALUE) break;
                    i++;
                }
                break;
            }
            else{
                if(cl[i] == ' '){
                    continue;
                }
                else{
                    break;
                }
            }
        }

        if(Math.abs(res) > Integer.MAX_VALUE){
            if(negative) return Integer.MIN_VALUE;
            else return Integer.MAX_VALUE;
        }

        if(negative) return (int) -res;
        return (int) res;
    }






    public int myAtoi_Mar10_firstTry_noWhileLoop(String str) {
        if( str.length() < 1 || str == null) return 0;
        long res = 0;
        int len = str.length();
        boolean negative = false;
        char[] cl = str.toCharArray();

        for(int i = 0 ; i < len ; i++){
            if(res == 0){
                if(Character.isDigit(cl[i])){
                    long digit = Character.getNumericValue(cl[i]);
                    res = digit;
                    continue;
                }
                if(i == len - 1) break;
                else if(cl[i] == ' ' || cl[i] == '+' && (i == 0 || !Character.isDigit(cl[i-1])) && Character.isDigit(cl[i+1])) continue;
                else if(cl[i] == '-' && (i == 0 || !Character.isDigit(cl[i-1])) && Character.isDigit(cl[i+1])) negative = true;
                else break;
            }
            else{
                if(Character.isDigit(cl[i])){
                    long digit = Character.getNumericValue(cl[i]);
                    res = res * 10 + digit;
                }
                else break;
            }
        }

        if(res > Integer.MAX_VALUE){
            if(negative) return Integer.MIN_VALUE;
            else return Integer.MAX_VALUE;
        }

        if(negative) return (int) -res;
        return (int) res;
    }





    //第二遍解法
    public int myAtoi(String str) {
        if (str.length() < 1 || str == null) return 0;
        long res = 0;
        int len = str.length();
        boolean start = false, negative = false, intGot = false;

        char[] cl = str.toCharArray();

        for (int i = 0; i < len; i++) {
            if (res == 0) {
                if (cl[i] == ' ') continue;
                else if (cl[i] == '-') negative = true;
                else if (Character.isDigit(cl[i])) {
                    long digit = Character.getNumericValue(cl[i]);
                    res = res * 10 + digit;
                } else return 0;
            } else {
                if (cl[i] == ' ') break;
                else if (Character.isDigit(cl[i])) {
                    long digit = Character.getNumericValue(cl[i]);
                    res = res * 10 + digit;
                } else return 0;

            }
        }
            if (negative) return (int) -res;
            return (int) res;


        }

    }



        // discussion

//




















//
//    public int myAtoi(String str
//    ){
//        Boolean start = false;
//        int result=0;
//        if(str==null || str.length()<1){
//            return result;
//        }
//        for(int i=0;i<str.length()-1;i++){
//
//            while(str.charAt(i) == ' '&&start==false){
//                start=false;
//                result=0;
//            }
//            if(start==false&&str.substring(i,i+1) instanceof  ){
//                start=false;
//                result = 0;
//            }
//            if (start==false&& (str.substring(i,i+1) == "+" ||str.substring(i,i+1)=="-") ){
//                start=true;
//                result =
//            }
//            if(start==false && ())
//
//
//
//        }
//    }


