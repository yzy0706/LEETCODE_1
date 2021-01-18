package OA2;

import java.util.*;

public class SmallestNegativeBalance {
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
