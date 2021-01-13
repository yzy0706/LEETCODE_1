package greedy;

public class removeKDigits {
    //看了答案自己写的但没过
    public String removeKdigits(String num, int k) {
        Deque<Character> stack = new ArrayDeque<>();

        for(char c : num.toCharArray()){
            while(stack.size() > 0 && k > 0 && stack.peek() > c){
                stack.pop();
                k --;
            }
            stack.push(c);
        }

        StringBuilder res = new StringBuilder();
        boolean zero = true;

        while(!stack.isEmpty()){
            Character cur = stack.pop();
            if(zero && cur == '0') continue;
            zero = false;
            res.append(cur);
        }
        res.reverse();
        if(res.length() == 0) return "0";
        return res.toString();
    }
}
