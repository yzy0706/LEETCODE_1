package Stack.reCombine;

public class RemoveAdjacentDuplicateLettersII {
    //做法： 其实碰到这种重新拼接的题都应该想到stack

    //这题跟twopointer的思路其实一样, 只是用了一个stringbuilder当作stack用, 代替了two pointer上一只在 i -= k 往回退的i,
    //取而代之的是一个不断砍尾巴的StringBuilder

    //1. 建立一个foreach loop浏览所有的字符, sb.append(c); ,  建立一个int[] cnt 来记录当前最后一位的位置一共有几个连续的相同字符
    //2. 最后一个字符的位置是 indexOnCnt = sb.length() - 1;  每增加一个字符都判断一下是不是跟倒数第二个字符一样, 一样的话就count上增加连续相同的个数, count[indexOnCnt - 1] + 1, 否则就等于1
    //3. 如果当前cnt[最后一个字符的位置] >= k了, 代表应该删掉这些字符了, 所以 sb.delete(sb.length() - k, sb.length()); 删除也是要头不要尾

    //Runtime: O(n), space: count的space最差就是装了所有的字符, 一串都没删过, 所以是 O(n)
    public String removeDuplicates(String s, int k) {
        int[] count = new int[s.length()];
        StringBuilder sb = new StringBuilder();
        char[] cl = s.toCharArray();

        for (char c : cl) {
            sb.append(c);
            int indexOnCnt = sb.length() - 1;
            count[indexOnCnt] = (indexOnCnt > 0 && sb.charAt(indexOnCnt) == sb.charAt(indexOnCnt - 1)) ? count[indexOnCnt - 1] + 1 : 1;
            if (count[indexOnCnt] >= k) {
                sb.delete(sb.length() - k, sb.length());
            }
        }

        return sb.toString();
    }






    public String removeDuplicates_stack(String s, int k) {
        int[] count = new int[s.length()];
        StringBuilder sb = new StringBuilder();
        char[] cl = s.toCharArray();

        for(char c : cl){
            sb.append(c);
            int indexOnCnt = sb.length() - 1;
            count[indexOnCnt] = (indexOnCnt > 0 && sb.charAt(indexOnCnt) == sb.charAt(indexOnCnt - 1)) ? count[indexOnCnt - 1] + 1 : 1;
            if(count[indexOnCnt] >= k){
                sb.delete(indexOnCnt - k, indexOnCnt);
            }
        }

        return sb.toString();
    }








   //快慢指针的做法： i和j一起从0向右跑， stack[i] = stack[j]
   // 1. 建立一个int[] cnt, cnt[i] = ( i > 0 && stack[i-1] == stack[j] ) ? cnt[i-1] + 1 : 1;
   // 2. 如果cnt[i] = k, i -= k, 就又退回开始去了， 下一轮开始时， stack[i] = stack[j] 代表 stack[i]的位置直接跳过所有repeat的字符，
    // 被跳过后的stack[j]赋值

    // Runtime: O(n), space: O(n)
   public String removeDuplicates_twoPointer(String s, int k) {
       int i = 0, n = s.length(), count[] = new int[n];
       char[] stack = s.toCharArray();
       for (int j = 0; j < n; ++j, ++i) {
           stack[i] = stack[j];
           count[i] = i > 0 && stack[i - 1] == stack[j] ? count[i - 1] + 1 : 1;
           if (count[i] == k) i -= k;
       }

       return new String(stack, 0, i);
   }






    //第一遍自己准备用recursion和 s.replace("!", "")的删除方法去删掉重复的， 发现不行
//    public String removeDuplicates(String s, int k) {
//        char[] cl = s.toCharArray();
//        int start = 0, end = 0;
//        StringBuilder sb = new StringBuilder(s);
//
//        for(int i = 1; i < cl.length; i++){
//            if(cl[i] != cl[i-1]){
//                start = i;
//            }
//            else{
//                while(cl[i] == cl[i-1]){
//                    end = i+1;
//                    i++;
//                }
//                i--;
//                if(end - start >= k){
//                    sb.replace(start, end, "!");
//                }
//            }
//        }
//
//        String si = sb.toString();
//        si.replace("!", "");
//        return removeDuplicates(si, k);
//    }
}
