package ARRAY;

public class stringToInteger {
    //第二遍解法
    public int myAtoi(String str) {
        if( str.length() < 1 || str == null) return 0;
        long res = 0;
        int len = str.length();
        boolean start = false, negative = false, intGot = false;

        char[] cl = str.toCharArray();

        for(int i = 0 ; i < len ; i++){
            if(res == 0){
                if(cl[i] == ' ' ) continue;
                else if(cl[i] == '-') negative = true;
                else if(Character.isDigit(cl[i])){
                    long digit = Character.getNumericValue(cl[i]);
                    res = res * 10 + digit;
                }
                else return 0;
            }
            else{
                if(cl[i] == ' ') break;
                else if(Character.isDigit(cl[i])){
                    long digit = Character.getNumericValue(cl[i]);
                    res = res * 10 + digit;
                }
                else return 0;

            }

            if(negative) return (int) -res;
            return (int) res;


        }


        // discussion

        public int myAtoi(String str) {
            if (str == null || str.isEmpty()) return 0;
            if (str.length() == 1 &&
                    !Character.isDigit(str.charAt(0))) return 0;
            StringBuilder sb = new StringBuilder();
            int idx = indexOfValidChar(str);
            if (idx == -1) return 0;
            boolean isNegative = false;
            char ch = str.charAt(idx);
            if (ch == '-') {isNegative = true; idx++;}
            if (ch == '+') {idx++;}
            while (idx < str.length()) {
                // construct integer
                ch = str.charAt(idx);
                if (Character.isDigit(ch)) sb.append(ch);
                else break;
                idx++;
            }
            if (sb.toString().isEmpty()) return 0;
            try {
                int val = Integer.parseInt(sb.toString());
                return isNegative ? -1*val : val;
            } catch (Exception ex) {
                return isNegative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
        }

        private int indexOfValidChar(String str) {
            int idx = 0;
            // pass all whitespaces;
            while (idx < str.length()) {
                char ch = str.charAt(idx);
                if (!Character.isWhitespace(ch)) break;
                idx++;
            }
            if (idx == str.length()) return -1;
            char ch = str.charAt(idx);
            // check if index has + or -;
            if (ch == '-') return idx;
            if (ch == '+') return idx;
            if (Character.isDigit(ch)) return idx;
            return -1;
        }




















    public int myAtoi(String str
    ){
        Boolean start = false;
        int result=0;
        if(str==null || str.length()<1){
            return result;
        }
        for(int i=0;i<str.length()-1;i++){

            while(str.charAt(i) == ' '&&start==false){
                start=false;
                result=0;
            }
            if(start==false&&str.substring(i,i+1) instanceof  ){
                start=false;
                result = 0;
            }
            if (start==false&& (str.substring(i,i+1) == "+" ||str.substring(i,i+1)=="-") ){
                start=true;
                result =
            }
            if(start==false && ())



        }
    }





}
