package Graph.deepCopy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CloneGraph {
    // 做法: 这题是个DFS做法
    // 1. 建立一个公用的map copied记录被克隆的node的val和对应的克隆产物node
    // 2. 建立一个DFS的helper来克隆每一个node
    //      a. 如果node == null, 证明在copy一个空的node, 证明到底了, return null;
    //      b. 如果map copied里面有node.val, 证明当前这个node作为前面某一个node的neighbor已经被copy过了, return copied.get(node.val); 也就是值和node一样的克隆node
    //      c. 新建一个node copy, 在copied里面放入当前node.val和对应的克隆node copy
    //      d. 根据当前node.neighbors, 也就是所有的邻居做dfs, 把每一个邻居的克隆产物clone(neighbor)加到当前node.neighbors上来

    // Runtime: O(V+E), Space: O(V+E)


    // 阻止多余recursion的做法
    HashMap<Integer, Node> copied = new HashMap<>();

    public Node cloneGraph(Node node) {
        return clone(node);
    }

    private Node clone(Node node){
        if(node == null) return null;
        // if(copied.containsKey(node.val)) return copied.get(node.val);
        Node copy = new Node(node.val, new ArrayList<Node>());
        copied.put(copy.val, copy);
        for(Node neighbor : node.neighbors){
            if(copied.containsKey(neighbor.val)) copy.neighbors.add(copied.get(neighbor.val));
            else copy.neighbors.add(clone(neighbor));
        }
        return copy;
    }









    // 经典recursion做法
    public Node cloneGraph_2(Node node) {
        return clone(node);
    }

    private Node clone_2(Node node){
        if(node == null) return null;
         if(copied.containsKey(node.val)) return copied.get(node.val); //也可以在下一个recursion开始前return已经copy的node
        Node copy = new Node(node.val, new ArrayList<Node>());
        copied.put(copy.val, copy);
        for(Node neighbor : node.neighbors){
            copy.neighbors.add(clone(neighbor));
        }
        return copy;
    }





    class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
}
