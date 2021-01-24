package Heap;

import java.util.Stack;

public class trappingRainWater {

    //单调栈的解法主要就是从右到左去浏览当前i左边的两个
    public int trap(int[] height) {
        int sum = 0;
        Stack<Integer> indexStack = new Stack<Integer>();
        indexStack.push(0);

        for(int i = 1 ; i < height.length ; i++){
            int curH = (i < height.length? height[i] : 0);

            while(!indexStack.empty() && height[indexStack.peek()] < curH){
                int h1 = height[indexStack.pop()];
                if(!indexStack.empty()){
                    int leftBound = indexStack.peek();
                    int h2 = height[leftBound];
                    int minH = Math.min(h2,curH);

                    res += (minH - h1)*(i - leftBound - 1);

                }

            }
            indexStack.push(i);
        }

        return sum;
    }




    //自己写的
    public int trap(int[] height) {
        int res = 0;
        // if(height.length < 1) return 0;
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(0);

        for(int i = 1; i < height.length; i++){
            int curH = (i < height.length? height[i] : 0);

            while(!stack.isEmpty() && height[stack.peek()] < curH){
                int left1 = height[stack.pop()]; //left1是左边第一个的高度, 先pop出i左边的第一个再看看stack是不是empty来决定要不要继续
                if(!stack.isEmpty()){
                    int left2Index = stack.peek(); //注意：最左邊的那個index不被pop出去，只是peek()
                    int left2 = height[left2Index]; //left2是最左边的高度
                    int minH = Math.min(left2, curH); //求是最左边的矮还是最右边的矮

                    res += (minH - left1) * (i - left2Index - 1); //用最左邊或者最右邊的那個高度減去中間的高度再乘以長度
                }
            }
            stack.push(i);
        }
        return res;

    }










    //这一遍解法没过
    public int res;
    public int trap(int[] height) {
        if(height.length < 1) return 0;
        // Queue<Integer> minHeapLeft = new PriorityQueue<Integer>();
        Queue<Integer> maxHeapLeft = new PriorityQueue<Integer>(Collections.reverseOrder());
        // Queue<Integer> minHeapRight = new PriorityQueue<Integer>();
        Queue<Integer> maxHeapRight = new PriorityQueue<Integer>(Collections.reverseOrder());
        res = 0;

        int i = 0 , cutL= 0, cutR = 0, lMax = 0, temp = 0;
        for(i = 0 ; i < height.length ; i++){
            if(maxHeapLeft.isEmpty() && i != height.length-1){
                if(height[i] == 0 || height[i] <= height[i+1]) continue;
                else{
                    maxHeapLeft.offer(height[i]);
                    cutL = i;
                    // minHeapLeft.offer(height)
                }
            }



            else{
                if(maxHeapRight.isEmpty() && i != height.length -1){
                    // minHeapLeft.offer(height[i]);
                    maxHeapLeft.offer(height[i]);
                    // if(minHeapLeft.peek() != height[i]) {
                    if(height[i] < height[i+1]){
                        // minHeapLeft.remove(height[i]);
                        // maxHeapLeft.remove(height[i]);
                        // int lMax = maxHeapLeft.peek();
                        // minHeapRight.offer(height[i]);
                        maxHeapRight.offer(height[i]);

                        continue;
                    }
                    // lCnt += height[i-1] - height[i];
                }


                else{
                    // minHeapRight.offer(height[i]);
                    maxHeapRight.offer(height[i]);
                    if(height[i] >= maxHeapLeft.peek()){
                        temp = maxHeapLeft.peek();
                        cutR = i;
                        cal(cutL,cutR,height,temp);
                        maxHeapLeft.clear();
                        maxHeapRight.clear();
                    }
                    else if(i == height.length - 1){
                        temp = maxHeapRight.peek();
                        cutR = i;
                        cal(cutL,cutR,height,temp);
                        maxHeapLeft.clear();
                        maxHeapRight.clear();
                    }
                    else if(height[i] > height[i+1]){
                        temp = maxHeapRight.peek();
                        cutR = i;
                        cal(cutL,cutR,height,temp);
                        maxHeapLeft.clear();
                        maxHeapRight.clear();
                    }
                }
            }
        }

        return res;

    }


    public void cal(int cutL, int cutR, int[] height,int temp){
        for(int j = cutL; j < cutR + 1 ; j++ ){
            res += temp - height[j];
        }
    }





    // twoPointer法
    public int trapTwoPointer(int[] height){
        if(height.length < 3) return 0;
        int res = 0 , l = 0 , r = height.length -1, lH = height[l] , rH = height[r];

        while(l < r){
            if(height[l] < height[r]){
                l++;
                if(lH <= height[l]) lH = height[l];
                else res += lH - height[l];
            }
            else{
                r--;
                if(rH <= height[r]){
                    r++;
                    if(rH <= height[r]) rH = height[r];
                    else res += rH - height[r];
                }
            }
        }

        return res;

    }

}
