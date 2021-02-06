package Greedy;

public class gasStation {
    //第一遍自己写的

    public int res = -1;
    public int canCompleteCircuit(int[] gas, int[] cost) {
        for(int i = 0 ; i < gas.length; i++){
            travel(gas, cost, i, i, 0);
            if(res != -1) return res;
        }
        return -1;
    }

    public void travel(int[] gas, int[] cost, int start, int pos, int amount){
        amount += gas[pos];
        if(amount < cost[pos]) {
            res = -1;
            return;
        }
        if((pos+1)% gas.length == start) {
            res = start;
            return;
        }
        travel(gas, cost, start, (pos+1) % gas.length, amount);
    }


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
