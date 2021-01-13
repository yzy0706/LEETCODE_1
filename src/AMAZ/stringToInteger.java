package AMAZ;

import java.util.PriorityQueue;
import java.util.Queue;

public class stringToInteger {
    public int myAtoi(String str){
        int result=0;
        int digit=0;
        for(int i=0 ; i<str.length();i++){
            if(str.substring(i,i+1)==" "){
                continue;
            }

            Queue<Integer> queue= new PriorityQueue<>();
            if(result==0&&Integer.parseInt(str.substring(i,i+1))（不是integer）){
                try(str.substring(i,i+1)){

                }
                break;
            }//第一个就不是int
            if(result!=0&&str.substring(i+1,i+2)（不是integer）){
                break;
            }//取完一段integer了直接结束

            digit++;
            result= Integer.parseInt(str.substring(i,i-digit));
        }

        return result;
    }
}
