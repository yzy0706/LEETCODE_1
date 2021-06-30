package Math;

public class ReachANumber {
    //


    //第一遍想用dfs来做， 但是stack overflow
    int res;
    public int reachNumber(int target) {
        if(target == 0) return 0;
        res = Integer.MAX_VALUE;
        dfs(target, 0, 1);
        return res;
    }

    public void dfs(int target, int cur, int move){
        if(cur + move == target || cur - move == target){
            res = move;
            return;
        }
        else{
            dfs(target, cur + move, move + 1);
            dfs(target, cur - move, move + 1);
        }
    }
}
