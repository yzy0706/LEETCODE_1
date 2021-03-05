package Tree.DFS.depth;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class DiameterOfNChildrenTree {
    //做法: 第一遍用pq来做的, 稍微有点慢, 基本跟543 diameter of binary tree差不多, 只是因为不是binary tree所以要用priority queue找出最大的两个长度再相加就是当前可能的最长长度, 如果只有一个child那就不用再相加了
    // Runtime: O(V+E), Space: O(E), E就是每个Node有几个child

    int res = 0;

    public int diameter(Node root) {
        dfs(root);
        return res;
    }


    public int dfs(Node root) {
        if (root == null) return 0;
        Queue<Integer> pq = new PriorityQueue<>((a, b) -> (b - a));
        for (int i = 0; i < root.children.size(); i++) {
            Node child1 = root.children.get(i);
            int depth = dfs(child1);
            pq.offer(depth);
        }
        if (pq.isEmpty()) return 1;
        int maxDepth = pq.poll();
        res = pq.isEmpty() ? Math.max(res, maxDepth) : Math.max(res, maxDepth + pq.poll());
        return maxDepth + 1;
    }


    class Node {
        public int val;
        public List<Node> children;


        public Node() {
            children = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            children = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _children) {
            val = _val;
            children = _children;
        }
    }
}
