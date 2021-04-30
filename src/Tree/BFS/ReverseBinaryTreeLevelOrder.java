package Tree.BFS;

import java.util.*;

public class ReverseBinaryTreeLevelOrder {
    // 做法： 用bfs浏览每一层， stack存储每一层浏览过的TreeNode们的val, 然后再把stack里面的list一个个pop出来放到res里
    // Runtime: O(n), Space: O(n)
    public List<List<Integer>> ReverseBinaryTreeLevelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        Stack<List<Integer>> stack = new Stack<>();
        List<List<Integer>> ans =  new ArrayList<>();
        if(root != null) queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            List<Integer> temp = new ArrayList<>();
            for(int i = 0 ;i < size  ; i++){
                TreeNode cur = queue.poll();
                temp.add(cur.val);
                if(cur.left != null) queue.offer(cur.left);
                if(cur.right != null) queue.offer(cur.right);
            }
            stack.push(temp);
        }

        while(!stack.isEmpty()){
            ans.add(stack.pop());
        }

        return ans;
    }


}
