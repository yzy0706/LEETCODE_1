package Temp;

import java.util.HashMap;
import java.util.Map;

public class FindTheHighestProfit {

    public int supplierInventory_reviewed(int N, Long[] arr, Long K){
        Map<Long, Long> freq = new HashMap<>();
        Long maxPrice = (long) 0;
        for(Long price : arr){
            freq.put(price, freq.getOrDefault(price, (long)0) + 1);
            maxPrice = Math.max(maxPrice, price);
        }
        Long res = (long)0;
        while(K > 0){
            Long numOrders = Math.min(K, freq.get(maxPrice));
            K -= numOrders;
            res += numOrders * maxPrice;
            maxPrice --;
            freq.put(maxPrice, freq.getOrDefault(maxPrice, (long)0) + numOrders);
        }
        return res.intValue();
    }


    // 这个做法是每次maxPrice - 1, 有点慢但可以用
    public int supplierInventory(int numSupplier, long [] inventory, long order){
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
        };
        return (int)profit;
    }
}
