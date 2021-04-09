package Array.string;

    public class LongestCommonPrefix {

        //第三遍用string builder做的

        // 做法: 这题我稍微修改了一下, 因为只要res在每个浏览到的string的indexOf()里面都是0, 就证明当前的res是当前string的common prefix, 如果不是0就把当前的res一直从后面往前delete最后的字符就行
        // Runtime: O(mn), (n是最长的string的长度), Space: O(1);

        public String longestCommonPrefix_3(String[] strs) {
            if(strs.length == 0) return "";
            StringBuilder res = new StringBuilder(strs[0]);
            for(int i = 1; i < strs.length; i++){
                String cur = strs[i];
                if(cur.indexOf(res.toString()) == 0){
                    continue;
                }
                while(res.length() > 0){
                    res.deleteCharAt(res.length() - 1);
                    if(cur.indexOf(res.toString()) == 0){
                        break;
                    }
                }
                if(res.length() == 0){
                    break;
                }
            }
            return res.toString();
        }










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

