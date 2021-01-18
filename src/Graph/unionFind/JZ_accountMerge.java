package Graph.unionFind;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JZ_accountMerge {
    UNF unf = new UNF();

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, Integer> emailToID = new HashMap<>();
        Map<String, String> emailToName = new HashMap<>();
        for (int cur_ID = 0; cur_ID < accounts.size() ; cur_ID ++){
            List<String> cur_Account = accounts.get(cur_ID);
            for(int i = 1 ; i < cur_Account.size() ; i++){
                String email = cur_Account.get(i);
                if(!emailToID.containsKey(email)) emailToID.put(email,cur_ID);
                String name = cur_Account.get(0);
                emailToName.put(email,name);
                unf.union(emailToID.get(cur_Account.get(1)),emailToID.get(email));
            }
        }

        Map<Integer,List<String>>  ans = new HashMap<>();
        for(String email : emailToName.keySet()){
            int father = unf.find(emailToID.get(email));
            ans.computeIfAbsent(father , x -> new ArrayList()) . add(email);
        }
        for(List<String> emails : ans.values()){
            Collections.sort(emails);
            emails.add(0,emailToName.get(emails.get(0)));
        }

        return new ArrayList<>(ans.values());
}


public class UNF{
    int[] f;
    public UNF() {
        f = new int[100001];
        for(int i = 0 ; i <= 10000 ; i++ ){
            f[i] = i ;
        }
    }

    public  int find(int x){
        int fx, j;
        j = f[x];
        while(j != f[j]) j = f[j];
        //路径压缩
        while(x != j){
            fx = f[x];
            f[x] = j;
            x = fx;
        }

        return j;
    }



    public void union(int x , int y){
        int fx = find(x);
        int fy = find(y);
        if(fx != fy) f[fx] = fy;
    }
}
}