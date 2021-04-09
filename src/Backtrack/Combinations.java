package Backtrack;

import java.util.ArrayList;
import java.util.List;

public class Combinations {
    // 做法: 用的基本的backtrack, 注意一下permutation的时候要不要考虑顺序就好, 其实考虑顺序就每次进入helper的position都变成i+1就行，不然就还是浏览完list里的所有东西，下面都是废话
    // Runtime: O(n^2), Space: O(n)
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if(k > n) return res;
        int[] nums = new int[n];
        permute(n, k, 1, res, new ArrayList<>());
        return res;

    }

    public void permute(int n, int k, int index, List<List<Integer>> res, List<Integer> temp){
        if(temp.size() == k){
            res.add(new ArrayList<>(temp));
            return;
        }
        for(int i = index; i <= n; i++){
            temp.add(i);
            permute(n, k, i + 1, res, temp);
            temp.remove(temp.size() - 1);
        }
    }



    // 做法: 用的基本的backtrack, 注意一下permutation的时候要不要考虑顺序就好,
    // 其实这里关于考不考虑顺序的说法错了， 其实就是要不要从当前的位置的下一个或者进入helper而已， 不然就还是浏览所有的list里的东西
    // 如果不考虑顺序可以直接进入helper, 然后每次helper都浏览完array里所有的东西, 只要不是重合的当前的这个东西就好
    // 如果考虑顺序就可以在进入helper之前就forloop一遍, 然后依次根据各个数进入helper
    // Runtime: O(n^2), Space: O(n)
    public List<List<Integer>> combine_self(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        int[] nums = new int[n];
        for(int i = 0; i < n; i++){
            nums[i] = i+1;
        }
        for(int i = 0; i < n; i++){
            List<Integer> temp = new ArrayList<>();
            temp.add(nums[i]);
            permute(k, i, nums, res, temp);
        }
        return res;

    }

    public void permute(int k, int index, int[] nums, List<List<Integer>> res, List<Integer> temp){
        if(temp.size() == k){
            res.add(new ArrayList<>(temp));
            return;
        }
        for(int i = index + 1; i < nums.length; i++){
            temp.add(nums[i]);
            permute(k, i, nums, res, temp);
            temp.remove(temp.size() - 1);
        }
    }
}
