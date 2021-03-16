package Sort.constructComparator;

import java.util.Arrays;

public class StoneGameVI {
    // 做法: 这题主要是跟strategy有关, 我们怎么去sort这些 aliceValues 和 bobValues
    // 1. 因为对于Alice来说, 假设当前第i块石头对于她的价值是a, 对于bob的价值是b, 因为她想拿到对于她价值高的东西,
    // 但她也不想让bob拿到对于bob价值高的物品, 所以对于她来说如果她拿到了这个物品就是价值a, b拿到就是价值 -b, 所以这个东西总共的价值是 a+b
    // 2. 所以对于所有的物品, 我们建立一个valDiff = new int[n][3], valDiff[i] = new int[]{aliceValues[i] + bobValues[i], aliceValues[i], bobValues[i]}, 那么我们现在再对于这些物品的价值根据 a + b 从大到小排序
    // 3. 排序完成以后, Alice第一个选, 她肯定优先选a+b高的, 因为她不能让能让b得分高的东西被b拿走, 她不能只管a, 所以如果 i % 2 == 0, Alice就会去选当前最大的AliceScore += valDiff[i][1], 我们用两个int记录两个人的score, 最后再去比较谁大谁小就行

    // Runtime:  O(nlogn) , Space: O(n)
    public int stoneGameVI(int[] aliceValues, int[] bobValues) {
        int n = aliceValues.length;
        int[][] valDiff = new int[n][3];
        for(int i = 0; i < n; i++){
            valDiff[i] = new int[]{aliceValues[i] + bobValues[i], aliceValues[i], bobValues[i]};
        }
        Arrays.sort(valDiff, (a, b) -> b[0] - a[0]);
        int AliceScore = 0, BobScore = 0;
        for(int i = 0; i < n; i++){
            if(i % 2 == 0) AliceScore += valDiff[i][1];
            else BobScore += valDiff[i][2];
        }
        return Integer.compare(AliceScore, BobScore);
    }
}
