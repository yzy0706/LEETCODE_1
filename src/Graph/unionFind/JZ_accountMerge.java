package Graph.unionFind;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JZ_accountMerge {
    UNF unf = new UNF();


    public List<List<String>> accountsMerge_1(List<List<String>> accounts) {
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






    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        List<List<String>> res = new ArrayList<>();
        UNF unf = new UNF();
        for(List<String> account: accounts){
            String name = account.get(0);
            boolean findSameEmail = false;
            for(int i = 1; i < account.size(); i++){ // merge all account with single same email to one name
                String email = account.get(i);
                for(List<String> key : unf.parents.keySet()){
                    if(key.contains(email)){
                        unf.merge(key, account);
                        findSameEmail = true;
                        break;
                    }
                }
            }

            if(!findSameEmail){
                unf.add(account);
            }
        }

        for(List<String> account : unf.parents.keySet()){
            String name = unf.parents.get(account);
            List<String> curAccount = new ArrayList<>();
            curAccount.add(name);
            account.sort((a, b) -> a.compareTo(b));
            account = removeDuplicates(account);
            curAccount.addAll(account);
            res.add(curAccount);
        }

        return res;

    }

    public List<String> removeDuplicates(List<String> account){
        for(int i = 0; i < account.size() - 1; i++){
            if(account.get(i).equals(account.get(i+1))){
                account.remove(i);
                account = removeDuplicates(account);
            }
        }
        return account;
    }

    class UNF{
        private HashMap<List<String>, String> parents;

        public UNF(){
            parents = new HashMap<>();
        }

        public void merge(List<String> key, List<String> account){
            String name = parents.get(key);
            parents.remove(key); //原来这个邮件地址对应的地址们在parents里面删掉
            account.remove(0); //删掉名字
            for(String s : account){
                if(!key.contains(s)){
                    key.add(s);
                }
            }
            parents.put(key, name); //合并的邮件地址在parents里添加进去
        }

        public void add(List<String> account){
            String name = account.get(0);
            account.remove(0);
            parents.put(account, name);
        }

    }