package backTrack;

import java.util.ArrayList;
import java.util.List;

public class combinationSum {
    //借鉴了java的sulution以后写的，改良了一下candidates把它先sort，如果cur>target就直接结束recursion,
    //添加temp到res的时候要写 res.add(new ArrayList<>(temp)) 不然会出bug
    //符合条件的情况要写在recursion的第一个if里,而不是在loop里
    //helper最好应该是void


    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        findNumber(candidates, target , 0 ,res , temp);
        return res;
    }

    public  void findNumber(int[] candidates, int k , int cnt , List<List<Integer>> res , List<Integer> temp ){
        if(k < 0) return;
        if(k == 0) {
            res.add(new ArrayList<>(temp));
            return;
        }
        else{
            for(int i = cnt; i < candidates.length ; i++){
                temp.add(candidates[i]);
                cnt = i;
                findNumber(candidates,k-candidates[i], cnt , res, temp);
                temp.remove(temp.size()-1);
            }
        }
    }
}
