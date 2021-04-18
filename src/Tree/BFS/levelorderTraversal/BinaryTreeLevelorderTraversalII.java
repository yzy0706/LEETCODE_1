package Tree.BFS.levelorderTraversal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class BinaryTreeLevelorderTraversalII {
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

    //做法: 直接用正常queue的做法做, 最后再倒过来因为要求从底下到顶上顺序呈现每一层
    //Runtime: O(n), space: O(k)
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if(root == null) return new ArrayList<>();
        List<List<Integer>> reverse = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            List<Integer> thisLevel = new ArrayList<>();
            for(int i = 0; i < size; i++){
                TreeNode cur = queue.poll();
                thisLevel.add(cur.val);
                if(cur.left != null) queue.offer(cur.left);
                if(cur.right != null) queue.offer(cur.right);
            }
            reverse.add(thisLevel);
        }
        List<List<Integer>> res = new ArrayList<>();
        for(int i = reverse.size()-1; i >= 0; i--){
            res.add(reverse.get(i));
        }
        return res;
    }
}
