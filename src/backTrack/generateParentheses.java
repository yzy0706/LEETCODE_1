package backTrack;

import java.util.ArrayList;
import java.util.List;

public class generateParentheses {
    public List<String> generateParenthese(int n){
        List<String> res = new ArrayList<>();
        generate(n,res, n, 0,"");
        return res;
    }
    public void generate (int n, List<String> res, int left, int right,String temp){
        if(left < 0 || right < 0) return;
        if(left == 0 && right == 0) {
            res.add(temp);
        }
        else{
            generate(n,res,left-1, right+1, temp.concat("("));
            generate(n,res,left,right-1,temp.concat(")"));
        }

    }

    //看了答案以后写的

    public List<String> generateParenthesis_2(int n) {

        List<String> res = new ArrayList<>();
        // if(n == 0) return res;
        // if(n == 1) return new ArrayList<>("()");
//        StringBuilder cur = new StringBuilder
        create(res, "", 0 , 0, n);
        return res;


    }

    public void create(List<String> res, String cur, int open, int close, int n ){
        if(cur.length() == n * 2 ) {
            res.add(cur.toString());
            return;
        }
        if(open < n){

            create(res,  cur + "(" , open+1, close, n);
        }
        if(close < open){
            create(res, cur + ")", open, close+1,n);
        }

    }




}
