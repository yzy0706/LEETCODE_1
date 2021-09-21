package DynamicProgramming.doubleForLoop.greedyCombinations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShoppingOffers {
    // 做法: 这是一个结合了DFS, DP, backtrack的做法, List<Integer> DP 代表当前这个recursion内各个item还需要多少个
    // 1. 首先建立一个helepr, 以便在当前的DP和当前浏览到的special的位置开始和后面其他的special offer做perutation
    // 2. 在helper tryOffer里:
    //      a. 首先用另外一个helper算出来如果当前不再进行任何别的special offer了, 就单单买每一个物品, 需要的总价格是多少, 记为localMin
    //      b. 从specialPos开始forloop special里面剩下的所有的offer, 建立一个新的临时list DP记载每个item在选择了这个special offer以后还剩下多少个:
    //          用j来forloop每一个special offer里的每一个item
    //          如果spcial offer的东西比需要的多了, 直接break, DP = null;
    //          否则 DP.add(needs.get(j) - specialOffer.get(j));
    //      c.  得到dp了以后进行新的DFS, recursion, 看这个组合最后的结果是不是小于localMin: localMin = Math.min(localMin, specialOffer.get(specialOffer.size()-1) + tryOffer(price, special, curDP, i));
    //      d. 直接return localMin, 最小的付钱总数就会一直更新
    // Runtime: O(n^2), Space: O(n);

    public int shoppingOffers_DFS_DP(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        return tryOffer(price, special, needs, 0);
    }

    private int tryOffer(List<Integer> price, List<List<Integer>> special, List<Integer> needs, int specialPos){
        int localMin = regularBuy(price, needs);
        for(int i = specialPos; i < special.size(); i++){ //从specialPos开始跟后面的做permutation, 把special里面的各个offer都浏览叠加一遍
            List<Integer> specialOffer  = special.get(i);
            List<Integer> curDP = new ArrayList<>();

            for(int j = 0; j < needs.size(); j++){
                if(needs.get(j) < specialOffer.get(j)){ //如果spcial offer的东西比需要的多了, 直接break
                    curDP = null;
                    break;
                }
                curDP.add(needs.get(j) - specialOffer.get(j)); //当前各个item的数量 = needs.get(j) - specialOffer.get(j);
            }
            if(curDP != null){ //尝试后面的recursion, needs被curDP给替代了
                localMin = Math.min(localMin, specialOffer.get(specialOffer.size()-1) + tryOffer(price, special, curDP, i));
            }
        }
        return localMin;
    }

    private int regularBuy (List<Integer> price, List<Integer> DP){
        int totalPay = 0;
        for(int i = 0; i < price.size(); i++){
            totalPay += price.get(i) * DP.get(i);
        }
        return totalPay;
    }







    //第一遍自己写的, 用了HashMap来储存每一个状态
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        HashMap<List<Integer>, Integer> consumed = new HashMap<>();
        List<Integer> dp = new ArrayList<>(needs.size());
        for(int i = 0; i < needs.size(); i++){
            dp.add(0);
        }
        consumed.put(dp, 0);
        tryOffer(price, special, needs, dp, consumed);
        return consumed.get(needs);
    }

    private void tryOffer(List<Integer> price, List<List<Integer>> special, List<Integer> needs, List<Integer> dp, HashMap<List<Integer>, Integer> consumed){
        if(dp.equals(needs)){
            return;
        }

        for(List<Integer> specialOffer : special){
            List<Integer> curDp = new ArrayList<>(dp.size());
            boolean qualified = true;
            for(int i = 0; i < specialOffer.size()-1; i++){
                int itemNeed = needs.get(i);
                int curNum = dp.get(i);
                int nextNum = curNum + specialOffer.get(i);
                if(nextNum > itemNeed){
                    qualified = false;
                    regularFill(price, needs, dp, consumed);
                }
                else{
                    curDp.add(nextNum);
                }
            }

            if(qualified){
                int curPay = consumed.get(dp) + specialOffer.get(specialOffer.size()-1);
                consumed.put(curDp, Math.min(consumed.getOrDefault(curDp, Integer.MAX_VALUE), curPay));
                tryOffer(price, special, needs, curDp, consumed);
            }
        }
    }

    private void regularFill(List<Integer> price, List<Integer> needs,  List<Integer> dp, HashMap<List<Integer>, Integer> consumed){
        int curPay = consumed.getOrDefault(dp, 0);
        for(int i = 0; i < dp.size(); i++){
            int itemNum = dp.get(i);
            int need = needs.get(i);
            while(itemNum < need){
                curPay += price.get(i);
                itemNum ++;
            }
        }
        consumed.put(dp, Math.min(consumed.getOrDefault(dp, Integer.MAX_VALUE), curPay));
    }
}
