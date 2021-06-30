package Temp;

import java.util.*;

public class SmallestNegativeBalance {

    public List<String> minDebtMembers_reviewed(List<debtRecord> records){
        int len = records.size();
        List<String> res = new ArrayList<>();
        if(len < 1){
            res.add("Nobody has a negative balance");
            return res;
        }
        HashMap<String, Integer> members = new HashMap<>();
        for(debtRecord dr : records){
            String borrower = dr.borrower, lender = dr.lender;
            int amount = dr.amount;
            members.putIfAbsent(borrower, 0);
            members.putIfAbsent(lender, 0);
            members.put(borrower, members.get(borrower) + amount);
            members.put(borrower, members.get(lender) - amount);
        }
        int min = Collections.min(members.values());
        if(min >= 0){
            res.add("Nobody has a negative balance");
            return res;
        }
        for(Map.Entry<String, Integer> entry : members.entrySet()){
            if(entry.getValue() == min){
                res.add(entry.getKey());
            }
        }
        res.sort(String::compareTo);
        return res;
    }







    // 找的是最小值， 不是最靠近0的负值， 不用TreeMap
    //        TreeMap<Integer, List<String>> treeMap = new TreeMap<>();
    //        for(Map.Entry<String, Integer> entry : members.entrySet()){
    //            String name = entry.getKey();
    //            int balance = entry.getValue();
    //            treeMap.putIfAbsent(balance, new ArrayList<>());
    //            treeMap.get(balance).add(name);
    //        }
    //    List<String> minNegative = treeMap.get(treeMap.ceilingKey(0));








    public  class debtRecord{
        String borrower = "";
        String lender  = "";
        int amount  = 0;

        public debtRecord(String borrower, String lender, int amount){
            this.borrower = borrower;
            this.lender = lender;
            this.amount = amount;
        }
    }
    public List<String> smallestNegative(int numRecords, List<debtRecord> debts){
        HashMap<String, Integer>  balances = new HashMap<>();
        for(debtRecord d : debts){
            balances.put(d.borrower, balances.getOrDefault(d.borrower, 0)+d.amount);
            balances.put(d.lender, balances.getOrDefault(d.lender, 0)-d.amount);
        }
        int min = Collections.min(balances.values());
        List<String> res = new ArrayList<>();
        for(String name : balances.keySet()){
            if(balances.get(name) == min) res.add(name);
        }
        Collections.sort(res);
        return res;
    }
}
