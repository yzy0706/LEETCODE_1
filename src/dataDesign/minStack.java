package dataDesign;

public class minStack {
        public Deque<Integer> stack ;
        public int min;
        public List <Integer> list = new ArrayList<>();

        /** initialize your data structure here. */
        public MinStack() {
            this.stack = new ArrayDeque();
        }

        public void push(int x) {
            if(this.stack.isEmpty()){
                this.min = x;
            }
            if(x < this.min) this.min = x;
            this.list.add(x);
            this.stack.push(x);

        }

        public void pop() {
            if(this.stack.peek() == this.min){
                Collections.sort(this.list);
                this.list.remove(this.stack.peek());
                this.min = list.get(0);
            }
            this.stack.pop();

        }

        public int top() {
            int a = this.stack.peek();
            return a;
        }

        public int getMin() {
            return this.min;

        }
    }

