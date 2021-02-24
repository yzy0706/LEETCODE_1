package Graph.DFS.commonAncestors;

import Tree.DFS.commonAncestors.Node;

import java.util.HashSet;
import java.util.Set;

public class LowestCommonAncestorIII {
    //做法: 这题相当于是p、q一起跑, 看看记录里面有没有过他们的祖先
    // 1. 先让p跑到null, 并且用Set<Node> visited每次都记录了p和q走过的所有Node
    // 2. 再让q跑到null, 不管他们是不同的子树跑上来, 还是从p是q的祖宗, q总会经过p走过的路径, 所以如果visited里面有q经过的地方就return q;

    //Runtime: O(n), 因为跑第一个重复的路径的时候就会return, 所以最多跑O(n), Space: O(n)

    public Tree.DFS.commonAncestors.Node lowestCommonAncestor(Tree.DFS.commonAncestors.Node p, Tree.DFS.commonAncestors.Node q) {
        Set<Tree.DFS.commonAncestors.Node> visited = new HashSet<>();

        while(p != null){ //p、q一起运动p到null的距离
            if(visited.contains(p)) return p;
            visited.add(p);
            p = p.parent;

            Tree.DFS.commonAncestors.Node temp = p;
            p = q; //跟q交换
            q = temp;
        }

        while(q != null){
            if(visited.contains(q)) return q; //如果之前浏览过这了证明他们有同一个ancestor
            q = q.parent;
        }

        return null;
    }


    ;
}
