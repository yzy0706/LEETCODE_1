package graph.unionFind;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JZ_minimumSpanningTree {
    public class Connection {
    public String city1, city2;
    public int cost;
    public Connection(String city1, String city2, int cost) {
        this.city1 = city1;
        this.city2 = city2;
        this.cost = cost;
   }

   class com implements Comparator<Connection>{
        public int compare(Connection a, Connection b){
            if(a.cost != b.cost) return a.cost - b.cost;
            if(a.city1.equals(b.city1)) return a.city2.compareTo(b.city2);
            return a.city1.compareTo(b.city1);
        }
   }

    public List<Connection> lowestCost(List<Connection> connections) {
        connections.sort(new com());
        Map<String,Integer> map =  new HashMap<>();
        int n = 0;
        for(Connection c : connections){
            if(!map.containsKey(c.city1)) map.put(c.city1,n++);
            if(!map.containsKey(c.city2)) map.put(c.city2,n++);
        }
        int[] father =  new int[n+1];
        List<Connection> results = new ArrayList<>();
        for(Connection c : connections){
            int n1 = map.get(c.city1);
            int n2 = map.get(c.city2);
            int f1 = find(n1,father);
            int f2 = find(n2,father);
            if(f1 != f2) {
                father[f1] = f2;
                results.add(c);
            }
        }
        if(results.size() != n - 1) return new ArrayList<Connection>();
        return results;


    }

    public int find(int n ,int[] father){
        int j;
        j = n ;
        while (father[j] != j){
        j = father[j];
    }
    return  j;
    }
    }
}
