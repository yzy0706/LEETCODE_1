package Math;

import java.util.ArrayList;
import java.util.List;

public class FizzBuzz {
    // 做法: 简单的把所有数字浏览一遍, 如果整除15就是整除两个数
    // Runtime: O(n), Space: O(1)
    public List<String> fizzBuzz(int n) {
        List<String> res = new ArrayList<>();
        for(int i = 0; i < n; i++){
            int num = i + 1;
            if(num % 15 == 0){
                res.add("FizzBuzz");
            }
            else if(num % 5 == 0){
                res.add("Buzz");
            }
            else if(num % 3 == 0){
                res.add("Fizz");
            }
            else{
                res.add(String.valueOf(num));
            }
        }
        return res;

    }


    // follow up做法： 假如不用%的话可以用三个parameter来记录3， 5， 15当前是不是走完了n个轮次， 如果是的话就是整除
    // Runtime: O(n), Space: O(1)

    public List<String> fizzBuzz_noMath(int n) {
        List<String> ret = new ArrayList<String>(n);
        for(int i=1,fizz=0,buzz=0;i<=n ;i++){
            fizz++;
            buzz++;
            if(fizz==3 && buzz==5){
                ret.add("FizzBuzz");
                fizz=0;
                buzz=0;
            }else if(fizz==3){
                ret.add("Fizz");
                fizz=0;
            }else if(buzz==5){
                ret.add("Buzz");
                buzz=0;
            }else{
                ret.add(String.valueOf(i));
            }
        }
        return ret;
    }
}
