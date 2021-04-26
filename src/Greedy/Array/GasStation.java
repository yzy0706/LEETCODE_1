package Greedy.Array;

public class GasStation {
    // 做法: discussion给的greedy做法:
    // 1. forloop整个array, 从0开始, 用totalLeft记录从最开始算起能够留下来的的gas, 用curLeft记录从上一个起点开始到当前的位置还剩下的油
    // 2. 如果当前curLeft < 0了, 证明前面那个start的点没法跑完全程, 跑到这个i点就没油了, start = i + 1;
    // 3. 如果跑完全程totalLeft > 0, 证明是可以跑完全程的,那么当前这个curLeft > 0的start肯定也能跑完全程, return start, 否则return -1;
    // Runtime:  O(n), Space: O(1);

    public int canCompleteCircuit_greedy(int[] gas, int[] cost) {
        int totalLeft = 0, curLeft = 0, start = 0;
        for(int i = 0; i < gas.length; i++){
            totalLeft += gas[i] - cost[i];
            curLeft += gas[i] - cost[i];
            if(curLeft < 0){
                start = i + 1;
                curLeft = 0;
            }
        }
        return totalLeft < 0 ? -1 : start;
    }



    // 做法: 用的dfs做法, 好像有点时间太长了
    // Runtime: O(n^2), Space: O(1);
    public int canCompleteCircuit_reviewed(int[] gas, int[] cost) {
        for(int i = 0; i < gas.length; i++){
            if(gas[i] < cost[i]) continue;
            if(dfs(gas, cost, i, (i + 1) % gas.length, gas[i] - cost[i]) != -1) return dfs(gas, cost, i, (i + 1) % gas.length, gas[i] - cost[i]);
        }
        return -1;
    }

    public int dfs(int[] gas, int[] cost, int start, int pos, int gasLeft){
        if(start == pos) return start;
        gasLeft = gasLeft + gas[pos] - cost[pos];
        if(gasLeft < 0) return -1;
        return dfs(gas, cost, start, (pos + 1) % gas.length, gasLeft);
    }



    //第一遍自己写的

//    public int res = -1;
//    public int canCompleteCircuit(int[] gas, int[] cost) {
//        for(int i = 0 ; i < gas.length; i++){
//            travel(gas, cost, i, i, 0);
//            if(res != -1) return res;
//        }
//        return -1;
//    }
//
//    public void travel(int[] gas, int[] cost, int start, int pos, int amount){
//        amount += gas[pos];
//        if(amount < cost[pos]) {
//            res = -1;
//            return;
//        }
//        if((pos+1)% gas.length == start) {
//            res = start;
//            return;
//        }
//        travel(gas, cost, start, (pos+1) % gas.length, amount);
//    }


    // lc的答案


    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int total_tank = 0;
        int curr_tank = 0;
        int starting_station = 0;
        for (int i = 0; i < n; ++i) {
            total_tank += gas[i] - cost[i];
            curr_tank += gas[i] - cost[i];
            // If one couldn't get here,
            if (curr_tank < 0) {
                // Pick up the next station as the starting one.
                starting_station = i + 1;
                // Start with an empty tank.
                curr_tank = 0;
            }
        }
        return total_tank >= 0 ? starting_station : -1;
    }
}
