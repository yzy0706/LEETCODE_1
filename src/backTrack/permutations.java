package backTrack;

import java.util.ArrayList;
import java.util.List;

public class permutations {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        mix(nums,res,temp);
        return res;
    }

    public void mix(int[] nums, List<List<Integer>> res,List<Integer> temp ){
        if(temp.size() == nums.length){
            res.add(new ArrayList<>(temp));
            return;
        }
        else{
            for(int i = 0; i < nums.length ; i++){
                if(!temp.contains(nums[i])){
                    temp.add(nums[i]);
                    mix(nums,res,temp);
                    temp.remove(temp.size()-1);
                }
            }
        }
    }
}
