package DynamicProgramming.twoDimensionalRelations;

public class TossStrangeCoins {
    //做法: 二维dp的做法就是dp[i][target]代表着当前浏览到第i个硬币, 还剩下target个硬币需要是head的成功概率
    // dfs的极限情况就是i == len且target也到0了, 就return 1.0, target到不了0就return 0;
    // if(target > 0) curProb += dfs(prob, target - 1, i + 1, dp) * probH; curProb += dfs(prob, target, i + 1, dp) * probT;
    // 这题不知道为什么用Double[][] dp就不会RTE, 如果是double[][] dp就会RTE
    // Runtime: O(n^2), Space: O(n^2)

    public double probabilityOfHeads_DFSDP(double[] prob, int target) {
        int len = prob.length;
        if(target > len) return 0;
        Double[][] dp = new Double[len+1][target+1];
        return dfs(prob, target, 0, dp);
    }

    private double dfs(double[] prob, int target, int i, Double[][] dp){
        if(i == prob.length){
            if(target == 0) return 1.0;
            return 0;
        }
        if(dp[i][target] != null) return dp[i][target];
        double probH = prob[i];
        double probT = 1 - probH;
        double curProb = 0;
        if(target > 0) curProb += dfs(prob, target - 1, i + 1, dp) * probH;
        curProb += dfs(prob, target, i + 1, dp) * probT;
        dp[i][target] = curProb;
        return curProb;
    }




    public double probabilityOfHeads(double[] prob, int target) {
        int len = prob.length;
        if(target > len) return 0;
        double[][] dp = new double[len+1][target+1];
        return dfs(prob, target, 0, dp);
    }

    private double dfs(double[] prob, int target, int i, double[][] dp){
        if(i == prob.length){
            if(target == 0) return 1.0;
            return 0;
        }
        if(dp[i][target] != 0) return dp[i][target];
        double probH = prob[i];
        // System.out.println(probH);
        double probT = 1 - probH;
        double curProb = 0;
        if(target > 0) curProb += dfs(prob, target - 1, i + 1, dp) * probH;
        curProb += dfs(prob, target, i + 1, dp) * probT;
        dp[i][target] = curProb;
        return curProb;
    }





    public double probabilityOfHeads_2ndTry(double[] prob, int target) {
        int len = prob.length;
        if(target > len) return 0;
        double[][] dp = new double[len+1][target+1];
        return dfs(prob, target, 0, 0, dp);
    }

    private double dfs(double[] prob, int target, int numHead, int i, double[][] dp){
        if(i == prob.length){
            if(numHead == target) return 1.0;
            return 0;
        }
        if(dp[i][numHead] != 0) return dp[i][numHead];
        double probH = prob[i];
        // System.out.println(probH);
        double probT = 1 - probH;
        double curProb = 0;
        if(numHead < target) curProb += dfs(prob, target, numHead + 1, i + 1, dp) * probH;
        curProb += dfs(prob, target, numHead, i + 1, dp) * probT;
        dp[i][numHead] = curProb;
        return curProb;
    }





    double res;
    public double probabilityOfHeads_dfs(double[] prob, int target) {
        if(target > prob.length) return 0;
        res = 0;
        dfs(prob, target, 0, 0, 1);
        return res;
    }

    private void dfs(double[] prob, int target, int numHead, int i, double curProb){
        if(i == prob.length){
            if(numHead == target) res += curProb;
            return;
        }
        double probH = prob[i];
        double probT = 1 - probH;
        if(numHead < target){
            dfs(prob, target, numHead + 1, i + 1, curProb * probH);
        }
        dfs(prob, target, numHead, i + 1, curProb * probT);
    }
}
