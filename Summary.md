# LEETCODE_1
Sort
------------------------------------------------------------------------------------------------------------------------
1. Sort的几大基础算法：
   
   Bubble Sort:
   
   做法： 从最后一位开始如果小于前面一位两两置换， 如果本次forloop没有出现置换则直接break；
   
   Runtime: O(N^2), Space: O(1)
   
       public void bubbleSort(int[] nums){ 
       boolean flag = false;
       for(int i = 0; i < nums.length; i++){
          for(int j = nums.length - 1; j > 0; j--){
            if(nums[j] < nums[j-1]){
            int temp = nums[j];
            nums[j] = nums[j-1];
            nums[j-1] = temp;
            flag = true;
            }
            if(flag = false) break;
          }
       }
   


   
   Insert Sort:
   做法： 从当前i+1位用whileloop往前一直跟比他大的互换

   Runtime: O(N^2), Space: O(1)
   
       public void insertSort(int[] nums){
       for(int i = 0; i < nums.length-1; i++){
         int j  = i+1;
         while(j > 0 && nums[j] < nums[j-1]){
            int temp = nums[j];
            nums[j] = nums[j-1];
            nums[j-1] = temp;
         }
        }
       }
       



   Heap Sort:
   做法： 从最后一个父亲节点开始maxHeapify它所对应的左右叶子节点以及之下的树， 一直到0， 
         然后再用一个forloop从最后一个节点开始swap(0, i)并再做一次范围递减的maxHeapify, 一直到1为止

   Runtime： 我们heapify一个二叉树是深度是log(n)， n个点每个点我们都有可能交换到树的最底下， 所以是O(nlog(n)), Space: O(1)
   
       public void heapSort(int[] nums){
         int lastIndex = nums.length - 1, lastFather = nums.length/2 - 1;
         for(int i = lastFather; i > 0; i--){
            maxheapify(nums, i, lastIndex);
         }
         for(int j = lastIndex; j > 0; j--){
            swap(j, 0);
            maxHeapify(nums, 0, j-1);
         }
       }
       
       public void swap(int i, int j, int[] nums){
         int temp  = nums[i];
         nums[i] = nums[j];
         nums[j] = temp;
         }
         
       public void maxheapify(int[] nums, int start, int lastIndex){
         int lSon = start*2+1, rSon = lSon+1, curMaxSon = lSon;
         if(lSon > lastIndex) return;
         if(rSon < lastIndex && nums[rSon] > nums[lSon]) curMaxSon = lSon;
         if(nums[curMaxSon] > nums[start]){
           swap(curMaxSon, start, nums);
           maxheapify(nums, curMaxSon, lastIndex);
           }
         }
   
   
   Quick Sort:
   做法：挖坑原理， 首先假如l > r直接return，否则把key设置为arr[l]； 
   然后设置i， j分别等于l， r； 然后折翼一个i < j的whileloop， 从右边看起，j或者i向中间移动，比较j，i位置的数
   是不是比key大， 是的话就拿对方置换自己并且再向中间移动一位， 这样对方就变成一个坑了， 当whileloop结束的
   时候i肯定等于j，那么当前的位置应该就要是当前key应该处于最后sort好的数列的位置， 因为他的左右两边相对于他
   已经是左小右大了，所以结束了以后再把不包括自己的左右两部分继续recursion， 直到l==r为止
   Runtime: 分割左右的数n次， O(nlog(n)), space O(1)

       public void quickSort(int[] nums, int l, int r){
       if(l >= r) return; 
       int i = l, j = r, key = nums[l];
       while(i < j){
        while (i < j && nums[j] > nums[i]){
           j--;
        }
        if(i < j && nums[j] < nums[i]){
           nums[j--] = nums[i];
        }
        while(i < j && nums[i] <= nums[j]){
           i++
        }
        if(i < j && nums[i] >= nums[j]){
            nums[i++] = nums[j];
       }
      }
       nums[i] = key;
       quickSort(nums, l, i-1);
       quickSort(nums, i+1, r);
    }
  
  Merge Sort
  做法： 首先和quick sort一样如果l >= r就直接return了， 
  
    public static void mergeSort(int[] arr, int l, int r){
     if(l < r){
     int m = (l+r)/2;
     mergeSort(arr, l, m);
    mergeSort(arr, m+1, r); //把两边都分割并排序成只有一个的情况
    merge(arr, l, r); //两边都处理完以后再把两边都merge
    }
    }
   

    public static void merge(int[] arr, int l, int r){
        int i = l, m = (l+r)/2, j = m+1, k = 0;
        int[] temp = new int[arr.length];
        //标准的合并两个有序数列的过程
        while(i <= m && j <= r){
            if(arr[i] < arr[j]){
                temp[k++] = arr[i++];
            }
            else{
                temp[k++] = arr[j++];
            }
        }
        while(i <= m){  //把剩下的数都送到temp里面去， 左右肯定有一个队列已经被排完了
            temp[k++] = arr[i++];
        }
        while(j <= r){
            temp[k++] = arr[j++];
        }
        for(int ind = 0; ind < k; ind++){ //这里k是代表着这个temp的长度，那么从l+0开始+ind，最后一位应该是l+k-1, 把temp里的值都放到arr里去
            arr[l+ind] = temp[ind];
        }
}
  
  SelectionSort
做法： forloop i， 里面再forloop一个j从最右边到i， 每次都把最小的值赋值到i
Runtime： O(n^2), space: O(1)

    public void selectionSort(int[] nums){
        for(int i = 0; i < nums.length; i++){
            int minIndex = i;
            for(int j  = nums.length-1; j >= i; j--){
                if(nums[j] < nums[i]) minIndex = j;
            }
            if( minIndex != i){
            int temp = nums[i];
            nums[i] = nums[minIndex];
            nums[minIndex] = nums[i];
            }
        }
    }


   BucketSort
做法： 用一个有限的bucket来限制所有的数， 分别按照最后一个位数和不同的位数放到cnt里面两次（一共两位数）， 排列的过程中按照相同的
位数把位数相同的所有的数分在一个位置上， 用int[] cnt来记录他们的数量， 然后再叠加cnt上的数量， 则这个数当前的cnt的大小就他在这个数列上应该处于的位置
他应该在这个数列里所处的位置， 从所有数的最后一位开始一一分配

    public void bucketSort(int[] nums, int digit){
        int num = nums.length, remainderAgent = 1;
        int[] temp = new int[nums.length];
        for(int i = 0; i < digit; i++, remainderAgent *= 10){
            int[] cnt = new int[10];
            for(int i = 0; i < num; i++){
                cnt[nums[i]/remainderAgent % 10]++;
            }
            for(int i = 1; i < 10; i++){
                cnt[i] += cnt[i-1];
            }
            for(int j = num-1; j >= 0; j--){
                cnt[nums[j]/remainderAgent % 10] --;
                temp[cnt[nums[j]/remainderAgent % 10]] = nums[j];
            }
            for(int i = 0; i < temp.length; i++){
                nums[i] = temp[i];
            }
        }
    }




2. 几种题型的解法： 
   
a. 有关于interval的题：
    
1. 一般要sort一堆interval， 那么我们根据interval[0]来比较就好

2. 比较两个interval我们一般用右边比较左边, 接下来这个取自insert interval：
     


            
                while (insert[0] > intervals[i][1]){
                    res.add(interval);
                    i++;
                }
                while (insert[0] <= intervals[i][1]){
                    insert[0] = Math.min(intervals[i][0], insert[0]);
                    insert[1] = Math.max(intervals[i][1], insert[1]);
                    i++;
                }
                res.add(insert);
                while (i < intervals.length){
                    res.add(intervals[i]);
                }

b.  Anagram, String之类sort之后比较char或者string的题:

1. 内容一样但顺序不一样的东西sort一下再比较就可以了， 比如如果要比较两个anagram直接可以把他们变成char[]然后sort一下再比较。

2. 两个单纯用数字组成的String可以直接compareTo：

        Comparator<String> com = (o1, o2) -> {
            String s1 = o1 + o2;  //o1放前面
            String s2 = o2 + o1;  //o2放前面
            return s2.compareTo(s1);
         };

c.  有负数让你sort平方以后的情况的题：

   1. 应该要结合two pointer和merge sort(例子来自SortTransformedArray), 如果是正系数平方可能要从大往小开始排:
      
               ...
               int l = 0, r = nuns.length-1;
               int StartIndex = a >= 0? l : r;
               while(l <= r){
                  if(a >= 0) temp[startIndex--] = calculate(nums[l]) >= calculate(nums[r])? nums[l++] : nums[r--];
                  if(a < 0) temp[startIndex++] = calcalate(nums[l]) >= calculate(nums[r])? nums[r--] : nums[l++];
               }


d. Use Val As Index

在constant O(1) 的space下， 有当前一个array的数， 要记录之前出现的所有数， 让你找整个array里缺失或者重复出现的数

1. forloop nums上的每一位, 如果当前位置的值大于0, 小于等于nums.length, 则当前的值可以作为index, 
   则用一个whileloop一直swap i 和 nums[i]-1上的数, 也可以用swap了以后再i--, 因为下一轮i又会++回来继续判断当前位置的值是不是就是i++
    
2. 设置当前最小的missing positive是1, 只要当前res应该还在num上就一直往大的移动, 一直到找到nums上当前res-1位置不是对应的值res, 或者当前nums长度以内的数都是符合条件的



         public int firstMissingPositive_valToMark(int[] nums) {
            if(nums.length < 1) return 1;
            for(int i = 0; i < nums.length; i++){
                if(nums[i] == i + 1 || nums[i] < 0) continue;
                while(nums[i] > 0 && nums[i] <= nums.length && nums[nums[i] - 1] != nums[i]) swap(nums, i, nums[i] - 1);
            }
            int res = 1;
            while(res <= nums.length && nums[res-1] == res){
                res ++;
            }
            return res;
         }

        public void swap(int[] nums, int i, int j){
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }

Tree
------------------------------------------------------------------------------------------------------------------------

1. Tree的几大Traversal算法

a. Preorder Traverse(根节点 -> 左子树 -> 右子树)

Recursiion做法：

            public void preorder_recursion(TreeNode root){
                if(root == null) return;
                System.out.println(root.val);
                preorder_recursion(root.left); //碰到底以后会返回
                preorder_recursion(root.right);
            }

Stack Iterative做法：
            
            public void preorder_stack(TreeNode root){
                Stack<TreeNode> Stack = new Stack<>();
                Stack.push(root);
                TreeNode head = root;
                while(head != null || !Stack.isEmpty()){
                    if(head != null){//如果head ！= null
                        System.out.println(head.val + " ");
                        Stack.push(head); // 把他自己装进去
                        head = head.left; //一直往左叶子节点移动
                    }
                    else{
                        TreeNode father = Stack.pop(); // 如果head是null， 就pop出他的爸爸
                        cur = father.right; // head移动到他爸爸的右儿子
                    }
                }
            }
                        
b. Inorder Traverse（左子树 -> 根节点 -> 右子树）

Recursion做法：
        
         public void preorder_recursion(TreeNode root){
                if(root == null) return;
                preorder_recursion(root.left); //碰到底以后会返回
                System.out.println(root.val); //打印第一个爸爸
                preorder_recursion(root.right); //看看右子树能不能也浏览一下
            }

Stack Iterative做法：

        public void preorder_stack(TreeNode root){
                Stack<TreeNode> Stack = new Stack<>();
                Stack.push(root);
                TreeNode head = root;
                while(head != null || !Stack.isEmpty()){
                    if(head != null){//如果head ！= null
                        Stack.push(head); // 把他自己装进去
                        head = head.left; //一直往左叶子节点移动
                    }
                    else{
                        TreeNode father = Stack.pop(); // 如果head是null， 就pop出他的爸爸
                        System.out.println(father.val + " "); 
                        //跟preorder不一样的是我们是浏览到最底端以后再去打印第一个爸爸
                        //所以我们应该在碰到了null以后再操作
                        cur = father.right; // head移动到他爸爸的右儿子
                    }
                }
            }

c. Postorder Traverse(左子树 -> 右子树 -> 根节点)

Recursion算法：
        
            public void postorder(TreeNode root){
                if(root == null) return;
                postorder(root.left); //还有左叶子节点就一直往左， return到根节点
                postorder(root.right); // 没有左叶子节点了就往右叶子节点试试
                System.out.println(root.val); //左叶子节点是空而且右叶子节点也是空就会print
 
d. Levelorder traverse(浏览每一层)

            private void levelOrder(TreeNode root){
                Queue<TreeNode> queue = new LinkedList<TreeNode>();
                queue.offer(root);
                while(!queue.isEmpty()){
                    TreeNode cur = queue.poll();
                    System.out.println(cur.val + " ");
                    if(cur.left != null) queue.offer(cur.left);
                    if(cur.right != null) queue.offer(cur.right);
                }
            }

e. DepthOrder traverse(其实就是preorder的另一种写法)

            private void depthOrder(TreeNode root){
                Stack<TreeNode> Stack = new Stack<>();
                Stack.push(root);
                while(!Stack.isEmpty()){
                    TreeNode cur = Stack.pop();
                    System.out.println(cur.val);
                    Stack.push(cur.right);
                    Stack.push(cur.left); //left后进去， 所以碰到null了以后他会第一个出来， 再转去右儿子
            }

2. 题型的总结 
   
    a. BFS的题：

    1. BFS的题结构上很简单：直接建立一个queue然后把head以及head以下所有的子节点都一层层加进去就好。
       如果需要浏览每一层再开始下一层的话我们直接每次都把这层的size = queue.size();要提前计算出来然后
       再forloop完一整层就好。
       
       在PopulatingNextRightPointersII这道题中， 我们不应该在forloop之外poll（）出最左边的Node然后再forloop剩下的，
       而是应该在进入forloop前设置一个空的pre来承载每一个应该在左边的node
       
                               while(!queue.isEmpty()){
                                    int size = queue.size();
                                    Node pre = new Node();
                                    for(int i = 0; i < size; i++){
                                        Node cur = queue.poll();
                                        pre.next = cur;
                                        if(cur.left != null) queue.offer(cur.left);
                                        if(cur.right != null) queue.offer(cur.right);
                                    }
                                }
    
    b.  Construct Binary Tree的题：
    
    1. 根据inorder数列和postorder或者preorder两者之一的数列来建立一个binary tree的题：
       
       a. 首先我们要注意在preorder里root在最左边， 所以我们应该从左往右开始找根节点 -> 左子树 -> 右子树， 反之在posdtOrder中， 
       我们应该从右往左开始找根节点 -> 右子树 -> 左子树， 因为在postorder中的顺序从左往右应该是左子树  -> 右子树 -> 根节点。 
       
       b. 其次我们要了解在一个重要的知识点（这个在count unique binary trees的dp题型里也会用到）， 在inorder中， 如果i是这个root， 
        则左边0到i-1都应该是他的左子树， i+1到最右边应该都是他的右子树, 所以我们应该设置一个变量inIndex表示当前root在inorder里面的位置， 
        inStart代表他的左子树起点， inEnd代表他的右子树终点， 这样在建立binary tree的helper里从postIndex = length-1， inStart = 0， 
        inEnd = inorder.length -1 的条件开始一直循环建立每个节点的左右子树就行。
       
                    public TreeNode BuildTreePostorder(int[] inorder, int[] postorder){
                        return construct(postorder.length-1, 0, inorder.length-1, inorder, postorder);
                    }
                    

                    public TreeNode construct(int postIndex, int inStart, int inEnd, int[] inorder, int[] postorder){
                        if (postIndex < 0 || inStart > inEnd) return null;
                        TreeNode root = new TreeNode(postorder[postIndex]);
                        inIndex = 0;
                        for(int i = inStart; i <= inEnd； i++){
                            if(inorder[i] == postorder[postIndex] inIndex = i;
                        }
                        root.left = construct(postIndex - (inIndex - inStart + 1),  inStart, inIndex-1, inorder, postorder);
                        root.right = construct(postIndex-1, inIndex+1, inEnd, inorder, postorder);
                        return root;
                    }
    
   




 
c. Counting Numbers of Unique Trees
    
1.首先如果是单纯求1到n这些数字能组成的binaryTree的个数， 我们可以用dp来做， 但是中心还是用的当一个树的头是j时（在一个i的forloop里 ， 以j为头的，
j <= i, 因为j可以为1到i的各个数字）， dp[i] += dp[j-1] (左边的树的大小) + dp[i-j] (j+1到i， 右边树的大小)， 而且dp[0] = 1， dp[1] = 1

        public int uniqueBinaryTrees(int n){
            int dp = new int[n+1];
            dp[0] = 1;
            dp[1] = 1;
            for(int i = 2; i <= n; i++){
                for(int j = 1; j <= i; j++){
                    dp[i] += dp[j-1] + dp[i-j]; ////以j为root， 大小为[j-1], [i-j]的subtree个数叠加在G[i]上
                }
            }
            return dp[n];

2.其次， 如果是给我一个数 n， 让我把所有的树的可能放在一个List<TreeNode> 里return的话:
  a.用two pointer： start和 end来表示这个树的所有元素应该在哪个区间生成
  b.然后用forloop从1到n去浏览所有根节点的可能
  c.根据（start， i-1), (i+1, end)两个区间去dfs 产生他所有的的左右subTree的可能在一个list上（这个题型的中心知识）
  d.然后再在每一个根节点的基础上permute所有左右子树的可能

        public List<TreeNode> uniqueBinaryTree(int n){
        }

        public List<TreeNode> generate(int start, int end){
            List<TreeNode> res = new ArrayList<>();
            if(start > end){  //做dfs的题一定要先想没法运行的corner case
                res.add(null);
                return res;
            }
            if(start == end){
                res.add(new TreeNode(start));
                return res;
            }
            for(int i = start; i <= end; i++){
                List<TreeNode> potentialLefts = generate(start, i-1);
                List<TreeNode> potentialRights = generate(i+1, end);
                
                for(TreeNode leftSon : potentialLefts){
                    for(TreeNode rightSon : potentialRights){
                        TreeNode curRoot = new TreeNode(i);
                        cur.left = leftSon;
                        cur.right = rightSon;
                        res.add(curRoot);
                    }
                }
            
            return res;
        }




d. DFS： DFS里面有connectNodesOnSameLevel这种题， 也有PathSum这种题
1. 首先我们要知道在dfs中， 我们碰到了底层或者碰到了想找的情况是会return回来的， 而不是直接结束方程：在AllNodesDistanceKInBinaryTre这道题
里， 第一个helper是去找到target， 然后找到target return 0， 再一步步在return的步骤中+1并且放到map里：


   
                public void findTarget(TreeNode root, TreeNode target,  Map<TreeNode, Integer> map){
                    if(root == null) return -1;
                    if(root.val  == target.val){
                        return 0;
                        map.put(root, 0);
                    }
                    int left = findTarget(root.left, target, map); //像map， list这种引用参数在各个recursion的使用是共通的
                    if(left >= 0){
                        map.put(root, left+1); //这样从target那一层开始就会一直return + 1， + 1一直到root
                        return left+1;
                    }
        
                    int right = ... (同样的再右子树找一遍， 一样的步骤);
                    
                    return -1;
                }
               
                //然后再找一遍每一个节点在不在在map上， 再分别计算他的距离等不等于k就行
           
        
            
                private void findQualified(TreeNode root, TreeNode target, int distance, int K, Map<TreeNode, Integer> map, List<Integer> res){
                    if(root == null) return;
                    if(map.containsKey(root)) distance = map.get(root); //如果map里本来就有当前的node, 那么证明他肯定在root到target的路径上, 直接把distance置换一下
                    if(distance == K) res.add(root.val); //如果不是路径上的再去判断distance是不是等于K
                    findQualified(root.left, target, distance+1, K, map, res);
                    findQualified(root.right, target, distance+1, K, map, res);  //再往左右dfs去看下面的其他TreeNode在map里还是distance == k都行
                }


2. 就在上面已经提到过， 在dfs的过程中， 如果是list， map之类的引用传递参数， 所有的变化是互通的， 如果我们是在做pathSumII这样需要分别记录每一次dfs
的路径的题目时， 我们需要每次做完dfs以后把自己的最尾部代表当前值的部分像backTrack一样去掉， 这样如果dfs触底了以后会在每一次return到上一个根节点的时候把自己当前的值删掉
    


        public void expand(TreeNode root, int sum, List<Integer> temp){
            if(root == null) return;
            temp.add(root.val);
            if(root.left == null && root.right == null && sum - root.val == 0){
                res.add(new ArrayList<>(temp));
            }
            else {
                expand(root.left, sum - root.val, temp);
                expand(root.right, sum - root.val, temp);
            }
            temp.remove(temp.size()-1);
        }

Stack
------------------------------------------------------------------------------------------------------------------------

1. 基础算法

a. PostFixExpress(也叫逆波兰式)， 计算的时候 
    1. 每次碰到运算符号就pop出装数字的stack里面的两个数
    2. 运算完以后把结果push回stack
    3. 最后把stack里剩下的最后一个数return就是结果

    private int postFixExpression(String s){
        Stack<Integer> stack = new Stack<>();
        char[] cl = s.toCharArray();
        for(char c : cl){
            if(Character.isDigit(c)){
                stack.push(Integer.valueOf(c));
            }
            else{
                int right = stack.pop();
                int left = stack.pop();
                stack.push(calculate(left, right, c));
            }
        }
        return stack.pop();
    }

    private int calculate(int l, int r, Character operator){
        if(operator.equals('+')) return l + r;
        if(operator.equals('-')) return l - r;
        if(operator.equals('*')) return l * r;
        return l / r;
    }
                  



b. PostFixToInfix(调度场算法) 
    
1. 形似火车停入车站：每看到一个运算符就把所有优先级比他大的pop出来， 并把优先级大的运算符都加到答案里



        public String inFixToPostFix(String s){
            HashMap<Character, Integer> priority = constructPriority();
            char[] cl = s.toCharArray();
            Stack<Character> operatorStack = new Stack<>();
            StringBuilder sb = new StringBuilder();
            for(char c : cl){
                if(Character.isDigit(c)){
                    sb.append(c); //如果是int直接append到结果上
                }
                else{ //不是数字的情况
                    if(c == '*' || c == '/' || c == '+' || c == '-') { //如果不是（
                        if (operatorStack.isEmpty()){
                            operatorStack.push(c);
                        }
                        else {
                            Character top = operatorStack.peek();
                            while (priority.get(c) <= priority.get(top)) { //如果当前的operator优先级小于等于前面的， 把前面的都加进去
                                top = operatorStack.pop();
                                sb.append(top);
                                if (operatorStack.isEmpty()) { //当前已经pop出最后一个operator， 直接break
                                    break;
                                }
                                top = operatorStack.peek(); //在循环继续前把stack的头再peek出来准备下一轮用
                            }
                            operatorStack.push(c); //最后再把当前最小的push到操作符栈里
                        }
                    }
    
                    else if(c == '('){
                        operatorStack.push(c);
                    }
                    else if(c == ')'){ //如果是右括号把碰到左括号前的所有的operator全都append上
                        while(!operatorStack.isEmpty()){
                            Character top = operatorStack.pop();
                            if (top == '('){
                                break;
                            }
                            sb.append(top);
                        }
                }

            }
        }
        while(!operatorStack.isEmpty()){
            sb.append(operatorStack.pop());
        }
        return sb.toString();
        }

        public HashMap<Character, Integer> constructPriority(){
            HashMap<Character, Integer> priority = new HashMap<>();
            priority.put('*', 2);
            priority.put('/', 2);
            priority.put('+', 1);
            priority.put('-', 1);
            priority.put('(', 0);
        }


2. 基本题型：

a. 用到了调度场算法的

1. 题型有多重种类，经典题型就是postFix表达式转inFix表达式：
    
    a. 注意在提取多位数的数字的时候可以用 num = num * 10 + (cl[i] - '0');
    
    b. 将一整个List addAll到一个stack里的话他会头先进去， 所以在必要的时候要学会reverse



        ...
        for(char c : s.toCharArray()){
            ...
            if(c == '+' || c == '-' || c == '*' || c == '/'){
                if(opStack.isEmpty()){
                    opStack.push(c);
                }
                else{
                character top = opStack.peek();
                while(priority.get(c) < priority.get(top)){
                    top = opStack.pop();
                    sb.append(top);
                    if(opStack.isEmpty()){
                        break;
                    }
                    top = opStack.peek();  //存在一个变量传送给下一个冒头的运算符的关系， 一直刨到底
                }

2. 也有类似的存储最高的高度来计算面积的（LargestRectangleArea)
        

    
        for(int i = 0; i  < heights.length; i++){
        ...
        if(stack.isEmpty() || heights[i] >= stack.peek()){
            stack.push(heights[i]);
        }
        else{
            int leftHighest = stack.pop();
            res = Math.max(res, nums[leftHighest] * (stack.isEmpty() ? i : i - stack.peek() - 1));
            i--;
        }
        }




b. Implement DataStructure:

1. 这种题主要注意两个Stack一起用的用法就好






c. Parentheses的题:

a. 这种题和任何对称性的题思路一样， 当碰到每个object就把他对称的object push到stack里，
如果后面能依次消除就证明是valid parentheses

        public boolean isValid_stack(String s) {
        char[] cl = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for(char c : cl){
            if(c == '[') stack.push(']');
            else if (c == '{') stack.push('}');
            else if(c == '(') stack.push(')');
            else{
                if(!stack.isEmpty()){
                    char right = stack.pop();
                    if(right != c) return false;
                }
                else{
                    return false;
                }
            }
        }
        if(!stack.isEmpty()) {
            return false;
        }
        return true;
    }

 b. 加入碰到要删除多余的括号的情况：

1. 要善于利用stack的就近优势把最近的对称性括号都删除了， 不要想的太复杂， 
   一般stack的题operator或者括号和普通不需要处理的字符最好分开 可以在stack里储存'['的位置

2. 建立一个StringBuilder(s)并把需要删除的地方都 sb.serCharAt(i, '！')； 
   最后转成String以后 s.replaceAll("!", ""); 可以统一删除
   


        public String minRemoveToMakeValid(String s) {
        char[] cl = s.toCharArray();
        Stack<Integer> stack = new Stack<>();
        StringBuilder sb = new StringBuilder(s);
        for(int i = 0; i < cl.length; i++){
            if(cl[i] == '('){
                stack.push(i);
            }
            else if(cl[i] == ')'){
                if(!stack.isEmpty()){
                    stack.pop();
                }
                else{
                    sb.setCharAt(i, '!');
                }
            }
        }
        
        while(!stack.isEmpty()){
            int pos = stack.pop();
            sb.setCharAt(pos, '!');
        }
        
        String res = sb.toString();
        
        return res.replaceAll("!", "");
            
        }



Dynamic Programming
------------------------------------------------------------------------------------------------------------------------
题型总结

1. 碰到有target求不同组合的问题一定要利用好target和每一个元素之间的差







2. Subarray

1. 如果只是求max subarray 可以用 kadane's algorithm




        public int maxSubArray(int[] nums) {
            if(nums.length < 1) return 0;
            int res = nums[0];
            int sum = nums[0];
            int start = 0, end = 0, curStart = 0, curEnd = 0;
    
            for(int i = 1; i < nums.length ; i++) {
                if (sum < 0) {
                    curStart = i;
                    curEnd = i;
                    sum = nums[i];
                } else {
                    curEnd = i;
                    sum += nums[i];
                }
                if (sum >= res) {
                    start = curStart;
                    end = curEnd;
                    res = sum;
                }
    
            }
    
            return res;
       }


即便是要同时记录两种情况， 只要是没有上限的求最大值我们都可以用kadane，
我们可以用参数来代表dp[i-1][0] 或更多的状态， 节省一点空间


    public int maxSumAfterOperation_kadane(int[] nums) {
        if(nums.length < 1){
            return 0;
        }
        int res = Integer.MIN_VALUE;
        int opHappend  = nums[0] * nums[0], opNotHappend = nums[0];
        if(nums.length == 1){
            return opHappend;
        }
        for(int i = 1; i < nums.length; i++){
            if(opNotHappend <= 0){
                opHappend = Math.max(nums[i] * nums[i], opHappend + nums[i]);
                opNotHappend = nums[i];
            }
            else{
                opHappend = Math.max(opNotHappend + nums[i] * nums[i], opHappend + nums[i]);
                opNotHappend += nums[i];
            }
            res = Math.max(opHappend, res);
        }

        return res;
    }


3. 背包问题：求combination个数的题， 用int[] dp来储存0到所要求的sum的每个数， dp[i]表示i能被已有的元素用几种组合组合起来, dp[0] = 1：

a. 如果是不考虑排列顺序的combination, 可以先forloop所有可能组合成的数字i， 再forloop所有的coin，只要i - number >= 0就行:
（CombinationSum IV)

            for(int i = 0; i <= target; i++){
                for(int number: nums){
                    if(i - number >= 0) dp[i] += dp[i - number];
                }
            }




b. 如果要考虑排列顺序，我们必须从元素找更大的i - coin，所以大的那个forloop应该就是forloop所有的组成元素， 小的那个forloop应该是forloop所有到sum为止 >= 这个coin的
数 (CoinCombinationsII) (PartitionEqualSubsetSum)


            for(int coin : coins){
                for(int i = coin; i <= sum; i++){
                    dp[i] += dp[i - coin];
                }
            }
            
            return dp[sum];


            for(int i = 0; i < nums.length; i++){
           for(int j = sum; j >= nums[i]; j--){
               if(dp[j - nums[i]] == true) dp[j] = true;
           }
        }

4. Bit Mask

a. 使用的符号： 

 1. 非（～）
   ～ 0000 0001
   = 1111 1110
   
 2. 或（|）
    0000 0001
    | 0000 0010
    = 0000 0011
    
 3. 与（&）
    0000 0101
    & 0000 0110
    = 0000 0100
    
 4. << 左移位 *= 2， >> 右移位 /= 2

常用的基本操作如下：
    a&~b: 清除标志位b;
    a|b: 添加标志位b;
    a&b: 取出标志位b;
    a^b: 取出a与b的不同部分;



5. Buy And Sell Stock的问题：

主要就是用到了double array的用法， 加上在sell stock这个过程中的各种变化：


        public int maxProfit(int[] prices) {
            int len = prices.length;
            if(len <= 1) return 0;
            int[] buy = new int[len], sell = new int[len];
            buy[0] = - prices[0];
            for(int i = 1; i < len; i++){
                buy[i] = Math.max(buy[i-1], sell[i-1] - prices[i])（这里可能会收费或者加一个cooldown time什么的）);
                sell[i] = Math.max(sell[i-1], buy[i-1] + prices[i]);
            }
            return sell[len-1];
    }

        

6. 2D dp的题， 比如说求两个string的longest common subsequence那种， 我们一般去假设dp[i+1][j+1]就是两个string分别到i和j的最长
的common subseq， 然后再去看i， j的位置是不是相等（这种subseq都是跳跃着组合的， 如果都不是跳跃的就只有相等的时候才叠加）： 
   

a. 要求完全连续的: 718 Maximum Length of Repeated Subarray



            public int findLength(int[] A, int[] B) {
                int len1 = A.length, len2 = B.length;
                int[][] dp = new int[len1+1][len2+1];
                int res = 0;
                for(int i = 0; i < len1; i++){
                    for(int j = 0; j < len2; j++){
                        if(A[i] == B[j]){
                            dp[i+1][j+1] = dp[i][j] + 1;
                            res = Math.max(dp[i+1][j+1], res);
                        }
                    }
                }
                return res;
        
           }



b.  跳跃着组合的




        public int longestCommonSubsequence(String text1, String text2) {
            int[][] dp = new int[text1.length()+1][text2.length()+1];
            for(int i = 0; i < text1.length(); i++){
                for(int j = 0; j < text2.length(); j++){
                    if(text1.charAt(i) == text2.charAt(j)){
                        dp[i+1][j+1] = dp[i][j] + 1;
                    }
                    else{
                        dp[i+1][j+1] = Math.max(dp[i][j+1], dp[i+1][j]);
                    }
                }
            }
            return dp[text1.length()][text2.length()];
        }


7. Divide into subproblem的题

a. Choose From Eitherside的题型

如果是碰到这种从两头轮流选一个才谁赢的题目， dp[i][j]代表player1在i， j这段中能取得的最大价值都可以用到这个2D DP,

 1. 用一个sum记录当前所有数的和， forloop j从0到len-1， 再forloop i从j-1到0
    
 2. 用mid， bothI， bothJ分别求出来两个人分别选了i， j（当前player1可以选i或者j）， 或者是都选了i， 都选了j四种情况比较大小
    
 3. mid + piles[i]， mid + pile[j], bothI + pile[i], bothJ + pile[j] 更新每一段dp[i][j]的最大值
    
 4. 检查dp[0][len-1]是不是大于sum的一半



            public boolean stoneGame(int[] piles) {
                int len = piles.length;
                if(len % 2 != 0) return true;
                int[][] dp = new int[len][len];
                int sum = 0;
                for(int j = 0; j < piles.length; j++){
                    dp[j][j] = piles[j];
                    sum += piles[j];
                    for(int i = j - 1; i >= 0; i--){
                        int mid = dp[i+1][j-1];
                        int bothI = i + 2 < len ? dp[i+2][j] : 0;
                        int bothJ = j - 2 >= 0 ? dp[i][j-2] : 0;
                        dp[i][j] = Math.max(Math.max(mid + piles[i], mid + piles[j]), Math.max(bothI + piles[i], bothJ + piles[j]));
                    }
                }
                return dp[0][len-1] * 2 > sum;
            }
 



Array
------------------------------------------------------------------------------------------------------------------------
1. Subarray 
   
a. 如果只是求max subarray 可以用 kadane's algorithm

       public int maxSubArray(int[] nums) {
            if(nums.length < 1) return 0;
            int res = nums[0];
            int sum = nums[0];
            int start = 0, end = 0, curStart = 0, curEnd = 0;
    
            for(int i = 1; i < nums.length ; i++) {
                if (sum < 0) {
                    curStart = i;
                    curEnd = i;
                    sum = nums[i];
                } else {
                    curEnd = i;
                    sum += nums[i];
                }
                if (sum >= res) {
                    start = curStart;
                    end = curEnd;
                    res = sum;
                }
    
            }
    
            return res;
       }

       return res;
       }


 即便是要同时记录两种情况， 只要是没有上限的求最大值我们都可以用kadane， 我们可以用参数来
 代表dp[i-1][0] 或更多的状态， 节省一点空间


    public int maxSumAfterOperation_kadane(int[] nums) {
        if(nums.length < 1){
            return 0;
        }
        int res = Integer.MIN_VALUE;
        int opHappend  = nums[0] * nums[0], opNotHappend = nums[0];
        if(nums.length == 1){
            return opHappend;
        }
        for(int i = 1; i < nums.length; i++){
            if(opNotHappend <= 0){
                opHappend = Math.max(nums[i] * nums[i], opHappend + nums[i]);
                opNotHappend = nums[i];
            }
            else{
                opHappend = Math.max(opNotHappend + nums[i] * nums[i], opHappend + nums[i]);
                opNotHappend += nums[i];
            }
            res = Math.max(opHappend, res);
        }

        return res;
    } 
   
b. 假如是只求确定的sum == k而size最大subarray, 就要用到类似与twoSum里面的map的解法，
因为sum(0, j) - sum(0, i) = sum(i, j), 所以我们每次更新res为 res = Math.max(i - map.get(nums[i] - k), res);




        public int maxSubArrayLen(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;
        for(int i = 1; i < nums.length; i++){
            nums[i] += nums[i-1];
        }
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int max = 0;
        for(int i = 0; i < nums.length; i++){
            if(map.containsKey(nums[i] - k)){
                res = Math.max(i - map.get(nums[i] - k), res);
            }
            if (!map.containsKey(nums[i])){
                map.put(nums[i], i);
            }
        }

        return res;
    }



c. 假如是有一个limit k的话， 就要用到treeSet()来找到sum <= k的最大的array

        public int maxSubarrayCloseToK(int[] nums, int k){
        int sum = 0, res = Integer.MIN_VALUE;
        TreeSet<Integer> set = new TreeSet<>();
        set.add(0);

        for (int i = 0; i < nums.length; i++){
            sum += nums[i];
            Integer ceiling = set.ceiling(sum - k);
            if(ceiling != null){
                res = Math.max(res, sum - ceiling); //sum - 与(sum - k)最接近的且大于它值就是sum里与k最接近的值
            }
            set.add(sum);
        }

        return res;

    }




d. 求最长长度话可以用map来记录每到一个位置的和：



    public int maxSubArrayLen(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;
        for(int i = 1; i < nums.length; i++){
            nums[i] += nums[i-1];
        }
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int max = 0;
        for(int i = 0; i < nums.length; i++){
            if(map.containsKey(nums[i] - k)){
                res = Math.max(i - map.get(nums[i] - k), res);
            }
            if (!map.containsKey(nums[i])){
                map.put(nums[i], i);
            }
        }

        return res;
    }



   或者求最短长度用同向双指针滑动窗口(sliding window):


        public int minSubArrayLen_slidingWindow(int target, int[] nums) {
        if(nums.length < 1) return 0;
        int i = 0, j = 0;
        int sum = 0, res = Integer.MAX_VALUE;

        while(j < nums.length){
            sum += nums[j]; //这是加了当前的这个j的数字, j还没有++

            while(sum >= target){
                res = Math.min(res, j - i + 1);
                sum -= nums[i];
                i++;
            }

            j++;
        }


        return res == Integer.MAX_VALUE ?  0 : res;
    }


e. 如果是在forloop上面通过另外一个pointer跳跃到一个位置， 当前的i要跳跃到想去的位置的前一个， 因为在进入下一次forloop前还会++一次到想要的位置

        public int partitionDisjoint(int[] A) {
            int lMax = A[0], pos = 0, len = A.length;
            for(int l = 0; l < len; l++){
                pos = l;
                int r = l + 1;
                int curMax = lMax;
                while(r < len && A[r] >= lMax) curMax = Math.max(curMax, A[r++]);
                if(r == len) break;
                lMax = curMax;
                l = r - 1;
            }
        
            return pos + 1;
        }

2. matrix:
   
1. Rectangle的题， 大体的骨架就是三个forloop， helper暂时见过kadane和closeToK两种情况

2. 如果是matrix要表示即时变化前后的两个状态可以用 1， 2， 3三个数表示，他们 % 2就是0或者1两个状态； 或者直接用二进制



3. Find Duplicates In Array

这种在[1, n]的array里找duplicate或者跟448一样找disappeared的元素又要O(1)space的可以尝试把当前的数值 - 1作为index来标记某个数是否出现过

        public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for(int i = 0; i < nums.length; i++){
            int val = Math.abs(nums[i]);
            if(nums[val-1] > 0) nums[val-1] = - nums[val-1];
        }

        for(int i = 0; i < nums.length; i++){
            if(nums[i] > 0) res.add(i+1);
        }
        return res;
    }

4. 对于这种图像的旋转是有套路的:
   
   1. 如果是顺时针旋转, 对这个图先上下替换, 再沿着对角线替换;
      
   2. 如果是逆时针, 对这个图先左右替换, 再沿着对角线替换
    
   特别注意上下替换i到n/2就可以了, 而且对角线替换j要从i+1开始, 也就是只操作对角线的右半部分, 如果对于整个matrix都操作的话那一开始换过去， 然后浏览到换过去的位置又换回来了, 等于什么都没干

            
            public void rotate_reviewed(int[][] matrix) {
                int n = matrix.length;
        
                for(int i = 0; i < n/2; i++){
                    for(int j = 0; j < n; j++){
                        int temp = matrix[i][j];
                        matrix[i][j] = matrix[n-1-i][j];
                        matrix[n-1-i][j] = temp;
                    }
                }
        
                for(int i = 0; i < n; i ++){
                    for(int j = i + 1; j < n; j ++){
                        int temp = matrix[i][j];
                        matrix[i][j] = matrix[j][i];
                        matrix[j][i] = temp;
                    }
                }
        
            }
LinkedList
------------------------------------------------------------------------------------------------------------------------

1. Reverse

a. 基本的reverse的格式


            ListNode pre = null;
            ListNode cur = head;
            while(!cur == null){
                ListNode tmp = cur.next;  //用tmp储存cur.next的部分
                cur.next = prev;  //接上前面的prev
                prev = cur;   //prev移动到当前位置cur
                cur = tmp;   //cur移动到之前存储的tmp那里
            }

b. ListNode从后往前建立的方法

            while(){
                ...
                cur.val = sum;
                ListNode pre = new ListNode(0);
                pre.next = cur;
                cur = pre;
            }



Two Pointer
------------------------------------------------------------------------------------------------------------------------   
1. FLoyd Cycle Detection Algorithm:
    a. 证明： https://www.youtube.com/watch?v=LUm2ABqAs1w 
   
   https://leetcode.com/problems/linked-list-cycle-ii/discuss/44774/Java-O(1)-space-solution-with-detailed-explanation.
    
    b. 做法： 中心就是两步:
      1. 先把fast和slow都指向head, 当fast和fast.next都不是null的时候, fast每次走两步, slow走一步, 一直到fast == slow, 如果fast或者fast.next是null的话证明到头了, 直接return null, 代表没有cycle, 也没有接入点 
         
      2. 当fast == slow的时候, fast调回到head, 然后fast和slow用一样的速度跑, 当fast和slow是一样的时候就return slow, 这时候的交汇点就是cycle的起始点 
    
      Runtime: O(n), LinkedList的长度, Space: O(1)

        public ListNode detectCycle(ListNode head) {
            ListNode fast = head, slow = head;
            while(fast != null && fast.next != null){
                fast = fast.next.next;
                slow = slow.next;
                if(slow == fast){
                    fast = head;
                    while(slow != fast){
                        slow = slow.next;
                        fast = fast.next;
                    }
                    return slow;
                }
            }
            return null;
        }



Graph
------------------------------------------------------------------------------------------------------------------------
   
1. UNF ：UNF就是借助一个class用DFS来检查一个directed或者undirected的graph是不是最终都指向一个祖宗， 也就是关注他们所有的edge之间的连通性


                class UNF{
                    int[] parents;
                    public UNF(int n){
                        this.parents = new int[n];
                        for(int i = 0; i < n; i++){
                        parents[i] = i;
                        }
                    }

                    public int find(int a){
                                while(parents[a] != a){
                                    parents[a] = parents[parents[a]];
                                    a = parents[a];
                                }
                                return a;
                            }
                            
                            public void merge(int a, int b){
                                int fa = find(a);
                                int fb = find(b);
                                if(fa != fb){
                                    parents[fb] = fa;
                                }
                            }
                        }
        


2. BFS : 用一个queue存储每一层或者每一次浏览的所有数据， 并且把他们的下一次浏览的数据加到queue里


    public List<List<Integer>> ReverseBinaryTreeLevelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        Stack<List<Integer>> stack = new Stack<>();
        List<List<Integer>> ans =  new ArrayList<>();
        if(root != null) queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            List<Integer> temp = new ArrayList<>();
            for(int i = 0 ;i < size  ; i++){
                TreeNode cur = queue.poll();
                temp.add(cur.val);
                if(cur.left != null) queue.offer(cur.left);
                if(cur.right != null) queue.offer(cur.right);
            }
            stack.push(temp);
        }

        while(!stack.isEmpty()){
            ans.add(stack.pop());
        }

        return ans;
    }


3. DFS : 对当前的方程进行无限制的recursion， 一直到当前的parameter没有办法使用为止


        public void dfs(TreeNode root){
            if(root == null) return;
            dfs(root.left);
            dfs(root.right);
        }q

        
     
   
   
        