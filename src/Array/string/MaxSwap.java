package Array.string;

import java.util.PriorityQueue;

public class MaxSwap {

    // 做法: 第二种解法是用一个int[]来记录每个digit在哪个位置
    // 1. forloop一遍num的String形式, 记录每个digit的位置
    // 2. 再forloop一遍所有的digit, 从大到小检查所有的数字, 如果有数字比当前digit大且位置靠后, 就跟当前位置调换
    // Runtime: O(n), Space: O(n)

    public int maximumSwap_3(int num) {
        if(num <= 10) return num;
        String s = String.valueOf(num);
        char[] cl = s.toCharArray();
        int[] positions = new int[10];
        for(int i = 0; i < cl.length; i ++){
            positions[cl[i] - '0'] = i;
        }
        for(int i = 0; i < cl.length; i++){
            char cur = cl[i];
            for(char j = '9' ; j > cur; j --){
                int pos = positions[j - '0'];
                if(pos > i){
                    char temp = cur;
                    cl[i] = j;
                    cl[pos] = temp;
                    return Integer.valueOf(new String(cl));
                }
            }
        }
        return  Integer.valueOf(new String(cl));
    }

    // 做法: 主要就是用PriorityQueue来排列每一个digit的大小和位置, 尽量把最大的且跟当前digit距离最远的digit和当前的digit互换
    // Runtime: O(n^2log(n)), Space: O(nlog(n))
    public int maximumSwap(int num) {
        if(num <= 10) return num;
        String s = String.valueOf(num);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if(a[0] == b[0]) return b[1] - a[1];
            return b[0] - a[0];
        });
        for(int i = 1; i < s.length(); i ++){
            Character cur = s.charAt(i);
            int val = Character.getNumericValue(cur);
            pq.offer(new int[]{val, i});
        }
        StringBuilder sb = new StringBuilder(s);
        for(int i = 0; i < s.length(); i ++){
            Character cur = s.charAt(i);
            int val = Character.getNumericValue(cur);
            if(pq.peek()[0] > val && pq.peek()[1] > i){
                int[] temp = pq.poll();
                int pos = temp[1], otherVal = temp[0];
                sb.replace(i, i + 1, String.valueOf(otherVal));
                sb.replace(pos, pos + 1, String.valueOf(cur));
                break;
            }
            int[] curDigit = new int[]{val, i};
            PriorityQueue<int[]> temp = new PriorityQueue<>((a, b) -> {
                if(a[0] == b[0]) return b[1] - a[1];
                return b[0] - a[0];
            });
            while(!pq.isEmpty()){
                int[] a = pq.poll();
                if(a[0] == curDigit[0] && a[1] == curDigit[1]) continue;
                temp.offer(a);
            }
            pq = temp;
        }
        return Integer.parseInt(sb.toString());
    }





    //做法： 第二遍没有每次都把当前的digit poll()出来， 但没过

    public int maximumSwap_2(int num) {
        if(num <= 10) return num;
        String s = String.valueOf(num);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if(a[0] == b[0]) return b[1] - a[1];
            return b[0] - a[0];
        });
        for(int i = 1; i < s.length(); i ++){
            Character cur = s.charAt(i);
            int val = Character.getNumericValue(cur);
            pq.offer(new int[]{val, i});
        }
        StringBuilder sb = new StringBuilder(s);
        for(int i = 0; i < s.length(); i ++){
            if(pq.isEmpty()) break;
            int val = s.charAt(i) - '0';
            int[] top = pq.peek();
            while(top[0] <= val && top[1] <= i){
                pq.poll();
                if(pq.isEmpty()){
                    break;
                }
                top = pq.peek();
                if(top[0] > val && top[1] > i){
                    int pos = top[1], otherVal = top[0];
                    sb.replace(i, i + 1, String.valueOf(otherVal));
                    sb.replace(pos, pos + 1, String.valueOf(val));
                    return Integer.parseInt(sb.toString());
                }
            }
        }
        return Integer.parseInt(sb.toString());
    }
}
