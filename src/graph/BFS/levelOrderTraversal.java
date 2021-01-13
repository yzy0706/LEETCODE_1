package graph.BFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.swing.tree.TreeNode;

public class levelOrderTraversal {
    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        if(root != null) queue.offer(root);
        List<List<Integer>> ans = new ArrayList<>();
        while(!queue.isEmpty()){

            List<Integer> temp = new ArrayList<>();
            int size = queue.size();
            for(int i = 0; i < queue.size() ; i++){
                TreeNode cur = queue.poll();
                temp.add(cur.val);
                if (cur.left != null) queue.offer(root.left);
                if (cur.right != null) queue.offer(root.right);
            }
            ans.add(temp);

        }
        return ans;
    }
}
