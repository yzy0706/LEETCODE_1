package graph.BFS.topologicalSort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class courseSchedule {
    //主要就是加了个new int[] indegree; 先用indegree记录所有课程的prerequisites数量（入数）， 然后再用一个forloop
    //去找出所有prerequisite是0的课程的名字，也就是数字， 然后都加入到bfs的一个queue里面； bfs的逻辑就是每一个queue里面的int课程
    //被poll()出来代表这这门课被上了，那么用forloop找出所有要求他是prerequisite的课并且indegree[toBeTaken]--,如果indegree变为0那么他也就
    //被加入到bfs里了
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if(prerequisites.length < 1) return true;
        Map<Integer, List<Integer>> map = new HashMap<>();
        int[] indegree = new int[numCourses];
        for(int i = 0; i < prerequisites.length; i++){
            if(!map.containsKey(prerequisites[i][1])) map.put(prerequisites[i][1], new ArrayList<>());
            map.get(prerequisites[i][1]).add(prerequisites[i][0]);
            indegree[prerequisites[i][0]]++;
        }

        Queue<Integer> bfs = new LinkedList<Integer>();
        for(int i = 0; i < indegree.length; i++ ){
            if(indegree[i] == 0) bfs.offer(i);
        }
        int numsTaken = 0;

        while(!bfs.isEmpty()){
            int courseTaking = bfs.poll();
            numsTaken++;
            if(!map.containsKey(courseTaking)){
                continue;
            }
            for(int i : map.get(courseTaking)){
                int toBeTaken = i;
                indegree[toBeTaken]--;
                if(indegree[toBeTaken] == 0){
                    bfs.offer(toBeTaken);
                }
            }
        }
        if(numsTaken == numCourses) return true;
        return false;
    }
}
