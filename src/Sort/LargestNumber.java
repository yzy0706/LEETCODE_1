package Sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LargestNumber {
    //做法: 这题直接把所有的integer转成string, 然后建一个comparator return (o2+o1).compareTo(o1+o2);就行
    //Runtime: O(nlog(n)), 每次排出来一个sort好的就是log(n), space: O(1)
    public String largestNumber(int[] nums) {
        if(nums.length < 1) return "";
        List<String> numsString = new ArrayList<>();
        for(int i : nums){
            numsString.add(Integer.toString(i));
        }
        Comparator<String> com = (o1, o2) -> {
            String s1 = o1 + o2;
            String s2 = o2 + o1;
            return s2.compareTo(s1);
        };
        numsString.sort(com);
        StringBuilder sb = new StringBuilder();
        for(String s : numsString){
            sb.append(s);
        }
        if(sb.charAt(0) == '0') return "0";
        return sb.toString();
    }
}
