package Stack.implementDataStructure;

import java.util.LinkedList;
import java.util.Queue;

//做法： queue1来承载所有的元素， queue2来在每次pop()或者peek()时承载所有queue1吐出来的元素， 然后queue2在吐到只剩下一个元素的时候就是stack
//要求的值
//Runtime： O(n), space: O(n)
public class ImplementStackWithQueue {
    Queue<Integer> queue1;
    Queue<Integer> queue2;
    /** Initialize your data structure here. */
    public ImplementStackWithQueue() {
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        queue1.offer(x);
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        while(!queue1.isEmpty()){
            queue2.offer(queue1.poll());
        }
        while(queue2.size() > 1){
            queue1.offer(queue2.poll());
        }
        int res = queue2.poll();
        return res;
    }

    /** Get the top element. */
    public int top() {
        while(queue1.size() > 1){
            queue2.offer(queue1.poll());
        }
        int res = queue1.peek();
        queue2.offer(queue1.poll());
        while(!queue2.isEmpty()){
            queue1.offer(queue2.poll());
        }
        return res;
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue1.isEmpty();
    }
}
}
