package Tree.countNumbersOfUniqueTrees;

public class UniqueBinaryTrees {
    // 做法：用G(i）来代表i个数字能组成的binary tree的数量， G(0) = 1, G(1) = 1, 而我们用forloop浏览从2到n的数字为root
    // 组成binary tree的时候我们要forloop一个j从1到i， 也就是当前这个树有n的大小， j从1开始到i都有可能是这个树的头，
    // 那么G(i) += G(j-1) * G(i-j), 也就是把大小为i的subtree的所有可能都从下往上相乘叠加了， 所以最后return G(n)就是大小为n的所有可能
    // Runtime: O(n^2), 因为每次有两个for loop, space: O(n)
    public int numTrees(int n) {
        int[] G = new int[n+1];
        G[0] = 1;
        G[1] = 1;


        //这是C(j, i)的求法
        for(int i = 2; i <= n; i++){
            for(int j = 1; j <= i; j++){
                G[i] += G[j - 1] * G[i - j]; //以j为root， 大小为[j-1], [i-j]的subtree个数叠加在G[i]上
            }
        }

        return G[n];
    }
}
