package Graph.DFS.path;

import Tree.DFS.TreeNode;
import Tree.DFS.path.PathSum;

import java.util.ArrayList;
import java.util.List;

public class PathSumII {
        class TreeNode {
            int val;
            PathSum.TreeNode left;
            PathSum.TreeNode right;

            TreeNode() {
            }

            TreeNode(int val) {
                this.val = val;
            }

            TreeNode(int val, PathSum.TreeNode left, PathSum.TreeNode right) {
                this.val = val;
                this.left = left;
                this.right = right;
            }

        //做法: 这题因为recursion里面是一个List<Integer> temp, 他是个引用传递的类型, 所以在recursion里所有的temp都会指向一个最初的temp,
        // 所以在每一次helper结束以后我们必须跟backtrack一样删掉当前的尾巴再return到上一行, 如果变的只是一个int sum的话是是不用在方程的末尾再+=被减去的root.val的,
        // 因为是参数传递
        //Runtime: O(N), space: O(N)
        List<List<Integer>> res = new ArrayList<>();
        public List<List<Integer>> pathSum(TreeNode root, int sum) {
            expand(root, sum, new ArrayList<>());
            return res;
        }

        public void expand(TreeNode root, int sum, List<Integer> temp){
            if(root == null) return;
            temp.add(root.val);
            if(root.left == null && root.right == null && sum - root.val == 0){
                res.add(new ArrayList<>(temp));
            }
            else {
                expand(root.left, sum - root.val, temp);
                expand(root.right, sum - root.val, temp);
            }
            temp.remove(temp.size()-1);
        }
    }
}
