package AMAZ.OA2;

import java.util.HashMap;
import java.util.Map;

public class SupplierInventory {
    public long supplierInventory(int numSupplier, long [] inventory, long order){
        Map<Long, Long> map = new HashMap<>();
        long highest = 0, profit = 0;
        for (long l : inventory) {
            //根据每一个价格记录他们的个数
            map.put(l, map.getOrDefault(l, 0L) + 1);
            highest = Math.max(highest, l);
        }

        //whileloop来用最高的价格满足所有的order
        while (!map.isEmpty() && order > 0){
            long freq = map.get(highest);
            //如果order比这个highest价格的frequency还小， profit直接+= orderr * highest, 因为highest的个数已经合并了
            if (order < freq) {
                profit += order * highest;
                break;
            }
            //如果order > frequency, profit += frquency * 最高价格
            profit += freq * highest;
            order -= freq;
            map.remove(highest);
            //当前这个价格用掉了， freq就要减1了， 这时候map里有这个价格就value加一，否则就建一个新的entry来装这个价格
            //然后highest--下一个循环再判断
            highest--;
            map.put(highest, map.getOrDefault(highest, 0L) + freq);
        }
        System.out.println(profit);
        return profit;
    }

    public static void main(String[] args) {
        SupplierInventory test = new SupplierInventory();
        int num = 3;
        long[] inventory = new long[]{2, 5, 5};
        long order = 7;
        test.supplierInventory(num,inventory,order);
    }
}
