package Stack.implementDataStructure;

import java.util.Stack;
//做法： 拿两个stack， stack装了当前queue的所有元素， 但head在最下面， 所以当我们要使用queue的功能时我们把stack1的元素由上到下push到
//stack2里， 这样就可以把stack2当作queue用， 然后再把stack2里的所有元素由上到下再放回stack1
//Runtime: O(n). space: O(n)
public class ImplementQueueUsingStack {
    Stack<Integer> stack1;
    Stack<Integer> stack2;
    /** Initialize your data structure here. */
    public ImplementQueueUsingStack() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        stack1.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        while(!stack1.isEmpty()){
            stack2.push(stack1.pop());
        }
        int res = stack2.pop();
        while(!stack2.isEmpty()){
            stack1.push(stack2.pop());
        }
        return res;
    }

    /** Get the front element. */
    public int peek() {
        while(stack1.size() > 1){
            stack2.push(stack1.pop());
        }
        int res = stack1.peek();
        while(!stack2.isEmpty()){
            stack1.push(stack2.pop());
        }
        return res;
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        if(stack1.isEmpty()) return true;
        else return false;
    }
}
