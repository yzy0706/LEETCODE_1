package Array;

    public class longestCommonPrefix {
        public String longestCommonPrefix(String[] strs) {
            if(strs==null||strs.length<1){
                return "";
            }
            String res = strs[0];
            for(int i=0;i<strs.length;i++){
                while(strs[i].indexOf(res)!=0)
                {
                    res=res.substring(0,res.length()-1);
                }
            }
            return res;

        }






//第二遍自己写的
public String longestCommonPrefix_2(String[] strs){
if(strs.length < 1 || strs == null) return "";
        String res = "";
        int mark = 0;

        while(mark <= strs[0].length()){
        for(String s : strs){
        if(mark <= s.length()){
        if(!s.substring(0,mark).equals(res)) return s.substring(0,mark-1);
        }
        else return s.substring(0,mark-1);
        }
        if(mark == strs[0].length()) return res;
        mark++;
        res = strs[0].substring(0,mark);

        }


        return res;
        }
    }

