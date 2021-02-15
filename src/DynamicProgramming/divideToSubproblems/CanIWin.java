package DynamicProgramming.divideToSubproblems;

import java.util.Arrays;
import java.util.HashMap;

public class CanIWin {

    //做法: 这题主要做法就是用一个只有'1' or '0' 的 char[] strategy = new char[maxChoosableInteger + 1]; 来代表当前status有哪些数被选过了, 哪些没有被选过
    // 1. 先检查如果 desiredTotal <= 0, 那么第一个人肯定赢, return true;
    // 如果 0 + 1 + 2 + ... + maxChoosableInteger < desiredTotal, 代表无论怎样都没法赢, return false;

    // 2. 建立一个 char[] strategy = new char[maxChoosableInteger + 1]; 上面全是'1', 建立一个 HashMap<String, Boolean> statuses 来记录每一个strategy有没有出现过, 能不能实现第一个玩家赢

    //3. 建立helper(char[] strategy, HashMap<Boolean, String> statuses, int curTotal)
    //  a. 如果curTotal <= 0了, 代表当前剩下的需要拿到的值都 <= 0了, 代表之前那个人已经拿到所有的数了
    //  b. 当前 String curStatus = new String(strategy), 如果statuses.containKey(curStatus); 代表当前的情况之前已经出现过了, 直接return statuses.get(curStatus);
    //  c. 建立一个res记录当前这个helper的结果, 从1开始forloop整个char[] strategy, 这代表第二个玩家选择什么:
    // 如果碰到是'1', 也就是没选择过的数的位置, strategy[i] = '0', 如果!(helper(strategy, map, curTotal - i)), 代表第二玩家选i会输, 那么res = true, 当前一号玩家会赢, 如果res = true就直接终止当前的forloop; 否则之后跟backtrack一样, 恢复strategy[i] = '1',  接着进行forloop
    //  d. 结束forloop了就在map里记录当前strategy的结果, 并且return res;

    // Runtime: O(2^n), 一共有2^n种可能, Space: O(2^n), 一直用的就是一个char[]和一个map, map的大小是2^n


    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if(desiredTotal <= 0) return true;

        if(maxChoosableInteger * (maxChoosableInteger + 1)/2 < desiredTotal) return false; //检查所有的Integer的和是不是都小于desiredTotal

        HashMap<String, Boolean> statuses = new HashMap<>();
        char[] strategy = new char[maxChoosableInteger + 1];
        Arrays.fill(strategy, '1');
        return checkStrategy(strategy, statuses, desiredTotal);
    }


    private boolean checkStrategy(char[] strategy, HashMap<String, Boolean> statuses, int curTotal){
        if(curTotal <= 0) return false;

        String curStatus = new String(strategy);
        if(statuses.containsKey(curStatus)) return statuses.get(curStatus);

        Boolean result = false;

        for(int i = 1; i < strategy.length; i++){
            if(strategy[i] == '0') continue;

            strategy[i] = '0'; //试一下现在选i能不能赢, 所以当前的位置变成了 ‘0’
            if(!checkStrategy(strategy, statuses, curTotal - i)){
                result = true;
            }

            strategy[i] = '1'; //又把当前i的strategy恢复成1, 跟backtrack一样, 在最后返回, 去试别的位置可不可以
            if(result) break;
        }

        statuses.put(curStatus, result);
        return result;
    }
}
