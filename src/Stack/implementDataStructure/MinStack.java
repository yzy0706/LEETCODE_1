package Stack.implementDataStructure;

import java.util.PriorityQueue;
import java.util.Stack;

public class MinStack {
    //做法： 用一个Stack和一个PriorityQueue来实现的, getMin()只是把最小的拿出来看一下， 如果是push就两个结构都push，
    // 如果是pop就pq.remove(stack.peek())的同时 stack.pop()
    // Runtime: O(nlog(n)), spcace: O(nlog(n))
    Stack<Integer> stack;
    PriorityQueue<Integer> pq;
    /** initialize your data structure here. */
    public MinStack() {
        stack = new Stack<Integer>();
        pq = new PriorityQueue<>();
    }

    public void push(int x) {
        stack.push(x);
        pq.offer(x);
    }

    public void pop() {
        pq.remove(stack.peek());
        stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return pq.peek();
    }


    //两个stack的做法
    //做法：
    // push： nums正常push， 如果当前x <= sorted.peek()则sorted也push
    // pop(): nums正常pop， 如果sorted的peek .equals nums的peek， 则sorted也要pop()

    Stack<Integer> nums;
    Stack<Integer> sorted;
    /** initialize your data structure here. */
    public MinStack() {
        nums = new Stack<>();
        sorted = new Stack<>();
    }

    public void push(int x) {
        nums.push(x);
        if(sorted.size() == 0 || sorted.peek() >= x){
            sorted.push(x); //如果当前最小的 <= x, 则把x push进sort好的stack
        }
    }

    public void pop() {
        if(nums.peek().equals(sorted.peek())) sorted.pop();
        //java里面peek()出来比较大的数会被看作object， 是不能直接用 == 比较的， 要么用equals要么用两个参数来point这两个object的intVal;
        //如果是比较小的数 -128 ～ 127之内的话是可以直接 == 比较， 就是在直接比较数值和地址
        nums.pop();
    }

    public int top() {
        return nums.peek();
    }

    public int getMin() {
        return sorted.peek();
    }




}


}
