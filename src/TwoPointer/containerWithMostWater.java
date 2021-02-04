package TwoPointer;

public class containerWithMostWater {
        public int maxArea(int[] height) {
            int left=0;
            int right=height.length-1;
            int max=0;
            while(right>left){
                int area= Math.min(height[left],height[right])*(right-left);
                if(area>max) max=area;
                if(height[right]<=height[left]){
                    right--;
                }
                else if(height[right]>height[left]){
                    left++;
                }
            }

    }
}
