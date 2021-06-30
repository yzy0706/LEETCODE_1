package Temp;


public class SubtreeWithMaximumAverage {

    //做法： 用的是dfs的做法， dfs这个helper从最底层开始一直return new int[]{subtree node num， subtree sum);
    // 然后在每一个node都计算一下当前的subtree的avg是不是比maxAvg大， 随时更新Node res;

    //Runtime: O(n), Space: O(1)

    double maxAvg;
    Node res;
    public Node subtreeWithMaximumAverage(Node root){
        maxAvg = Integer.MIN_VALUE;
        res = new Node();
        if(root == null) return null;
        dfs(root);
        return res;
    }

    public int[] dfs(Node root){
        if(root == null) return new int[]{0, 0};
        int curSum = root.val, curNum = 1;
        for(Node child : root.children){
            int[] childAns = dfs(child);
            curNum += childAns[0]; //先用childAns储存结果， 避免RTE
            curSum += childAns[1];
        }
        if((double)curSum/curNum > maxAvg){
            res = root;
            maxAvg = (double) curSum/ curNum;
        }
        return new int[]{curNum, curSum};
    }



    // 总的来说就是一个dfs的方法， helper是一个return double[3]的一个方程，
    // 这个double[]代表着{这个node包括它自己和下面所有node的sum， 这个node和所有它的subtree里的node个数， 当前自己代表的tree的average}
    // 如果root当前到了null了， 那么直接return new double[]{0.0, 0.0, 0.0}， 否则， 因为这个方程不用考虑root是不是null的情况，那么
    //当前回归的double[0]也就是tree里所有的数字之和应该就是root的val加上dfs(root.left)[1], dfs(root.right)[1]; double[1]也就是tree里所有的node的个数也同理
    //然而double[2]也就是当前maxAverage等于当前sum/cnt，dfs(root.left)[2], dfs(root.right)[2]， 也就是它自己和左右subtree average的最大值
    //Runtime： 每个treeNode都跑到底了， 那么最坏的情况也就是O(n)， space： O(1）



    public double maximumAverageSubtree(TreeNode root) {
        return dfs(root)[2];
    }


    private double[] dfs(TreeNode root){
        if(root == null) return new double[]{0.0, 0.0, 0.0};
        double[] l = dfs(root.left), r = dfs(root.right);
        double sum = (double)root.val + l[0] + r[0];
        double number = 1.0 + l[1] + r[1];
        double maxAverage = Math.max(sum/number, Math.max(l[2], r[2]));
        return new double[]{sum, number, maxAverage};
    }




//    public double maxmumAverageNonbinarySubtree(TreeNode root){
//        return expand(root)[2];
//
//    }


//    public double[] expand(TreeNode root){
//        if(root == null) return new double[]{0.0, 0,0, 0.0};
//        double curSum = (double)root.val;
//        double curNum = 1.0;
//        for(TreeNode son : root.sons){
//            double[] result = expand(son);
//            curSum += result[0];
//            curNum += result[1];
//        }
//        double maxAverage = curSum/ curNum;
//        for(TreeNode son : root.sons){
//            double[] result = expand(son);
//            maxAverage = Math.max(maxAverage, result[2]);
//        }
//        return new double[]{curSum, curNum, maxAverage};
//    }
}
