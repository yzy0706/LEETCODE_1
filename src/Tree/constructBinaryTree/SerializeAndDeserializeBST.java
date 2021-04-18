package Tree.constructBinaryTree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class SerializeAndDeserializeBST {
// 做法: 这种其实就是construct bst的题, 但跟之前那些preorder + inorder的不一样, 这里因为每一个treenode的左子树的所有值都比它大, 右子树的所有值都比它小, 我们是根据这个判断方法去寻找它左右子树的, 之前在preorder + inorder的题里面是通过左右子树的大小在preorder上找到当前树的root再进行dfs
// 现在这里只说deserialize的流程, 因为serialize就是简单的preorder, 每一次加上一个","在StringBuilder里:
// 1. 首先我们得到的是一个String data, 如果data是空的那么我们直接return null;
// 2. 我们用data.split(",")得到一个String[], 然后用Arrays.asList(String[] l);可以变成一个list, 然后就可以直接拿这个list的内容建立一个queue了
// 3. 我们建立一个dfs的helper construct(Queue<String> queue, int low, int high), 第一次call它是 construct(queue, 0, Integer.MAX_VALUE);
//  在construct helper里:
//      a.  极限条件是 if(queue.isEmpty()) return null;
//      b.  把当前queue的最前面那个值peek()看一下, 如果 topVal不在[low, high]的范围以内, 我们直接return null;
//      c.  能到这一步就是在极限范围以内,那么queue.poll(). 因为知道当前这个值的TreNode右子树都是比它val大的, 左子树都是比它值小的, 所以建立当前TreeNode cur
//          然后就是正常的construct一个TreeNode的过程, 以左右子树val的大小范围为限制, cur.left = construct(queue, low, topVal); cur.right = contruct(queue, topVal, high); 然后return cur; 就形成了一个recursion从底下最下面的树到最上面的层层叠加

// Runtime: O(n), Space: O(n)

        StringBuilder sb;

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            sb = new StringBuilder();
            preorder(root);
            return sb.toString();
        }

        public void preorder(TreeNode root){
            if(root == null) return;
            sb.append(root.val);
            sb.append(",");
            preorder(root.left);
            preorder(root.right);
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data){
            if(data.length() < 1) return null;
            Queue<String> queue = new LinkedList<>(Arrays.asList(data.split(",")));
            return construct(queue, 0, Integer.MAX_VALUE);
        }

        public TreeNode construct(Queue<String> queue, int low, int high){
            if(queue.isEmpty()) return null;
            String top = queue.peek();
            int topVal = Integer.parseInt(top);
            if(topVal < low || topVal > high) return null;
            queue.poll();
            TreeNode cur = new TreeNode(topVal);
            cur.left = construct(queue, low, topVal);
            cur.right = construct(queue, topVal, high);
            return cur;
        }
}

