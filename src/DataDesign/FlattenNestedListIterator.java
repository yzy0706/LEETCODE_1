package DataDesign;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FlattenNestedListIterator {
    Queue<Integer> q;
    List<NestedInteger> list;
    int pos;

    public void NestedIterator(List<NestedInteger> nestedList) {
        pos = 0;
        q = new LinkedList<>();
        list = nestedList;
    }

    public Integer next(){
        if(!hasNext()) return null;
        if(!q.isEmpty()) return q.poll();
        NestedInteger next = list.get(pos ++);
        if(next.isInteger()) return next.getInteger();
        dfs(next);
        if(q.isEmpty()) return next();
        return q.poll();
    }

    public boolean hasNext() {
        return !(q.isEmpty() && pos == list.size());
    }

    public void dfs(NestedInteger n){
        for(NestedInteger child : n.getList()){
            if(child.isInteger()) q.offer(child.getInteger());
            else dfs(child);
        }
    }
}
