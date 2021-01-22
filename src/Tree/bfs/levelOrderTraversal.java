package Tree.bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class levelOrderTraversal {
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


    //第一种解法
    // 做法: levelOrder肯定用queue了, 但这题我们需要记录每一层的数据,而不是光浏览就行,
//(注意)所以在这种情况下建议用两个变量thisLevel和nextlevel来记录每一层还剩下几个node没检查, 和下一层有多少node等待
//检查,在每次poll()出来一个node以后thisLevel--, nextLevel根据子节点该加的加,  如果在本次的检查的最后thisLevel ==
//0了就把当前的temp加到res里面去, 并且把thisLevel和nextLevel置换一下开始下一层的浏览, 总结来说就是拿thisLevel当作
//一个记录该层有没有走完的工具

    //Runtime: O(n), space: O(k), queue的大小
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int thisLevel = 1, nextLevel = 0;
        List<Integer> tempL = new ArrayList<>();

        while(!queue.isEmpty()){

            TreeNode cur = queue.poll();
            thisLevel--;
            tempL.add(cur.val);

            if(cur.left != null){
                queue.offer(cur.left);
                nextLevel++;
            }

            if(cur.right != null){
                queue.offer(cur.right);
                nextLevel++;
            }

            if(thisLevel == 0){
                res.add(tempL);
                tempL = new ArrayList<>();
                thisLevel = nextLevel;
                nextLevel = 0;
            }
        }

        return res;

    }



    //第二种解法， 每次直接记录queue的size然后forloop一遍就行

    public List<List<Integer>> levelOrder_forloop(TreeNode root) {
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
