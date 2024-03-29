package Tree.BFS.levelorderTraversal;

import java.util.*;

public class ZigZagTraversal {


    //做法: 自己复习了一遍, 加入还是正常的从左到右加入, 只是当前这一层如果需要反过来就把当前存储这一层的数据的list反过来一下
    //Runtime: O(n), Space: O(n)
    public List<List<Integer>> zigzagLevelOrder_reviewed(TreeNode root){
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        Boolean rev = false;
        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            List<Integer> curLevel = new ArrayList<>();
            for(int i = 0; i < size; i++){
                TreeNode cur = queue.poll();
                curLevel.add(cur.val);
                if(cur.left != null) queue.offer(cur.left);
                if(cur.right != null) queue.offer(cur.right);
            }
            if(rev) Collections.reverse(curLevel);
            rev = !rev;
            res.add(curLevel);
        }
        return res;
    }





    //正确的解法如果用queue的话应该是添加node的方向其实都是一样的，然后根据是否reverse来改变现有的curList里面int的数值就好
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        boolean reverse = true;
        queue.offer(root);

        while(!queue.isEmpty()){

            int size = queue.size();
            List<Integer> curList = new ArrayList<>();

            for(int i = 0; i < size; i++){
                TreeNode cur = queue.poll();
                curList.add(cur.val);
                if(cur.right != null) queue.offer(cur.right);
                if(cur.left != null) queue.offer(cur.left);
            }

            if(!reverse) res.add(curList);
            else{
                Collections.reverse(curList);
                res.add(curList);
            }

            reverse = !reverse;

        }

        return res;

    }


    //第二遍自己写的
    public List<List<Integer>> zigzagLevelOrder_2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        boolean reverse = true;
        queue.offer(root);

        while(!queue.isEmpty()){

            int size = queue.size();
            List<Integer> curList = new ArrayList<>();

            if(reverse){
                for(int i = 0; i < size; i++){
                    TreeNode cur = queue.poll();
                    curList.add(cur.val);
                    if(cur.right != null) queue.offer(cur.right);
                    if(cur.left != null) queue.offer(cur.left);
                }
                reverse = false;
            }

            else if(reverse == false){
                for(int i = 0; i < size; i++){
                    TreeNode cur = queue.poll();
                    curList.add(cur.val);
                    if(cur.left != null) queue.offer(cur.left);
                    if(cur.right != null) queue.offer(cur.right);
                }
                reverse = true;
            }


            res.add(curList);
        }

        return res;

    }




    public List<List<Integer>> zigzagLevelOrder_1(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null) return ans;
        Queue<TreeNode> queue = new LinkedList<>();
        // Deque<TreeNode> stack = new ArrayDeque<>();
        if(root!=null) queue.offer(root);
        Boolean reverse = false;



        while(!queue.isEmpty()){
            int size = queue.size();
            List<Integer> temp = new ArrayList<>();
            for(int i = 0 ; i < size ; i++){
                TreeNode cur = queue.poll();
                temp.add(cur.val);
                if(reverse == false){
                    if(cur.left != null) queue.offer(cur.left);
                    if(cur.right != null) queue.offer(cur.right);
                }
                else if(reverse == true){
                    if(cur.right != null) queue.offer(cur.right);
                    if(cur.left != null) queue.offer(cur.left);
                }
            }
            ans.add(temp);
            // if(reverse==false){
            List<TreeNode> list = new ArrayList<>(queue);
            Collections.reverse(list);
            queue.clear();
            for(int i = 0 ; i < list.size() ; i++)
                queue.offer(list.get(i));
            // }
            reverse = !reverse;
        }
        return ans;

    }
}

