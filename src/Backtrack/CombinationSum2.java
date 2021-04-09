package Backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//跟combinationSum原始版本相比就是把recursion里的l换成了下一个位置， 然后res加temp的时候查重一下
public class CombinationSum2 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        generate(candidates, target, target, 0, temp, res);
        return res;
    }

    public void generate(int[] candidates, int target, int k, int cnt, List<Integer> temp, List<List<Integer>> res) {
        if (k < 0) {
            return;
        }
        if (k == 0) {
            List t = new ArrayList<>(temp);
            if (!res.contains(t)) res.add(t);
            return;
        } else {
            for (int i = cnt; i < candidates.length; i++) {
                temp.add(candidates[i]);
                cnt = i + 1;
                generate(candidates, target, k - candidates[i], cnt, temp, res);
                temp.remove(temp.size() - 1);
            }
        }
    }



    //自己写的
    public List<List<Integer>> combinationSum2_2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        if(candidates[0] > target) return res;
        find(candidates,target,res,temp,0,0);
        return res;

    }

    public void find(int[] candidates, int target,  List<List<Integer>> res, List<Integer> temp, int cur, int l){
        if(cur == target){
            List<Integer> curL = new ArrayList<>(temp);
            if(!res.contains(curL)){
                res.add(curL);
            }
            return;
        }
        for(int i = l; i < candidates.length; i++){
            cur += candidates[i];
            if(cur > target){
                return;
            }
            else{
                temp.add(candidates[i]);
                find(candidates,target,res,temp,cur,i+1);
                temp.remove(temp.size() - 1);
                cur -= candidates[i];
            }
        }
    }

}



