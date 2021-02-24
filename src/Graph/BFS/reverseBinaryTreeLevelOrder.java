package Graph.BFS;

public class reverseBinaryTreeLevelOrder {

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        Deque<List<Integer>> stack = new ArrayDeque<>();
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
