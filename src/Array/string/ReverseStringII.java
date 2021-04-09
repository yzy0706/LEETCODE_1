package Array.string;

import java.util.LinkedList;
import java.util.Queue;

public class ReverseStringII {
    //做法: 用一个pos随时记录当前到哪个位置了, 用到的技巧是用StringBuilder()来reverse s.substring(pos, pos+k);
    //Runtime: O(n), Space: O(n);

    public String reverseStr_reviewed(String s, int k) {
        int len = s.length();
        if(len < 1) return "";
        int pos = 0;
        StringBuilder sb = new StringBuilder();
        while(pos < s.length()){
            StringBuilder cur = pos + k >= len ? new StringBuilder(s.substring(pos, len)) : new StringBuilder(s.substring(pos, pos + k));
            sb.append(cur.reverse());
            if(pos + k >= len) break;
            if(pos + 2*k >= len) sb.append(s.substring(pos + k, len));
            else sb.append(s.substring(pos + k, pos + 2*k));
            pos += 2*k;
        }
        return sb.toString();
    }






    //第一遍写的
    public String reverseStr(String s, int k) {
        int len = s.length(), pos = 0;
        if(len < 1) return "";
        char[] cl = s.toCharArray();
        Queue<Character> queue = new LinkedList<Character>();
        boolean betweenK2K = false;
        StringBuilder sb = new StringBuilder();

        while(pos < len ) {
            if(!betweenK2K){
                queue.offer(cl[pos]);
                if(pos % k == 0 &&  pos % 2*k != 0){
                    while(!queue.isEmpty()){
                        sb.append(queue.poll());
                    }
                    betweenK2K = true;
                }

            }

//            else if(betweenK2K){
//                sb.append(char[pos]);
//                if(pos % 2k ==0){
//                    betweenK2K = false;
//                }
//            }

            pos++;
        }


        return sb.toString();
    }
}
