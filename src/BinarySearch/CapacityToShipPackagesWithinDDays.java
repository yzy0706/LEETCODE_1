package BinarySearch;

public class CapacityToShipPackagesWithinDDays {



    //第一遍想用dfs做，TLE了
    int res = Integer.MAX_VALUE, len;

    public int shipWithinDays(int[] weights, int days) {
        len = weights.length;
        int cur = 0;
        for(int i = 0; i < len; i ++){
            cur += weights[i];
            dfs(weights, days, 1, i, cur);
        }
        return res;
    }

    public void dfs(int[] weights, int days, int day, int i, int cap){
        if(day > days || cap > res){
            return;
        }
        if(i == len - 1){
            if(day == days){
                res = Math.min(res, cap);
            }
            return;
        }
        int cur = 0;
        for(int j = i + 1; j < len; j ++){
            cur += weights[j];
            if(cur > cap) dfs(weights, days, day + 1, j, cur);
            else dfs(weights, days, day + 1, j, cap);
        }
    }
}
