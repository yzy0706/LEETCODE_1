package DynamicProgramming.accumulate;

public class DailyTemperatures {
    //做法: 用了一个类似dp的做法, 因为res上的每个位置的每个数正好就是下一个比他大的数跟他的距离
    // 1. 从倒数第二个往左边开始forloop, 倒数第一个肯定没有比他大的值, 所以res[T.length - 1] = 0; 跟dp一样作为初始值
    // 2. 从倒数第二个开始, 每个位置的nearst都初设为 i+1,
    //    用whileloop检查 T[nearst] <= T[i]的情况什么时候结束, nearst每次更新为 nearst += res[nearst];, 就可以直接跳跃到下一个比他大的地方, 就省了检查比他小的数;  如果res[nearst] == 0;代表接下来也没有比nearst这个位置更大的数了, 直接break跳出whileloop
    //    如果出了whileloop nearst的位置还是i+1, 如果T[nearst] <= T[i]的话就只是我一开始预设的一个参数, 并不是真的比T[i]大, 所以res[i] = 0;
    //    否则res[i] = nearst - i; 就是最近的比i大的位置 - i的位置

    //Runtime: 如果没法跳过比nearst小的数的话, 每次都要从nearst开始一个个检查看有没有比T[i]大, i又要从倒数第二个一直浏览到第一个, 所以是O(n^2),  Space: O(n), 只用了一个res来储存

    public int[] dailyTemperatures(int[] T) {
        int[] res = new int[T.length];
        res[T.length - 1] = 0;

        for(int i = T.length - 2; i >= 0; i--){
            int nearst = i+1;
            while(T[nearst] <= T[i]){
                nearst += res[nearst];
                if(res[nearst] == 0){
                    break;
                }
            }
            if(T[nearst] > T[i]){
                res[i] = nearst - i;
            }else{
                res[i] = 0;
            }
        }

        return res;
    }
}
