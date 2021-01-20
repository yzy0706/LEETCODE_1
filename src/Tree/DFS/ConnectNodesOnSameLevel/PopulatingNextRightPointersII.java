package Tree.DFS.ConnectNodesOnSameLevel;

public class PopulatingNextRightPointersII {
    class Node {
        public int val;
        public  Node left;
        public  Node right;
        public  Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val,  Node _left,  Node _right,  Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

}
    //做法: 这个题主要跟之前那个是一个解法, 但因为不是完美的平衡树, 我们不是先去判断root.right是不是null, 而是先去判断root.left是不是null, 然后再去讨论如果root.left不是null的话, root.right是不是null的情况; 反之, 如果root.left是null,
    //root.right也要连接下一个neighbor, 在这里neighbour也不再一定是root.next.left, 而是要通过一个helper来找到最近的下一个邻居给root.left或者root.right连
    //Runtime:  我们每一个node都要浏览, 而且还要浏览同层的最近的一个邻居, 最坏的情况是O(N^2), spcae是O(1)
    class Solution {
        public Node connect(Node root) {
            modify(root);
            return root;
        }

        private void modify(Node root){
            if(root != null){

                if(root.left != null){ //如果左叶子不是null
                    if(root.right != null){ //假设左右叶子都在
                        root.left.next = root.right; //连接左右叶子,
                    }
                    else{ //如果只有左叶子没有右叶子
                        root.left.next = findNeighbour(root); //连接做叶子和下一个节点
                    }
                }

                if(root.right != null){ //如果right不是null的话把后面的也要连上, 在这里不管left是不是null右叶子都要跟下一个连上
                    root.right.next = findNeighbour(root);
                }

                modify(root.right);
                modify(root.left);
            }
        }

        private Node findNeighbour(Node root){
            if (root.next != null){
                if(root.next.left != null) return root.next.left;
                else if(root.next.right != null) return root.next.right;
                else return findNeighbour(root.next);
            }
            return null;
        }


}
