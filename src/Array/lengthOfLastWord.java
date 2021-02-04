package Array;

public class lengthOfLastWord {
    public int lengthOfLastWord(String s) {
        int end = 0;
        int start = 0;
        boolean exist = false;
        s.toCharArray();
        s.
        if(s=="") return 0;
        for (int i = s.length() - 1; i>= 0 ; i--){
            if(s.charAt(i) != ' '&& exist == false){
                end = i+1;
                exist = true;
            }
            if(exist == true && s.charAt(i) != ' '){
                start  = i;
            }


            if(exist == true && s.charAt(i) == ' '){
                break;

            }

        }

        return end - start;



    }
}
