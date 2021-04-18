package Graph.unionFind;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ReorderRoutesToMakeAllPathTo0 {
    // 做法: 这里是用了一个类似于bfs的做法, 又类似于unf, 只是把当前的connections来代替了queue的作用, 储存所有暂时连不到0的connection
    // 1. 建立一个link0 = new HashSet<>(); 来记录所有能连到0的城市, 首先把0自己放进去
    // 2. 只要connections的长度还>0, 我们就可以一直做connection的减法
    //      a. 如果当前connection[1]在link0里, 那么当前connection[0]也可以到0, 直接加到link0里去
    //      b. 如果当前connection[0]在link0里, 上面已经判断了connection[1]不在link0里了, 当前方向调转, res++;
    //      c. 如果当前connection两个城市都到不了0, 那么先不要急着去解决, 放到unused里去准备建立新的connection

    // Runtime: 如果每次只能删一个connection那么就是正好O(n^2), Space: O(n)
    public int minReorder_bfs(int n, int[][] connections) {
        HashSet<Integer> link0 = new HashSet<>();
        link0.add(0);
        int res = 0;
        while(connections.length > 0){
            List<int[]> unused = new ArrayList<>();
            for(int[] connection : connections){
                if(link0.contains(connection[1])) link0.add(connection[0]);
                else if(link0.contains(connection[0])){
                    link0.add(connection[1]);
                    res ++;
                }
                else unused.add(connection);
            }
            int[][] temp = new int[unused.size()][2];
            for(int i = 0; i < unused.size(); i++){
                temp[i] = unused.get(i);
            }
            connections = temp;
        }
        return res;
    }





    //又根据这个bfs改了一遍unf的写法， 还是不对
    public int minReorder_unf2(int n, int[][] connections) {
        UNF unf = new UNF(n);
        for(int[] connection : connections){
            unf.merge(connection[0], connection[1]);
        }
        int res = 0;
        unf.parents[0] = 0;
        while(connections.length > 0){
            List<int[]> unused = new ArrayList<>();
            for(int[] connection : connections){
                int start = connection[0], dest = connection[1];
                if(unf.find(dest) == 0){
                    unf.merge(start, dest);
                }
                else if(unf.find(start) == 0){
                    unf.merge(dest, start);
                    res ++;
                }
                else{
                    unused.add(connection);
                }
            }
            int[][] temp = new int[unused.size()][2];
            for(int i = 0; i < unused.size(); i++){
                temp[i] = unused.get(i);
            }
            connections = temp;
        }
        return res;
    }

    class UNF{
        int[] parents;

        public UNF(int n){
            this.parents = new int[n];
            for(int i = 0; i < n; i ++){
                parents[i] = i;
            }
        }

        public int find(int x){
            while(x != parents[x]){
                parents[x] = parents[parents[x]];
                x = parents[x];
            }
            return x;
        }

        public void merge(int a, int b){
            int fa = find(a), fb = find(b);
            if(fa != fb){
                parents[fa] = parents[fb];
            }
        }
    }





    // 第一遍用unf写的好像不对
    public int minReorder(int n, int[][] connections) {
        UNF unf = new UNF(n);
        for(int[] connection : connections){
            unf.merge(connection[0], connection[1]);
        }
        System.out.println(unf.find(4));
        int res = 0;

        for(int[] connection : connections){
            int start = connection[0], dest = connection[1];
            if(start == 0){
                unf.parents[0] = 0;
                unf.merge(dest, start);
                res ++;
            }
            else if(unf.find(start) != 0 || unf.find(dest) != 0){
                unf.merge(dest, start);
                res ++;
            }
        }
        return res;
    }
}
