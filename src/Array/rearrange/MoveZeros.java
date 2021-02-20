package Array.rearrange;

public class MoveZeros {
    // 做法： 第一遍直接拿两个forloop写的， 碰到不是0的位置j就和是0的位置i交换
    // Runtime：O（n^2), Space: O(1)
    public void moveZeroes(int[] nums) {
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == 0){
                for(int j = i + 1; j < nums.length; j++){
                    if(nums[j] != 0){
                        int temp = nums[i];
                        nums[i] = nums[j];
                        nums[j] = temp;
                        break;
                    }
                }
            }
        }
    }

    // 做法: 第二个就是用一个insertPos来记录每一个插入后面非0的数的位置, 插入完所有非0的数以后再把后面的位置都设置为0
    // Runtime: O(n) Space: O(1);
    public void moveZeroes_insertPos(int[] nums) {
        int insertPos = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] != 0){
                nums[insertPos ++] = nums[i];
            }
        }
        for(int i = insertPos; i < nums.length; i++){
            nums[i] = 0;
        }
    }
}
