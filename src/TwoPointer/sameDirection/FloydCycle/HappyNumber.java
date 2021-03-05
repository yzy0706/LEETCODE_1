package TwoPointer.sameDirection.FloydCycle;

import java.util.HashSet;
import java.util.Set;

public class HappyNumber {
    //做法: 就是还没成环就一直往下用whileloop找, 成环了whileloop就break, 找到和是1了就直接return true, 否则就一直更新这个n
    //Runtime: O(k)? (就是次数? 因为这个runtime真不确定), Space: O(1)

    public boolean isHappy(int n) {
        Set<Integer> visited = new HashSet<>();
        int digitSum = 0;
        while(visited.add(n)){
            while(n > 0){
                int remain = n % 10;
                digitSum += remain * remain;
                n /= 10;
            }
            if(digitSum == 1) return true;
            n = digitSum;
            digitSum = 0;
        }
        return false;
    }
}
