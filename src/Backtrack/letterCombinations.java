package Backtrack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class letterCombinations {
    public List<String> letterCombinations(String digits) {
        String[] strs = {"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        List<String> res = new ArrayList<>();
        if(digits==null||digits.length()<1){
            return res;
        }
        backTrack(res,new StringBuilder(),digits,0,strs);
        return res;
    }

    private void backTrack (List<String> res,StringBuilder sb, String digits,int index,String[] strs){
        if(index==digits.length()){
            res.add(sb.toString());
            return;
        }
        for(char ch: strs[Integer.parseInt(digits.substring(index,index+1))].toCharArray()){
            sb.append(ch);
            backTrack(res,sb,digits,index+1,strs);
            sb.setLength(sb.length()-1);
        }
    }





//第二遍自己写的
List<String> res = new ArrayList<>();
    List<String> res = new ArrayList<>();
    public List<String> letterCombinations_2(String digits) {
        StringBuilder sb = new StringBuilder();
        if(digits.length() < 1 || digits == null) return res;
        char[] cl = digits.toCharArray();
        String[] strs = {"abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        char[] nums = {'2','3','4','5','6','7','8','9'};
        Map<Character,String> map = new HashMap<>();
        for(int i = 0 ; i < nums.length ; i++){
            map.put(nums[i],strs[i]);
        }
        reCombine(map,cl,res,new StringBuilder(),0);
        return res;


    }


    public void reCombine(Map<Character,String> map,char[] cl, List<String> res,StringBuilder sb,int index){
        if(sb.length() == cl.length) {
            res.add(sb.toString());
            return;
        }
        Character c = cl[index];
        String cur = map.get(c);
        for(char ch: cur.toCharArray()){
            sb.append(ch);
            reCombine(map,cl,res,sb,index+1);
            sb.setLength(sb.length()-1);
        }
        // for(int j = 0; j < cl.length ; j++){
        //     String cur = map.get(cl[i]);
        //     char[] cl = cur.toCharArray();
        //     for(char c: cl){
        //         sb.append(c);
        //         reCombine(map,cl,res,sb);
        //         sb.setLength(sb.length()-1);
        //     }
        // }
    }
}