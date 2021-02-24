package Graph.DFS.inorderTraversal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class BinaryTreeInOrderTraversal {
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


       //做法: 用的是一个stack在stack不是空的或者当前浏览到的不是null的whileloop里来承载所有的root和他们的左儿子, 假设说找到null的话就从stack里找到他的爸爸并且使用他爸, 然后当前浏览的head直接跳到他爸的右儿子, 来浏览完当前他爸的这个subtree
       //Runtime: O(n), Space : 最多也是O(n), 假设只有最底端一个右儿子的情况
       private List<Integer> inorderTraversal(TreeNode root) {
           LinkedList<TreeNode> stack = new LinkedList<>();
           List<Integer> res = new ArrayList<>();
           TreeNode head = root;
           while (!stack.isEmpty() || head != null) {
               if (head != null) {
                   stack.push(head);
                   head = head.left;
               } else {
                   TreeNode cur = stack.pop();
                   res.add(cur.val);
                   // if(cur.right != null) stack.push(cur.right); !这一步是错的, stack里面只装左儿子和root
                   head = cur.right;
               }
           }
           return res;
       }
   }

