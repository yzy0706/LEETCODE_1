package Math;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class RomanToInteger {
    //做法: discussion的解法是用int[] nums来记录每一个character对应的数, (注意switch格式的用法),
    // 然后再去比较最后一位数之前, 每一位的数和后面那个数的大小之比, 如果小就减去当前的数, 否则加上当前的数
    //Runtime: O(n), Space: O(n)

    public int romanToInt_arr(String s){
        if(s.length() < 1) return 0;
        int res = 0;
        int[] nums = new int[s.length()];
        int pos = 0;
        for(Character c : s.toCharArray()){
            switch(c){
                case 'M':
                    nums[pos++]=1000;
                    break;
                case 'D':
                    nums[pos++]=500;
                    break;
                case 'C':
                    nums[pos++]=100;
                    break;
                case 'L':
                    nums[pos++]=50;
                    break;
                case 'X' :
                    nums[pos++]=10;
                    break;
                case 'V':
                    nums[pos++]=5;
                    break;
                case 'I':
                    nums[pos++]=1;
                    break;

            }
        }
        for(int i = 0; i < nums.length - 1; i++){
            if(nums[i] < nums[i+1]) res -= nums[i];
            else res += nums[i];
        }
        return res + nums[nums.length-1];
    }





    //做法: 用了一个stack来检查前面的那个字符是不是比当前的这个字符对应的数字小, 是的话就要考虑4, 9, 40, 90等情况
    //Runtime: O(n), Space: O(n)

    public int romanToInt_reviewed(String s){
        if(s.length() < 1) return 0;
        Map<String,Integer> map = new HashMap<>();
        int[] nums = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
        String[] strs = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        int res = 0;
        for(int i = 0 ;i < nums.length ; i++){
            map.put(strs[i],nums[i]);
        }
        Stack<Character> stack = new Stack<>();
        for(Character c : s.toCharArray()){
            if(!stack.isEmpty()){
                Character last = stack.pop();
                if(map.get(last.toString()) < map.get(c.toString())){
                    res += map.get(c.toString()) - map.get(last.toString());
                    continue;
                }
                else{
                    res += map.get(last.toString());
                }
            }
            stack.push(c);
        }
        if(!stack.isEmpty()) res += map.get(stack.pop().toString());
        return res;
    }





    public int romanToInt(String s) {
        Character[] chars={'M','D','C','L','X','V','I'};
        int[] nums={1000,500,100,50,10,5,1};
        int res = 0;
        Map<Character,Integer> map= new HashMap<>();

        for(int i=0;i<chars.length;i++){
            map.put(chars[i],nums[i]);
        }

        res=map.get(s.charAt(s.length()-1));

        for(int j=0;j<s.length()-1;j++){
            if(map.get(s.charAt(j))<map.get(s.charAt(j+1))){
                res -= (map.get(s.charAt(j)));
            }
            else{
                res +=  map.get(s.charAt(j));
            }
        }
        return res;
    }
}
