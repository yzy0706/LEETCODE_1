package Stack.reconstruct;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class SimplifyPath {
    //做法: 这题的主要做法就是先把path用“/”split成各个String s (s有可能是“”),
    //然后我们建立一个Set<String> skip来记录碰到了应该跳过的两个String: ".", "";
    //然后我们浏览被Split了的各个String s,
    //1. 如果s是 “..”, 直接stack.pop(), 代表上了一层directory
    //2. 如果s是Set<String> skip里的其中一个, 那么直接跳过;  如果s不属于skip里的, 那么我们stack.push(s);
    //最后我们再根据stack里剩下的元素从右到左排列res: res = "/" + stack.pop() + res;

    //Runtime: O(n), space: O(n);
    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();
        Set<String> skip = new HashSet<>(Arrays.asList(".",  ""));
        //如果被“/”split的部分是 “.”, “..”, 或者“”,
        //就直接跳过, 也不要在stack里push或者pop任何东西
        for(String s : path.split("/")){
            if(s.equals("..")){
                if(!stack.isEmpty()){
                    stack.pop();
                }
            }
            else{
                if(!skip.contains(s)){
                    stack.push(s);
                }
            }
        }
        String res = "";
        while(!stack.isEmpty()){
            String cur = stack.pop();
            res = "/" + cur  + res;
        }
        return res.length() == 0 ? "/" : res;
    }
}
