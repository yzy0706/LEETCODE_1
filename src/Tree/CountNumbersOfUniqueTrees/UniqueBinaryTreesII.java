package Tree.CountNumbersOfUniqueTrees;

import java.util.ArrayList;
import java.util.List;

public class UniqueBinaryTreesII {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


    //做法: 这个recursion的解法从1到n做一个recursion: 用i从start到end做forloop, i是当前这个tree的头,
    // 然后leftTrees和rightTrees分别是gen(start, i-1)和gen(i+1, end) 所能找到的左右节点的每一种可能,
    // 所以我们以这两个List<TreeNode>为基础做一个双重的forloop, 就会把左叶子和右叶子的所有可能permutate,
    // 产生当前根节点的所有可能并加到当前的trees也就是当前这个方程能产生的所有可能里去
    //Runtime: 网上说是一共有 (4^n)/((n^(3/2))*sqrt(pi))种tree, 所以看作O(4^n); Space最多是O(n)
    public List<TreeNode> generateTrees_recursion(int n) {
        if(n == 0) return new ArrayList<TreeNode>();
        return genTrees(1, n);
    }


    public List<TreeNode> genTrees(int start, int end){
        List<TreeNode> trees = new ArrayList<>();
        if(start > end){
            trees.add(null);
            return trees;
        }
        if(start == end){
            trees.add(new TreeNode(start));
            return trees;
        }

        for(int i = start; i <= end; i++){//根节点的所有可能

            List<TreeNode> leftTrees = genTrees(start, i-1); //左子树的所有可能
            List<TreeNode> rightTrees = genTrees(i+1, end); //右子树的所有可能

            for(TreeNode left : leftTrees){
                for(TreeNode right: rightTrees){
                    TreeNode head = new TreeNode(i);
                    head.left = left;
                    head.right = right;
                    trees.add(head);
                }
            }
        }
        return trees;

    }












    //第一遍自己写的
    //做法: 这题我准备结合permutation和recursion来做
    //Runtime: 一共有n个head, 每个head下面又会有n-1个叶子节点, 所以是O(n^2), space是O(n)
    private List<TreeNode> generateTrees(int n) {
        if(n == 0) return null;
        TreeNode[] candidates = new TreeNode[n];
        List<TreeNode> res = new ArrayList<>();
        for(int i = 1; i <= n; i++){
            candidates[i] = new TreeNode(i);
        } //find all candidates to be added as leaf

        for(int i = 1; i<= n; i++){
            TreeNode cur = candidates[i];
            candidates[i] = null;
            TreeNode root = new TreeNode(cur.val);
            buildTree(root, candidates, n);
            res.add(root);
            candidates[i] = cur;
        } //做一个permutation

        return res;
    }

    public void buildTree(TreeNode root, TreeNode[] candidates, int n){

    }
}
