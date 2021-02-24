package Graph.unionFind;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EvaluateDivision {
    // 做法: 这题也是用的unf做法, 但是不是用的parents[i]来表示某个点的父亲, 而是用的 Map<String, String> parents;
    //      并且用了一个 Map<String, Double> vals = new HashMap<>(); 来表示各个数的倍数关系, 一开始都是1.0
    // 1. 每次有一对新关系进来, 我们都要add(a), add(b), 再find(a), find(b), 当find的过程中 vals.get(a) *= vals.get(fa);
    // 2. 因为我们已知当前的倍数time, 那么假如当前的关系是merge("b", "c", 3.0), find("b") = "a", vals.put("a", 3.0 * )
    class UNF{
        Map<String, String> parents;
        Map<String, Double> vals = new HashMap<>();

        public UNF(){
            parents = new HashMap<>();
            vals = new HashMap<>();
        }

        public void add(String s){
            if(parents.containsKey(s)) return;
            parents.put(s, s);
            vals.put(s, 1.0);
        }

        public String find(String s){
            String fatherS = parents.getOrDefault(s, s);
            if(fatherS != s){ //如果当前s不是祖宗的话
                String grandFatherS= find(fatherS); // 比如说 s是"c", fs是"b", gfs = find(b) = "a"
                vals.put(s, vals.get(s) * vals.get(fatherS)); // vals里s对应的值更新为他的值 * 他爸的值, vals.get("b") = 1.0, vals.put("b", 1.0 * 2.0);
                parents.put(s, grandFatherS); // s指向他的grandFather, 祖宗
            }
            return parents.getOrDefault(s, s);
        }


        public void merge(String a, String b, double time){
            add(a);
            add(b);
            String fa = find(a), fb = find(b);
            parents.put(fa, fb);
            vals.put(fa, time * vals.get(b) / vals.get(a)); //用a和b的倍数来更新fa的vals
        }


    }
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        double[] res = new double[queries.size()];
        UNF unf = new UNF();
        for(int i = 0; i < equations.size(); i++){
            String a = equations.get(i).get(0);
            String b = equations.get(i).get(1);
            unf.merge(a, b, values[i]); //先把两两merge起来
        }

        for(int i = 0; i < queries.size(); i++){
            String a = queries.get(i).get(0);
            String b = queries.get(i).get(1);
            res[i] = (unf.parents.containsKey(a) && unf.parents.containsKey(b) && unf.find(a) == unf.find(b)) ?  unf.vals.get(a)/ unf.vals.get(b) : -1.0;
        }

        return res;
    }
}
