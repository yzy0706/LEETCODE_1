package Backtrack;

import java.util.ArrayList;
import java.util.List;

public class CountNumberOfTeams {

    // 做法: backtrack可能时间太慢了
    // 1. 对整个int[]用j forloop, 每次都检查j的左边有多少个比j位置小的, 有多少个比j位置大的, 右边同理也forloop然后记录一遍;
    // 2. 然后每次 res += leftSmall * rightLarge + leftLarge * rightSmall;

    // Runtime: O(n^2), Space: O(1)

    public int numTeams(int[] rating) {
        int len = rating.length, res = 0;
        for(int j = 1; j < len - 1; j++){
            int leftSmall = 0, leftLarge = 0, rightLarge = 0, rightSmall = 0, cur = rating[j];
            for(int i = 0; i < j; i ++){
                if(rating[i] < cur) leftSmall ++;
                else leftLarge ++;
            }
            for(int k = j + 1; k < len; k ++){
                if(rating[k] > cur) rightLarge ++;
                else rightSmall ++;
            }
            res += leftSmall * rightLarge + leftLarge * rightSmall;
        }
        return res;
    }




    public int numTeams_backtrack1(int[] rating) {
        int len = rating.length;
        if(len < 3) return 0;
        res = 0;
        backtrack1(rating, 0, new ArrayList<>());
        return res;
    }

    private void backtrack1(int[] rating, int i, List<Integer> temp){
        if(i == rating.length || temp.size() == 3){
            if(temp.size() < 3) return;
            if((temp.get(1) - temp.get(0)) * (temp.get(2) - temp.get(1)) > 0) res ++;
            return;
        }
        for(int j = i; j < rating.length; j++){
            temp.add(rating[j]);
            backtrack(rating, j + 1, temp);
            temp.remove(temp.size() - 1);
        }
    }









    int res;

    public int numTeams_backtrack(int[] rating) {
        int len = rating.length;
        if(len < 3) return 0;
        res = 0;
        backtrack(rating, 0, new ArrayList<>());
        return res;
    }

    private void backtrack(int[] rating, int i, List<Integer> temp){
        if(i == rating.length || temp.size() == 3){
            if(temp.size() < 3) return;
            if((temp.get(1) - temp.get(0) > 0 && temp.get(2) - temp.get(1) > 0) || (temp.get(1) - temp.get(0) < 0 && temp.get(2) - temp.get(1) < 0)) res ++;
            return;
        }
        for(int j = i; j < rating.length; j++){
            temp.add(rating[j]);
            backtrack(rating, j + 1, temp);
            temp.remove(temp.size() - 1);
        }
    }








    public int numTeams_brute_force(int[] rating) {
        int len = rating.length, res = 0;
        if(len < 3) return 0;
        for(int i = 0; i < len - 2; i++){
            for(int j = i + 1; j < len - 1; j++){
                for(int k = j + 1; k < len; k++){
                    if((rating[j] - rating[i] > 0 && rating[k] - rating[j] > 0) || (rating[j] - rating[i] < 0 && rating[k] - rating[j] < 0)) res++;
                }
            }
        }
        return res;
    }
}
