package Backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum {
    // 做法: 用的基础的backtrack, 因为考虑顺序, 但不考虑重复, 就每次从i开始浏览
    // Runtime: O(n*t), n是candidates的数量, t是target的大小, 计算最多的情况就是candidates里全都是1、2这种很小的数, 就要每次组合接近t个数来组成一个有效的组合. Space: O(t), 每个temp的大小

    private List<List<Integer>> res;
    public List<List<Integer>> combinationSum_reviewed(int[] candidates, int target) {
        res = new ArrayList<>();
        combine(candidates, target, 0, new ArrayList<>());
        return res;
    }

    private void combine(int[] candidates, int target, int i, List<Integer> temp){
        if(target < 0) return;
        if(target == 0){
            if(!res.contains(temp)) res.add(new ArrayList<>(temp));
            return;
        }
        for(int j = i; j < candidates.length; j++){
            temp.add(candidates[j]);
            combine(candidates, target - candidates[j], j, temp);
            temp.remove(temp.size()-1);
        }
    }





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




    public List<List<Integer>> combinationSum_2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        if(candidates[0] > target) return res;
        find(candidates, target, temp, res, 0, 0);
        return res;
    }

    public void find(int[] candidates,int target, List<Integer> temp,List<List<Integer>> res, int l,int cur){
        if(cur == target){
            res.add(new ArrayList<>(temp));
            return;
        }
        for(int i = l; i < candidates.length ; i++){
            cur += candidates[i];
            if(cur > target){
                // cur -= candidates[i];
                // continue;
                return;
            }
            else{
                temp.add(candidates[i]);
                // res.add(temp);
                find(candidates,target,temp,res,i,cur);
                cur -= candidates[i];
                temp.remove(temp.size() - 1);
            }
        }
    }
}
