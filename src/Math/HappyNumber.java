package Math;

import java.util.HashSet;
import java.util.Set;

public class HappyNumber {

    //做法: 就是还没成环就一直往下用whileloop找, 成环了whileloop就break, 找到和是1了就直接return true, 否则就一直更新这个n
    //Runtime: O(k)? (就是次数? 因为这个runtime真不确定), Space: O(1)

    public boolean isHappy_4(int n) {
        Set<Integer> visited = new HashSet<>();
        while(visited.add(n)){
            int digitSum = 0;
            while(n > 0){
                int digit = n % 10;
                digitSum += digit * digit;
                n /= 10;
            }
            if(digitSum == 1) return true;
            n = digitSum;
        }
        return false;
    }


    ///自己的解法
    public boolean isHappy_3(int n) {
        if(n == 1) return true;
        if(n == 0) return false;
        Set<Integer> set = new HashSet<>();
        while(set.add(n)){
            // Set<Integer> cur = new HashSet<>();
            int cnt = 1;
            int nextN = 0 ;
            while(Math.pow(10,cnt-1) <= n ){
                int digit = (n % (int)Math.pow(10,cnt)) / (int)Math.pow(10,cnt-1);
                // cur.add(digit);
                nextN += (int)(Math.pow(digit,2));
                // System.out.println(nextN);
                n -= digit * (int)(Math.pow(10,cnt-1));
                cnt++;
            }
            n = nextN;
            // set.add(nextN);
            cnt = 1;

            // sets.add(cur);
            // cur.clear();
        }
        if(n == 1) return true;
        return false;
    }

    //把while条件改了的解法
    public boolean isHappy_2(int n) {
        if(n == 1) return true;
        if(n == 0) return false;
        Set<Integer> set = new HashSet<>();
        while(set.add(n)){
            // Set<Integer> cur = new HashSet<>();
            int cnt = 1;
            int nextN = 0 ;
            while(n > 0 ){
                int digit = (n % (int)Math.pow(10,cnt)) / (int)Math.pow(10,cnt-1);
                // cur.add(digit);
                nextN += (int)(Math.pow(digit,2));
                // System.out.println(nextN);
                n -= digit * (int)(Math.pow(10,cnt-1));
                cnt++;
            }
            n = nextN;
            // set.add(nextN);
            cnt = 1;

            // sets.add(cur);
            // cur.clear();
        }
        if(n == 1) return true;
        return false;
    }


    //加了helper的解法
    public boolean isHappy_1(int n) {
        if(n == 1) return true;
        if(n == 0) return false;
        Set<Integer> set = new HashSet<>();
        while(set.add(n)){
            n = loop(n);
        }
        if(n == 1) return true;
        return false;
    }


    public int loop(int n){
        int nextN = 0;
        while(n > 0){
            int digit = n % 10;
            n /= 10;
            nextN += Math.pow(digit,2);
        }
        return nextN;
    }
}
