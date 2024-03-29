package Tree.DFS;

public class converSortedArrayToBST {
    public TreeNode sortedArrayToBST(int[] nums) {
        return createBST(nums,0,nums.length-1);

    }

    public TreeNode createBST(int[]nums, int start, int end){
        if(start>end) return null;
        int mid = (start+end)/2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = createBST(nums,start,mid-1);
        node.right= createBST(nums,mid+1,end);
        return node;
    }
}
