package Array.string;

public class LengthOfLastWord {
    //做法: 我第一遍直接用split做的, 就没有想太多, 因为我只要求最后一位string的长度
    //Runtime: O(n), Space: O(n), n是string的整个长度

    public int lengthOfLastWord(String s) {
        String[] cl = s.split(" ");
        if(cl.length < 1) return 0;
        return cl[cl.length - 1].length();
    }





    public int lengthOfLastWord_forloop(String s) {
        int end = 0;
        int start = 0;
        boolean exist = false;
        s.toCharArray();
        if(s=="") return 0;
        for (int i = s.length() - 1; i>= 0 ; i--){
            if(s.charAt(i) != ' ' && exist == false){
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
