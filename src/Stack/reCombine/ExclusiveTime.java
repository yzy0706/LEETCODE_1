package Stack.reCombine;

import java.util.List;
import java.util.Stack;

public class ExclusiveTime {
    // 做法: 因为这题对于时间的刻度比较奇怪, 所以我们主要要分别处理一下当前的status是start还是end的情况
    // 1. 先设置一个stack来记录上一个function的ID, 一个int prevStart记录上一个开始的时间
    // 2. 如果stack不是空的, stack.peek()就是上一个方程的id, 不管当前是start还是end, 我们先用当前的时间 res[stack.peek()] += timeStamp - prevStart; , 就算如果当前是end还需要再多算一分钟, 我们也后面再处理, prevStart更新为当前的timeStamp
    // 3. 我们再判断当前是不是start:
    //      a. 如果是start, 就不用多余的处理, stack.push(ID);
    //      b. 如果当前是end, 首先prevStart相当于再过了一分钟到当前这一分钟的末尾, prevStart ++ ,
    //      然后总的时间也要多算一分钟, 而且是end的话上一个方程就结束了, 所以还要把上一个id pop出来, res[stack.pop()] ++;
    // Runtime: O(n), Space: O(n)
    public int[] exclusiveTime_dicussion(int n, List<String> logs) {
        int[] res = new int[n];
        Stack<Integer> stack = new Stack<>();
        int prevStart = 0;
        for(String s : logs){
            String[] list = s.split(":");
            int ID = Integer.parseInt(list[0]);
            String status = list[1];
            int timeStamp = Integer.parseInt(list[2]);
            if(!stack.isEmpty()){
                res[stack.peek()] += timeStamp - prevStart;
            }
            prevStart = timeStamp;
            if(status.equals("start")){
                stack.push(ID);
            }
            else{
                prevStart ++;
                res[stack.pop()] ++;
            }
        }
        return res;
    }











    //第一遍写的
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] res = new int[n];
        Stack<int[]> stack = new Stack<>();
        for(String s : logs){
            String[] list = s.split(":");
            int ID = Integer.parseInt(list[0]);
            String status = list[1];
            int timeStamp = Integer.parseInt(list[2]);
            if(status.equals("end")){
                if(stack.isEmpty()) return new int[n];
                int[] last = stack.pop();
                int lastStart = last[1];
                res[ID] += timeStamp - lastStart + 1;
            }
            else if(status.equals("start")){
                if(!stack.isEmpty()){
                    int[] last = stack.pop();
                    int lastID = last[0];
                    int lastStart = last[1];
                    res[lastID] += timeStamp - lastStart;
                }
                stack.push(new int[]{ID, timeStamp});
            }
        }
        return res;
    }
}
