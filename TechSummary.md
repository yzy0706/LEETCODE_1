
好的代码质量包括:
• Bug Free
• 好的代码风格(Coding Style)，包括变量名命名规范有意义，合理的使用空格，善用空行
• 容易让人读懂的逻辑。要把复杂的事情用简单的方式，别把简单的事情写复杂了。
• 没有冗余代码
• 有边界检测和异常处理













-----------------------------------------------------------------------------------------------------------------------------





  Stack{
  Initialization: Deque<E> Stack = new  ArrayDeque<>();
   加东西：push(E element)
    抽most recent：pop（）
    看most recent：peek（）
    boolean： isEmpty()


  validParentheses:
  中心思想：建一个stack（Deque<Character> Stack = new Deque，把所有遇到的括号左边'{','(','['都以'}',')'和']'的形式
  push到stack里(Stack.push(s.charAt(i)))







  }









----------------------------------------------------------------------------------------------------

BinarySearch（二分法）{
常用解法：把整个数列拆分为两半（一定要用mid = l + (r - l)/2，否则会出现加的次数太多stack根据overflow！！）。题目给的规则分别判断两段哪一段可能存在想要的结果，然后再继续分段


1. rotatedSortedArray{
 要求：return target在一个int[]里的位置
 已知：int[] nums, int target, nums是ascending, 但在某个点rotate了

 最理解的解法1 直接分段找连续性法：
 中心思想：已知nums是rotated了， 在一段递增的情况下，另外一段可以拥有比这个区间大或者小的数，所以我们建设一个分层判断：1.哪一段递增 2.target在不在递增的区间里，

 完整的边界状况：
 第一种情况：如果mid到end是连续增大,则start:a.且int[mid]<=target<=int[end],新的recursion:mid+1到end
                                b.target不在这个区间之内，新的recursion：start到mid-1
 第二种状况：如果mid到end不是连续增大：a. int[start]<=target<=int[mid], 新的recursion：start到mid-1
                                   b. 不在这个递增区间，则去不递增区间找

 易错点：
 1. find(int[]nums,int target, int start, int end)，如果start > end的话直接return -1
 2. 每次判断完int[mid]以后下次recursion换成 mid+1 或者 mid-1 有助于节省recursion的次数
}




2.closetBadVersion:
 要求：找到第一个是true的产品

 已知：n个产品， isBadVersion(int n)

 中心思想： 标记左右两边，找他们的mid，如果mid还是false的话 l = mid+1； 如果mid是true： r = mid；
----------------------------------------------------------------------------------------------------




 易错点：
 1. mid = l + (r-l)/2 , 如果是直接（l+r)/2的话会出现stack overflow，加的次数太多
 2. l = mid + 1;如果是l = mid的话会进入死循环。



}



----------------------------------------------------------------------------------------------------
















Tree.BFS{
常用解法：BFS和DFS最大的不同在于BFS要随时储存每一层的，而DFS是从一个node一查到底一直到null&null，所以BFS一般新建一个queue来承载所有的node



levelOrderTraversal：
中心思想：用一个queue去承载每一个level的所有node，while （queue！= null）每检查到一个node就把它poll（）掉，因为poll（）会删除，顺便把它的left和right都加到这个queue里
1.因为在forloop里还要再改变queue的size，所以在forloop之前应该有一个int size来承载这个queue的size，否则的话如果queue的size变了这个forloop的条件也变了，所以不能写(i < queue.size())


zigZagTraversal:
中心思想：如果是反的level就倒着放入queue，如果是正的level就正着放入queue，然后再每次把queue里面的TreeNode反过来读取，就可以zigzag
1.queue转换到list的技术放在技术里了


reverseLevelOrderTraversal：
中心思想：用一个linkedList queue存储每一个level的node，用一个temp每次将每个level所有的val一起填到一个stack里面去，然后再将stack里面的List<Integer>一个个抽出来到ans里
return。
1.一定要在对于queue的for loop结束以后往stack里添加temp，不然temp会加两次






















Tree.DFS{

maxDepthBinaryTree:
中心思想：如果null了就return 0，否则return recursion Math.max(maxDepthBinaryTree(root.left)+1, maxDepthBinaryTree(root.right)+1)
1.走recursion的时候找到了null才会return最大值

twoSameTree:
中心思想：达标的情况是两个node都是null了，return true；如果两个都不是null而且两个的val一样，才可进入下一个循环（Recursion)
1.&和&&是不一样的，&的情况下第一个是false还会检查第二个；&&的话第一个是false就不会检查第二个可能了，所以你打错可能会有runtime error

symmetricTree:
中心思想：达标的情况是两个node是null，return true，往上面递归，否则进入循环


sortedArrayToBST:
中心思想：如果start>end,return null, 否则进入新的recursion createBST（nums,start,mid-1) ; createBST(nums,mid+1,end),总之就是把一个int array由中间一直
对半分，然后把中间的那个数作为新的node。
1.记得是mid-1和mid+1，这样才有可能最后start>end，即start和end之间的差小于1的时候

subTreeofAnotherSubtree:
中心思想：在本身的fuction里面创造recursion来cover不同subtree的情况 return isSubtree(s.left,t)||isSubtree(s.right,t)；在helper里面创造recursion来不断查询每一个
left right的node是不一一对应，如果不是对应的话本次helper结束，回到原来的方程去比较别的subtree是不是一一对应

balancedBinaryTree{
已知：一个binary tree
求：他的每一个node的subtree的深度相差是不是超过1
中心思想：建一个int helper查每一个node的最深subtree，并由root开始recursive查每一个node是不是isBalanced
出现的bug：
  1. helper里有几个node判断null的时候就应该判断几个node，而不是判断他们的子node，那样会null pointer
}

}


























































----------------------------------------------------------------------------------------------------



UnionFind{
适用的类型： 跟连通性有关的（各个变量互相相通） 、 BFS类的
九章总结:
  基础框架：
  ——————————————————————————————
  Initialization：
  int x ;
  int[] f;
  for(int i = 0 ; i < n ; i++) {
     f[i] = i;
     }
  ——————————————————————————————
  find（路径压缩）：

  public int find(int x (目标值， 起始坐标） , int[] f){
  int j , fx ;

  j = x;

  while( f[j] != j ){
  j = f[j]; (一步步往上推，儿子变爸爸，爸爸变爷爷。。。）
  }
  //一路找到要求的爸爸x

  while( x != j){
  fx = f[x] ; (记录爸爸）
  j = f[x]; (新爸爸指向找到的祖宗j）
  x = fx; (坐标变成爸爸）；
  }
  //所有的x都指向新爸爸j

  return j;
  }
  ————————————————————————————————
  merge（合并爸爸）：
  public void merge ( int x , int y , int[] f){
  int fx = find(x,f); 找到x的祖宗；
  int fy = find(y,f);

  if(fx != fy) f[fx] = fy; y祖宗的爸爸是x祖宗
  }

 实用性：
 1. 一般都需要用一个有序号的map把每一个需要子参数并入到unf里面
题目：

connectingGraph{
中心思想：跟上面的基础结构一样， connect就是合并祖宗， query就是find祖宗并比较
1. 要建 int[n+1],  如果是n的话会超出界限, 因为是从1开始
}


connectingGraphII{
中心思想：基础结构还是基本一样， 但要建立一个size[]来初始每个node的size都是1， 再在connect的时候把每次要connect的儿子的size加上去，最后返回size[祖宗]
1. 因为connect是void才能每次都能改size，其他地方是不会记录size的改变的
}

connectingGraphIII{
中心思想：基本结构还是一样，在query（）的时候检查f[n] == n 则cnt++
出现的bug：
1.public了一个n，如果你不是在constructor里面赋值 this.n = n 而是 n = n ， 则之后其他方程引用的n都不会等于n而是等于0
}

accountMerge{
中心思想：
}

graphValidTree{
已知：一堆pair of nodes ， 两两相连
目的： 这些nodes能不能构成一个tree（两两相连或者有超过一个root都不行）
中心思想： 建一个unionFind， 查证两两的祖宗是不是一样， 一样就false； 或者最后union完了有超过一个以上的祖宗也不行
出现的bug：
1.建立unf的时候constructor要根据n来建，建f[] = new int[10000]不仅浪费时间而且根据n的不同还有可能出现error
2.union只能单向，否则会有runtime error
}

minmumSpanningTree{
已知：几个两两城市的connection和他们对应的cost
要得到： 连接所有城市的connection的list，以从小到大的cost的顺序排列（或以city1\city2的城市名字排列）

中心思想： 1.建立一个comparator按照题目要求的顺序排列connection 2.建立一个n = 0 记录城市的数量，把每个connection的城市名字不重复放入一个map<cityName,n++>里
3.建立一个father[]，提取每个connection里的城市名字的n，unionFind每个connection里面的两个城市的爸爸,如果爸爸不一样就union他们并把这个connection加到result里面
4.如果最后result的size（）！= n-1，证明没有连接所有的城市，直接return一个空的arrayList<>(),否则return result；

注意点：
1. 建立comparator时定义compare， 可以 return a-b；， 也可以使用compareTo来比较两个string来排列
}



----------------------------------------------------------------------------------------------------
Trie

适用的类型：字词的查找、添加建库
基础框架：

  class TrieNode{

  public trieNode[] sons;
  public boolean isWord;

  public trieNode(){
  sons = new trieNode[26];
  for(int i = 0 ; i < 26 ; i ++){
   sons[i] = null;
  }
  isWord = false;
} // constructor



}


wordSearchII{
已知： 一个字母组成的matrix ； 一个String[]词组
目的： return 这个matrix里有的词
中心思想： 建一个Trie把所有的词放进去，再由每个点进行recursion一个个的排查存在的词
出现的bug：
1. initialize了root两次，导致进入recursion的root是个空的
2. boolean[][] 要有length和width的大小
3. res直接initialize成了Arraylist而不是hashset，最后再return new ArrayList<String>(res), 导致结果不一样
4. recursion的结尾不把 sb.deleteCharAt(sb.length() - 1), 或者visited[][]不设置成false， 会导致stack overflow

}





----------------------------------------------------------------------------------------------------






















TwoPointer{

    九章总结：
    1.双指针类题题目：
    主副指针法： 一个forloop的指针每次走一步，另一个while的指针每次查到符合条件的指针为止
    快慢指针法： 要找倒数第N个，让一个指针先走N步，另一个还在头， 然后一起动
    找linkedlist的middle： 指针一一次移动两步，指针二一次移动一步，指针二就是middle（注意长度奇偶）

    minimumWindowSubstring{
    已知： String s, String t
    目的： 得到S中最短的包含t所有字母的substring
    中心思想：两个指针一起从0往右动，主指针for，副指针while，用两个int[]以map功能来记录t需要的、以及读取到的字母及其个数；对应的字母找齐了K+1 一直到 C == K 为止
    1. int[]可以当map用
    2. forloop最后要减去最左边的那个字节，这样l像右移以后还能继续检查，而且要检查是不是 cntS[s[l]] == cntT[s[l]]-1; 这样可以检查他是不是边界值
    3. r在whileloop结束以后到了目标字节的后一个，这样就是r-l而不是r-l+1了
    4. 要用ansl和ansr来记录头尾，直接每次跟Stirng res比较长度没法成功
    }





    threeSum{
       中心思想：sort以后，一个forloop i和由一个中间值和最右边最大值组成的slidewindow根据不同情况变化。
       (其实说白了就是sum=0，window两边都变；sum<0,window左边增加；sum>0;window右边缩小）
       1.java里面有continue
       2.承接的temporary object 在下一次使用之前一定要记得清空， 比如在threeSum里面的temList
       3.Arrays.Sort(int[])可以直接把int[]按大小排列
       4.在List已经被sort的情况下中间值和最右边的最大值组成的windown的变化：和过小，最左边往右移；和过大，最右边往左移
       5.else if偶尔可以用用
       6.平时要判断一个数值的时候最好先定义一个承接的object再用这个object去比较得出boolean比如说：
        int specialSum = nums[0]+nums[1]+nums[2];
                   if (specialSum==0)
                   先把 specialSum定义出来再去比较.
       7.在for loop的时候如果<length-1 i++， 意思是想要考虑 length-2的情况但不走length-1的loop
       8.Arrays.asList(nums[0],nums[i])
       9.检查重复的时候 是用middle-1和middle比， right+1和right比， 也就是上一个用过的和这个加减以后的值相比，而不是反过来.
       10.多用i+1而不是i++,++,--的意思是连这个数（variable）都被改变了，而不是单单+-
       11.每做一步的时候都要记得尽量去减少重复的情况
       12.sum==0的while loop查重完end和middle以后要再end--，middle++一次，不然只是查重完没有真正移动pointer，是死循环，runtime Error
    }



      threeSumClosest{
      中心思想：跟threeSum一样设置一个middle和一个right,如果大于target R--, 如果小于target L++， 唯一的区别是每次结束一轮之前i
      之前都要比一下Math.abs(sum-target)和Math.abs(result-target),然后再决定这个sum要不要取代result
      1. i+1:不改变i的值，print（i+1）完了之后i还是i，只是print（i+1）；i++，先把原来的i用上去，再改变i的值，比如说print（i++），就是print 0
      马上紧接着就会0+1=1，改变了i的值，在i++这个指令中；++i，与i++相反，先把i的值由0变成了1， 在把这个值拿去使用变成了print（1）
      }


      fourSum{
      中心思想：我的解法是在i的forloop基础上再加一个l（第二个数字）的forloop， 这样的话除了多一个forloop其他的基本跟threeSum一样，值得注意的是
      每次检查到i（第一个数字），l（第二个数字）时都记得检查重复率， 在sum==target时，m++，r--前 m（第三个数字），r（第四个数字）也需要检查
      重复率
      1.if和while不能混用， if是提供一个条件，在此基础上只做一次transaction，while是创造一个loop，在while提供的条件里可以做无限次transaction,
      比如说在l的forloop里， （sum>target)这个条件只需要判断一次，之后只有r--这一步transaction， 在这个基础上你只能选if而不是while
      }

      Container With Most Water{
      中心思想：总的来说是从最左端和最右端的height开始比较，把左右比较低的那个height往中间靠（目的是寻找一个更高的height），然后不断的比价maxarea和现有area的大小，
      和threeSum基本一样。
      1. if和else if表示两种不同的情况只会判断其中一个， 但if和if并列无论什么情况都会考虑两次，在这道题里right>left和left>right只可能出现一个情况，所以只能用else if
      而不是if
      }




      removeNthNodefromEnd{
             中心思想：首先在整个list之前加一个 Math.ListNode node= new Listnode（0），让这个node = head，新建一个mark node； 然后把这个node往后面一个一个的移， counter i=n的时候，
              mark = head， 之后mark=mark.next, 这样mark和node之间的距离永远都是n，一直到node走到尽头， 这时候mark等于倒数第n个，再用mark.next= mark.next.next就好
              1.最后一步要先检查mark.next != null;
              2.因为在最前面加了一个node（0），所以i=n的时候mark和node之间距离是n+1个位置（从0数起），这样的话我们才能操作mark.next=mark.next.next,因为我们要的是从1数起的第n个

             }



      mergeTwoSortedLists{
      中心思想：如果要合并两个list，则加起来的长度肯定是m+n了，那么就从nums1的m-1和nums2的n-1开始比较一个个往m+n-1个数里面放
      1.如果m已经检查到0了证明nums1的数都被搬到后面去了，这样的话只要把nums2的数再一个个放到nums1前面就好
      }




    }




















    Array{
    reorderLogfiles:
    中心思想：建立一个comparator以比较String的各个被split分开的部分。
    1. '数字'可以直接和'数字'比大小
    2. split(String, int) 会在碰到特定的String的时候将原来的String分成很指定的部分
    3. String之间可以用compareTo方程来比较字母的index
    4. 定义一个comparator：
       Comparator<String> com = (o1, o2) -> {
       String[] split1 = o1.split(" ", 2);
       String[] split2 = o2.split(" ", 2);

       if(split1[1].compareTo(split2[1]) == 0) return split1[0].compareTo(split2[0]);

       return split1[1].compareTo(split2[1]);
       };
       letters.Sort(com);

    intToRoman:
    中心思想：建一个1000，900，500，400到1的数列，再建一个M,CM到I的字符组，建一个forloop
    lookthrough每一个数，num大于这个nums[i]的时候num-=nums[i],StringBuilder sb append这个
    字符（strs[i]）
    1.如果这个数num不是>= nums[i],

    romanToInt:
    中心思想：建一个int[]和Character[]，再建一个<Character,Integer> map，每浏览s的一个character，把它对应的Integer调出来，比较它是不是比后面那个数小，
    小的话变成负数，否则不变。
    1.int res的值是最后一个字母对应的数，因为最后一个数必须是正值，而且如果再跟后面一个比就outofbound了
    2.else用不用要记得，不用的话肯定得run一遍

    parlindromeNumber:
    中心思想：从两边开始检查，每两个必须相等。
    1.while的条件是r>=l，而不是>
    2.Integer.toString(int i);

    longestCommonPrefix:
    中心思想：把res设为strs中的第一个string，如果indexOf(res)!=0; res减去最后一个字母
    1.注意设为while来减去字母，不是if，不然不会减完


    findFirstOccurenceString:
    中心思想：不用builtin（indexOf())的话就用substring一个个比较和needle相同长度的substring
    1. substring要头不要尾，所以forloop里是<haystack.length()-length+1,即i最后一位为haystack.length()-length
    }


    searchInsertPosition{
    中心思想：一个个比大小，如果相等或者小于就return i，记得把最后i =  nums.length - 1作为一个特殊的case讨论
  }



  lengthOfLastWord{
  中心思想：从最后一个index查起, 用一个end表示最后一位，用一个start表示第一位
  }


  reverseString{
  中心思想：从最中间开始expand，然后左右互换
  1.记得用一个a和b来先承接两边的char
}




twoSum{
   中心思想：用hashmap把每一个nums里面的Integer和对应的位置分别作为key和value，然后再用forloop找map包不包含
   1.target-nums[i]来找到每一个compliment
   }


  removeDuplicates{
  中心思想： 设置一个res，起始值为0；设置一个i的forloop，i=1, 在i<nums.length的情况下， 假如nums[i]!=nums[i-1],则res++，res++了以后，还要安排nums[res]=nums[i],
  意思就是第二个数字开始检查， 跟前面不一样的话res可以增长一位给第二个位置的数字赋予第二数字的值， 最后return res+1的长度， 因为nums[0]没有安排数值
  1.在这种情况下i<nums.length,因为必须检查nums.length-1情况， threeSum就必须检查nums.length-3的情况，所以是<nums.length-2，总之就是<什么数， 这个i的最大值就要检查到
  这个数-1的情况
  }

  removeElement{
  中心思想：和removeDuplicates一样，只是把判断nums[i]和nums[i-1]改成了判断nums[i]和val（parameter）
  }








































   LinkedLists{

   mergeTwoSortedLists:
   中心思想：把第一个head设为0，如果l1.val<l2.val，则l1为head，反之l2，这样l1，l2都要往后move（l1=l1.next）（这都是用来决定head是l1还是l2）；
   最后再用一个recursion head.next=mergeTwoSortedLists（l1，l2）来决定head之后的节点就好

   1.linkedlist每一个head都代表着一个linkedlist，如果想要改变只能去改变head.next然后最后再return这个head
   2.head一般新建 Listnode head = new Math.ListNode（0）；再去给head赋值。

















    Backtrack{

    letterCombinations：
    中心思想：建立一个backtrack的helper，有一个变量是index=0（这个index是用来在backtrack里面记录是否已经浏览完所有的digit的）。
    每次取这个String[]里面的第index个string，把这个string变成toCharArray，做一个forloop，每浏览到一个char，index+1，进入一个新的backtrack function。
    并把StringBuilder sb setLength（sb.length()-1）。这样的话每次浏览完第一个String的第一个字母，就会进入第二个String的第一个字母，
    当index=digits（变量String）的length的时候，选择return，总之就是在一个Backtrack fuction的foorloop里面再加一个backtrack的function，并且index+1。
    1.Integer.parseInt(Array s)
    2.StringBuilder()自己就是一个变量。


   combinationSum{
   要求：得到和等于target的所有集合
   已知：int[] candidates , int target
   中心思想：确定极限值的方法：用k一步一步的减下去, cur来代表现在找到candidates里面哪个数了，然后temp每次都把最后一个数去掉尝试新的组合

    }

   combinationSum2{
   要求：得到和等于target的所有集合，而且所有的数只要用一次
   已知：同combinationSum
   中心思想：唯二的区别就是 1：每次都从下一个数开始尝试和（之前是从这一个相同的数开始） 2： res.add(temp)；之前要查重
   }

    generateParenthesis{
    要求：得到所有可能的括号组合
    已知：括号的个数 int n
    中心思想：建立一个int left等于n的值，建立一个int right等于0。 多一个左括号left-1，right+1；多一个右括号left不变，right-1


    }










----------------------------------------------------------------------------------------------------



    Math数字题{
    reverseInteger:
    中心思想：建立一个temp然后让他跟着x%10一起*10，然后x再除以10将上一位排到小数点以后
    1. x%10不会把小数点以后的数字一起退回来，给的是整数
    2. 建立temp要建立一个long而不是int，最后退回int
    3. int 2^32, 10 digit         float 6 after decimal, 2^32
       long 2^64, 10 digit        double 16 after decimal, 2^64
       short 2^16, 5 digit







    happyNumber:
    已知：一个数，各个digit的平方一直相加
    目的：证明这个数会不会变成1
    中心思想：可以直接brutal force把10^n的余数一个个列出来再减掉，也可以直接n/10， n%10不会得到小数点以后的位置
    出现的bug：
    1. while loop 直接用while(set.add(n)), 如果while（n != 1)的话会进入死循环（set.add(n)会return false 如果有重合）
    2. pow(底数，幂） return一个double， 想要int就 (int)pow(a,b).

    }



-------------------------------------------------------------------------------------------------------



Matrix矩阵题{
常用解法：
1.由每个点出发向各个方向拓展（recursion）
2. Tree.BFS,用queue


 numberOfIslands:
  要求：找到matrix里岛的个数

  中心思想：由每个'1'点出发把每个'1'点变成0；forloop的时候碰到一个'1'就res++

  出现的bug：
  1. 首先考虑 gird.length < 1就return， 然后再去考虑grid[0].length， 一起判断的话会导致grid[0]可能测不出来ƒ
















————————————————————————————————————————————————————————————————————————————————————————————————————




  rottenOrange_mock{
  要求：找到多久以后matrix里所有的橘子会变烂

  中心思想：用一个 Queue<pair> queueRotten = new LinkedList<pair>() 来承载所有的坏掉的橙子， 再在一个whileloop里加一个forloop，
  模拟每过一秒所有的rotten传染身边的fresh橙子，看最后fresh是不是剩下0

  出现的bug：
  1. x对应的是 Array.matrix.length, y 对应 Array.matrix[0].length;

}












































    DynamicProgramming{


    reverseLinkedList:
    中心思想：forloop比较是比最小的的price低还是prices[i]-minprice比res更大


    maxSubarray:
    中心思想： 用一个cur来标记当前的和， 用一个res来标记见到的最大和，cur = cur + nums[i], 如果nums[i]本身就比cur大， cur = nums[i],　反之保持nums[i].
    最后再检查 cur是不是比res大更改res。
    这个题的核心只在于如果nums[i]已经比cur（cur = cur + nums[i]) 大了前面的都是累赘，就去掉。

    1.错误点在于不是去比较cur和cur+nums[i],而是去比较cur和num[i],我们只关注是不是需要把cur变成nums[i],而不是cur是不是越来越大，那在这道题里没有意义
    2.放一个res去存储最大值比用list存要方便


    climbingChairs:
    问题：n个阶梯， 每次走1步或者2步， 有多少种走的方式
    中心思想： 其实每一个给的n个阶梯的路径之和 = n-1 + n-2 subtree的路径数量之和，最开始的0，1，2是确定的subtree， 就用一个list表示各个subtree的 路径数量
     0 ，1 ，2当作list[0],list[1],list[2]的路径数量的叠加



    longestValidParentheses:
    中心思想：用一个int[] dp 来储存每个'）'之前连续的括号的个数，只存')'上的

    1. (i - dp[i-1] - 2) >= 2? dp[i-dp[i-1]-2] : 0

    }

















    Greedy{
    assignCookies:
    中心思想：用一个j一个i分别找size和greedy的数列，如果不符合条件j++
    1.记得先sort






    meetingRooms:
    中心思想：用一个PriorityQueue来存储每个room结束的时间，如果不符合条件就room++，加个时间；如果符合条件就 pq.poll() 然后再加个时间intervals[i][1]
    1.Arrays.Sort(intervals, (a,b) -> a[0] - b[0])
    2. Queue的peek（）只查不删，poll（）边查边删


    }





























    hashTable查重{



    jewelsAndStone:
    中心思想：把J放到hashset里看有没有重复
    1.hashset是查重最快的方式
    2.hashset的initialization是Set
    3.for（char ch：S.toCharArray() ） toCharArray()只是在用了这个method call的这一刻变成了CharArray 其他时候还是String








    uniqueEmail:
    中心思想：hashset相同的东西只会加一次，再在int i loop里用switch考虑每一个情况， '+' condition里面
    1. switch(ch){

       case '.' :
       ...
       break;

       case '@' :
       ...
       break;

       case '+':
       ...
       break;

       default:
       }








    }































    HashTable.mapUseArray{
    重要：看到这种从int[]删除某些条件的数字的题就应该想到新建一个新的数列pointer：res去赋值

     removeDuplicates{
          中心思想：用一个int res=1的值表示不重复list的长度，用forloop每找到一个不重复的，先int[res]= int[i]，再让res++表示长度为2就好
          （如果int i=0就是表示着list每一个不同数字的
          位置，这样最后return i+1 就好）

          }



     removeElement{
         中心思想：这种题的中心思想都是给一个想要（修改以后）的list的位置pointer： res， 在nums中如果nums[i]符合修改以后的要求就把它安排到nums[res]的位置上，
         res++，不符合的就留在原来的int[]不带走
              }



    }


















-----------------------------------------------------------------------------------------------------------------------------------------------------------






    Heap{

    kClosestPointsToOrigin:
    中心思想： 用 comparator去sort这个list of points，找到第0到第K个最近的
    1.Arrays.Sort(points, Comparator.comparing(a -> a[0]*a[0] + a[1] *a[1]))
    2. res = new int[K][2]来确定是K个小数列并且每个数列里有两个数分别代表XY坐标



    kthSmallestMatrix:
    中心思想：建立一个priorityQueue<pair>（k , comparator)来存储各个从matrix[0][0]开始的拓展坐标（拓展的时候还需要一个boolean[][])，循环k-1次，
    每次选取最大的那个坐标拓展就得到第k小的.
    出现的问题：
    1.是k-1次
    2.PriorityQueue<pair>(k(size在前）,comparator)

    kthSmallestSum：
    中心思想：基本思想和kthSmallestNatrix差不多，一个坐标是matrix里的x,y， 另外一个是a,b(在两个int[]里面的pointer位置


    slidingWindowMeidan:
    已知：一个int[] nums, k大小的sliding window。
    求：每k个数的median
    中心思想： 用一个同向双指针确定k个数的区间，用两个heap随时传递i的数，maxHeap放前面，minHeap放后面
    bug:
    1. 注意k是even，odd的时候的不同情况
    2. PriorityQueue的默认是从大到小，如果要建立 maxHeap就是  Queue<Integer> maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder) = new PriorityQueue<Integer>((a,b) -> (b.compareTo(a));



   trappingRainWaterII:

   出现的bug：
   1. boolean[][] visited = new boolean[m][n],前面两个空括号要记得打[][]
   2. class com implements Comparator<pair>{} <pair>要记得写
   3. 写constructor的时候写成了 val = this.val; 应该是 this.val = val;




   wordBeak_dp:
   中心思想：拿一个queue来装载start和end

____________________________________________________________________________________________________________

技术:

杂：

1.想要做到 Bug Free 最重要的是优化你的 Coding Style， 子函数 + 好的命名风格

2.同时的两个赋值方程可以写在一起  比如 int i= 0； int j =0; 可以写成 int i = 0 j=0;

3.while loop只是设置条件和极限值，parameter在loop里面也需要有变化（R++，L--之类的）

4.平常有什么不确定的参数可以用一个最简单的例子试着跑一下
      
5. lock的用法：

 a. 在enqueue的方程里:
       

                   synchronized(lock){
                     while(deque.size() == size) lock.wait();
                     ...
                     deque.notify();
                   }
       
 b. 在deque的方程里
            
                   
                   
                    synchronized(lock){
                           while(deq.isEmpty()){
                               lock.wait();
                           }
                           res = deq.removeFirst();
                           lock.notify();
                    }

6. Random的用法:
    

               Random random = new Random();

               public int[] shuffle() {
                   for(int i = 1; i < nums.length; i++){
                       int j = random.nextInt(i + 1);
                       int temp = nums[i];
                       nums[i] = nums[j];
                       nums[j] = temp;
                   }
                   return nums;
               }

Concept
----------------------------------------------------------------------------------------------------

1. Overloading and Overriding:  
   
   Overloading: When two or more methods in the same class have the same name but different parameters, 
   it's called Overloading. 
   
   Overriding: When the method signature (name and parameters) are the same in the superclass and in the child class, 
   it's called Overriding.
   





   
2. Polymorphism : Object's ability to be taken on many forms.







3.  NoSQL: non-relational: 
    
    1. simple and quick search 
    
    2. different types of data
   
    3. key-value(Redis); file-oriented(MongoDB)
    
    4. High traffic
    
    SQL: relational:
   
    1. complicated CURD(JOIN)
       
    2. bad for high traffic
    





4. Abstract Class vs Interface:
   
   An abstract class allows you to create functionality that subclasses can implement or override. 
    
   An interface only allows you to define functionality, not implement it.
   
   And whereas a class can extend only one abstract class, it can take advantage of multiple interfaces.






5. Process（进程） means a program is in execution, whereas thread（线程） means a segment of a process. 
   A Process is not Lightweight, whereas Threads are Lightweight.  
   A Process is mostly isolated, whereas Threads share memory. 
   Process does not share data, and Threads share data with each other.
   




   
6. 底层数据结构：

   1. List(有序,可重复)
   
   a. ArrayList 底层数据结构是array,查询快,增删慢（因为：增删后涉及到其他数据的位移）
   线程不安全,效率高
   
   b. LinkedList
   底层数据结构是双向链表,查询慢,增删快
   线程不安全,效率高


   2. Set(无序,唯一)
   
   a. HashSet 底层数据结构是 HashTable
   
   HashTable依赖两个方法：hashCode()和equals()
   
   执行顺序：

   首先判断hashCode()值是否相同

   是：继续执行equals(),看其返回值

   是true:说明元素重复，不添加

   是false:就直接添加到集合

   否：就直接添加到集合

   最终：

   自动生成hashCode()和equals()即可

   b. TreeSet 底层数据结构是红黑树。(是一种自平衡的二叉树)


   3. Map

   a. HashMap 底层数据结构是:(数组+单向链表) HashTable

   线程不安全，效率高

   b. TreeMap
      底层数据结构是Binary Tree, 红黑树(是一种自平衡的二叉树).





7. Design Pattern: 
   
    a. Singleton（单例） : one object exists 、

   优点：
   
   1. 所有对象访问一个受控访问的实例, 允许多个实例
    
   2. class(类)对实例化有一定的伸缩性
    
   3. 节省系统资源， 避免交叉访问， 节省共享资源
   
   缺点：
   
   1.不适用于变化的对象，如果同一类型的对象总是要在不同的用例场景发生变化，单例就会引起数据的错误，不能保存彼此的状态。
   
   2.由于单例模式中没有抽象层，因此单例类的扩展有很大的困难。
   
   3.单例类的职责过重，在一定程度上违背了“单一职责原则”。
   
   4.滥用单例将带来一些负面问题，如为了节省资源将数据库连接池对象设计为的单例类，可能会导致共享连接池对象的程序过多而出现连接池溢出；
   如果实例化的对象长时间不被利用，系统会认为是垃圾而被回收，这将导致对象状态的丢失。

    b. Factory: Create related dependent objects





8.  Scaling:

    Vertical: add more resource

    Horizontal: add more instances



   
  
9. Processor: 16、32、64位处理器分别代表可以处理16、 32、 64位字




10. 计算机系统：
        
        1. 信息储存的方式： 源程序就是0和1组成的位（byte）序列， 文本字符都用ASCII标准来表示， 计算机系统里的所有的信息都是存储在比特里（字节序列）
        
        2. 编译系统的组成： 

        Unix上GCC编译器转换源文件(文本）到目标文件（二进制）：

        hello.c（原文本）-> 预处理器(cpp) -> hello.



    
11. 数字的大小： 一个字节八位

   short, char: 2 字节

   int, float : 4字节

   long, double : 8字节





12. 互斥锁和自旋锁：

自旋锁是一种互斥锁的实现方式而已，相比一般的互斥锁会在等待期间放弃cpu，自旋锁（spinlock）则是不断循环并测试锁的状态，这样就一直占着cpu。

互斥锁：用于保护临界区，确保同一时间只有一个线程访问数据。对共享资源的访问，先对互斥量进行加锁，如果互斥量已经上锁，调用线程会阻塞，直到互斥量被解锁。在完成了对共享资源的访问后，要对互斥量进行解锁。

临界区：每个进程中访问临界资源的那段程序称为临界区，每次只允许一个进程进入临界区，进入后不允许其他进程进入。

自旋锁：与互斥量类似，它不是通过休眠使进程阻塞，而是在获取锁之前一直处于忙等(自旋)阻塞状态。用在以下情况：锁持有的时间短，而且线程并不希望在重新调度上花太多的成本。"原地打转"。

自旋锁与互斥锁的区别：线程在申请自旋锁的时候，线程不会被挂起，而是处于忙等的状态。

信号量：信号量是一个计数器，可以用来控制多个进程对共享资源的访问。它常作为一种锁机制，防止某进程正在访问共享资源时，其他进程也访问该资源。因此，主要作为进程间以及同一进程内不同线程之间的同步手段。

未加互斥锁：
    

        public class LockDemo {
        
        //private static Object lock = new Object(); // static确保只有一把
        private int i = 0; 
        
          public void increaseI() {
            //synchronized (lock) {

                for(int k=0;k<10;k++) {  // 对i执行10次增1操作 
                i++; 

            //}
            System.out.println(Thread.currentThread().getName() + "线程，i现在的值：" + i); 
          }

            public static void main(String[] args) {
                LockDemo ld = new LockDemo();
                int threadNum = 1000;   // 选择1000个线程让结果更加容易观测到
                MyThread[] threads = new MyThread[threadNum];
                for(int i=0;i<threads.length;i++) {
                    threads[i] = new MyThread(ld);  // 所有线程共用一个LockDemo对象
                    threads[i].start();
                }
            }


        class MyThread extends Thread {
            LockDemo ld;

            public MyThread(LockDemo ld) {
                this.ld = ld;
            }
            public void run() {
                ld.increaseI();
            }
        
        }




  // 加了互斥锁
        
        public class LockDemo {

            private static Object lock = new Object(); // static确保只有一把锁
            private int i = 0;

            public void increaseI() {
                synchronized (lock) {
                    for(int k=0;k<10;k++) {  // 对i执行10次增1操作
                        i++;
                    }
                    System.out.println(Thread.currentThread().getName() + "线程，i现在的值：" + i);
                }
            }
        
            public static void main(String[] args) {
                LockDemo ld = new LockDemo();
                int threadNum = 1000;   // 选择1000个线程让结果更加容易观测到
                MyThread[] threads = new MyThread[threadNum];
        
                for(int i=0;i<threads.length;i++) {
                    threads[i] = new MyThread(ld);  // 所有线程共用一个LockDemo对
                    threads[i].start();
                }
            }
        }



        class MyThread extends Thread {
        
            LockDemo ld;
            public MyThread(LockDemo ld) {
                this.ld = ld;
            }

            public void run() {
                ld.increaseI();
            }
        }




自旋锁：
    
    import java.util.concurrent.TimeUnit;
    import java.util.concurrent.atomic.AtomicReference;
    
    public class SpinLockTest {
    
        /**
         * 自旋锁：
         * 是指尝试获取锁的线程不会立即阻塞，而是采用循环的方式获取锁，这样的好处是减少线程上下文切换的消耗，缺点是循环耗用CPU
         * 而在一个多核的机器中，多个线程是可以并行执行的。如果当后面请求锁的线程没拿到锁的时候，不挂起线程，而是继续占用处理器的执行时间，
         * 让当前线程执行一个忙循环（自旋操作），
         * 也就是不断在盯着持有锁的线程是否已经释放锁，那么这就是传说中的自旋锁了。
         * @param args
         */
         
        /**
         * 1.6 版本后使用了自适应自旋锁：
         * 自旋次数通常由前一次在同一个锁上的自旋时间及锁的拥有者的状态决定。如果线程【T1】自旋成功，自旋次数为17次，那么等到下一个线程【T2】自旋时，
         * 也会默认认为【T2】自旋17次成功，
         如果【T2】自旋了5次就成功了，那么此时这个自旋次数就会缩减到5次。
        自适应自旋锁随着程序运行和性能监控信息，从而使得虚拟机可以预判出每个线程大约需要的自旋次数
         * @param args
         */

        public static void main(String[] args) {
            SpinLockTest sl= new SpinLockTest();
            // 给线程赋予AA名称
            new Thread(()->{
                sl.myLock();
                try {
                    // 休眠5 秒
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                sl.unMyLock();
            },"AA").start();
             
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
             
            new Thread(()-> {
                sl.myLock();
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                sl.unMyLock();
            },"BB").start();
             
             
             
        }
        //  AtomicReference  原子方式更新对象引用 ，保证多线程下操作该对象进行更新时，避免出现脏读，只要有一个线程改变 ，
        //其他现场不会在改变该值
        AtomicReference<Thread> atomicReference = new AtomicReference<>();
         
        // null 表示锁未被线程持有，  atomicReference.compareAndSet(t,null);  线程的持有状态改变为null
        public void  myLock() {
            Thread t=   Thread.currentThread();
            System.out.println(t.currentThread().getName()+ "come in");
            while (!atomicReference.compareAndSet(null, t)) {
            }
        }
        public void unMyLock() {
             Thread t=  Thread.currentThread();
             atomicReference.compareAndSet(t,null);
             System.out.println(t.currentThread().getName()+ "invoked unMyLock() ");
        }
    
    }

13. 做过的锁：


    Deque<Integer> deq;
    int size;
    Object lock;

    public void BoundedBlockingQueue(int capacity) {
        size = capacity;
        deq = new LinkedList<>();
        lock = new Object();
    }

    public void enqueue(int element) throws InterruptedException {
        synchronized(lock){
            while(deq.size() == size){
                lock.wait();
            }
            deq.addLast(element);
            lock.notify();
        }
    }

    public int dequeue() throws InterruptedException {
        int res = 0;
        synchronized(lock){
            while(deq.isEmpty()){
                lock.wait();
            }
            res = deq.removeFirst();
            lock.notify();
        }
        return res;
    }

    public int size() {
        return deq.size();
    }

    Deque<Integer> deq;
    int size;
    Object lock;

    public void BoundedBlockingQueue(int capacity) {
        size = capacity;
        deq = new LinkedList<>();
        lock = new Object();
    }

    public void enqueue(int element) throws InterruptedException {
        synchronized(lock){
            while(deq.size() == size){
                lock.wait();
            }
            deq.addLast(element);
            lock.notify();
        }
    }

    public int dequeue() throws InterruptedException {
        int res = 0;
        synchronized(lock){
            while(deq.isEmpty()){
                lock.wait();
            }
            res = deq.removeFirst();
            lock.notify();
        }
        return res;
    }

    public int size() {
        return deq.size();
    }






14. HTTPS和HTTP区别：
    
    1. https需要收费的CA协议证书
    
    2. http使用80号端口， https使用443号端口
    
    3. http是超文本传输协议， 是明文传输， https是http + ssl的加密传输、身份认证协议， 更加安全
    




    
15. HTTPS加密原理：

    1. 加密方法：
    
        a. 对称加密： 双方商定一个密钥， 速度快， 不安全
        
        b. 非对称加密： RSA算法等生成公钥和私钥， 公钥信息要私钥解密， 私钥信息要公钥解密， 速度慢， 资源占用多但是安全
    
    2. 加密过程：
    
        1. 客户端发出https请求， 服务器通过RSA算法产生公钥和私钥， 发送回客户端
    
        2. 客户端验证证书， 用公钥加密自己产生的通信密钥， 发送到服务器
    
        3. 服务器用私钥解密公钥得到通信密钥 
           
    3. 使用非对称加密仅仅获得一个用对称加密生成的秘钥？ 
    
    耗时长， 只用来传输密钥而不是数据
   






16. TCP和UDP的区别：

    1. TCP 是面向连接的（TCP建立连接时有三次握手， 断连时有四次握手），UDP 是面向无连接的
    
    2. UDP程序结构较简单, 只有两个端口号
    
    3. TCP 是面向字节流的，UDP 是基于数据报的
    
    4. TCP 保证数据正确性，UDP 可能丢包
    
    5. TCP 保证数据顺序，UDP 不保证
    
    6. TCP通过滑动窗口控制数据传输的大小
    




    
17. TCP为什么是可靠连接：
    
    1. TCP的报文头序号保证数据不丢包按顺序到达
    
    2. 通过滑动窗口实现流量控制和堵塞控制
    




    
18. NSString为什么用copy修饰：
    
使用copy修饰之后,即使属性拷贝来自可变字符串,也会被深拷贝成不可变字符串,也就是源字符串修改之后不会影响到属性字符串,增强了代码的健壮性。








19. Runloop和线程的关系： 一个runloop对应并管理一个核心线程，runloop创建以后线程准备执行任务。
    主线程的runloop在程序启动的时候就加载好了， 子线程的runloop在使用时创建， 任务完成以后销毁
    



    
20. 交换机和路由器的区别： 交换机是各自拨号使用自己的宽带账号， 形成一个局域网，工作在中继层； 
    而路由器是共享一个宽带账号， 工作在网络层
    




    
21. Top K out of massive amount of integers : 建立size为k的min heap，每次都去比较堆顶的最小值， Runtime: O(nklog(k)), 优化就是分在1000(eg.)个文件里，
分别找到最小值以后再汇总
    





22. 浅拷贝： 只复制指针； 深拷贝： 复制整个object的数据







23. power()函数的实现： 

    1. 首先判断底数（base）是不是0， 是的话直接return 0；
    
    2. 判断exponent大于等于或者小于0， 如果小于0用一个boolean negative记录
    
    3. 如果negative, res = 1/res;
    




    
24. static用来修饰成员变量或方法，主要作用是在不创建对象的情况下，类名.变量名/方法，就可以访问。被static修饰的成员变量或方法，是在类级别的。

注意几点：

    1.static关键字修饰时，在类被初次加载时，顺序执行代码快，且只执行一次。

    2.static 修饰的成员变量或方法实在编译时就动态绑定了的。
    所以在被static修饰的方法中不能调用非static修饰的变量，（原因：编译期非static变量还未被创建）而在普通方法中可以调用被static修饰的变量。

    3.构造方法默认为static类型。

    4.java中不允许static修饰局部变量（语法规定）

    5.static关键字不依赖于对象，所以不能用this访问。







25. TCP粘包问题和断包问题：

    1. 粘包： 缓存里的数据包和当前数据包一起发送了， 会导致通道的堵塞

    2. 断包： 当前数据包过大导致发送方缓冲区存不下， 所以截取发送


解决方法：

    1. 记录包的大小， 接收方根据包的大小进行截取处理

    2. 发送的包加入特殊字符， 接收方根据特殊字符处理









26. http里GET和POST请求都是TCP的链接, GET传输的参数在url里， 比较没有隐私性， 产生一个TCP数据包；
    POST通过request body来传输参数， 比较安全， 产生两个TCP数据包










27. 进程间通信： 

    1. 匿名管道： 两个程序分享数据

    2. 有名管道： 允许无亲缘关系程序通信
    
    3. 高级管道： 把一个程序当作另外程序的子进程
    







28. 死锁： 多个进程因为争夺资源而形成的僵局

    产生原因： 1. 竞争资源  2. 推进顺序非法

    必要条件： 
    
    1. 互斥条件：进程要求对所分配的资源进行排它性控制，即在一段时间内某资源仅为一进程所占用。
    
    2. 请求和保持条件：当进程因请求资源而阻塞时，对已获得的资源保持不放。
    
    3. 不剥夺条件：进程已获得的资源在未使用完之前，不能剥夺，只能在使用完时由自己释放。
    
    4. 环路等待条件：在发生死锁时，必然存在一个进程--资源的环形链。

    解决方法： 1. 一次性分配资源， 资源尽量不共享 2. 可剥夺资源： 如果当前程序得不到别的资源， 直接放弃当前资源






29.  JVM原理：


程序计数器

占用内存小，线程私有，

生命周期与线程相同

大致为字节码行号指示器






虚拟机栈

线程私有，生命周期与线程相同，使用连续的内存空间

Java 方法执行的内存模型，存储局部变量表、操作栈、动态链接、方法出口等信息







java堆

线程共享，生命周期与虚拟机相同，可以不使用连续的内存地址

保存对象实例，所有对象实例（包括数组）都要在堆上分配







方法区

线程共享，生命周期与虚拟机相同，可以不使用连续的内存地址

存储已被虚拟机加载的类信息、常量、静态变量、即时编译器编译后的代码等数据






30. 单纯问一个字占多少字节是没有意义的,因为字的大小取决去具体系统的总线宽度,如果是32位的系统,则一个字是4个字节,如果是64位,则是8个字节.




31. cpu工作状态分为系统态（或称管理态，管态）和用户态（或称目态）。 引入这两个工作状态的原因是：为了避免用户程序错误地使用特权指令，保护操作系统不被用户程序破坏。
    
    具体规定为：当cpu处于用户态时，不允许执行特权指令,
    当cpu处于系统态时，可执行包括特权指令在内的一切机器指令


Graph（记得优先建数据结构： 两种图的表示方式 ： adjacent Array.matrix, adjacent list)
----------------------------------------------------------------------------------------------------

1.最短路径的三大算法：

   a. Dijkstra:

   条件：一般是去找起始点到某个点的最短的路径，然后所有的点之间距离都是正值

   start的数据结构： V大小的map，并且initialize成(i,Integer.MAX_VALUE)； 设置成公用参数

   核心算法：

           while(!stops.isEmpty()){
               int curStop =  stops.poll();
               visited[curStop] = true;
               if(map.containsKey(curStop){
                 for(int[] time : map.get(curStop)){
                   if(distances.get(time[0]) > distances.get(curStop) + time[1]){
                      distances.put(time[0], distances.get(curStop) + time[1]);
                      }
                   if(!visited[time[0]] stops.offer(time[0]);
                   }
           }


   Runtime: O(V+E)log(V), 这是pq的解法的复杂度，V是节点的个数，E是每个节点分别能去到的最多的其他节点的个数， 如果是forloop的解法就是O(V^2)


   b. Floyd-warshall：
   条件：可以有负的权重，其实就是Dijkstra循环了n次并放在一个dp[][]的数组里
   start的数据结构：一个动态规划的dp[V][V]
   核心算法：
   //a就是n个stop循环一下

        
           for(int a = 0; a < n; a++){
             for(int i = 0; i < n; i++){
               for(int j = 0; j < n; j++){
               dist[i][j] = Math.min(dist[i][j], dist[i][a] + dist[a][j];
               }
             }
             }


     Runtime：O(n^3)，相当于所有的node循环了立方次

   c. Bellman-ford

   条件：也可以有负权重，

   核心算法：
   对每条边进行n-1次松弛，如果距离有变化就是闭环

   Runtime: O(V*E)




2. 组成MST的两大算法（也是算最短距离， 但这两个是有中转站的数量限制的）
   
    a. Kruskal
   
    条件：双向图单向图都可以， 主要是去增加两个常量cost和checkNum叠加一个每条边（也就是2个vertice）的weight和已经检查过的边的数量，并建立一个unf，特别记得要sort
   
    数据结构：除了unf不用建新的， forloop每条边就可以
   
    核心算法：
   
            public void merge(int a, int b){
                int rootA = find(a), rootB = find(b);
                if(weights[rootA] > weights[rootB]){
                  parents[rootB] = parents[rootA]; // 合并小的root到大的root去
                  weights[rootA] += weights[rootB]; //并且把小的weight加到大的上面
                }
                else{
               ...  //反过来
                }
            }
            ...

            for(int[]  connection  : connections){
               if(unf.isSameTree(connection[0], connection[1])) continue;
               unf.merge(connection[0], connection[1]);
               cost += connection[2];
               checkNum++;
               }
   
     Runtime: O(Elog(v)) = O(log(v)(E+V)), 然而space是O(E), parents的大小




   b. Prim：

   条件: 做的那道题是单向图，不过双向图应该也可以， 其他部分都跟dijkstra一样， 只是不需要建立一个dist来比较并更新src到各个点的距离，反之我们是在叠加每个点和他最近的点的距离

   数据结构： pq和map都一样， 但我们不需要建立dist[]，而是建立一个新的int[邻居的位置， cost,  checkNum]; 如果中转站的数量checkNum > k则continue, 一开始应该都是offer

   src的邻居 new int[]{cur[0], cur[1], 0};
    
   核心算法：

     ...
     for(int[] cur : map.get(src)){
        stops.offer(new int[]{cur[0], cur[1], 0};
     }

     while(!stops.isEmpty(){
     int[] curStop = stops.poll();
     int curPos = curStop[0], cost = curStop[1], checkNum = curStop[2];
     if(checkNum > K) continue;
     if(curPos == dst) return cost;
     for(int[] neighbour : map.get(curPos)){
        pq.offer(new int[]{ neighbour[0], cost + neighbour[1], checkNum+1});
     }

     return -1;
     }

     Runtime: 因为有比较所以是O(log(V)V+log(V)E), space是O(E+V)， 所有当前邻居的最大值

Tree Traverse
----------------------------------------------------------------------------------------------------

1. Tree的题一定要灵活利用左子树是start到i-1，右子树是i+1到end这一个特性， 无论是dp
还是recursion还是forloop来做permutation


Tree DFS
----------------------------------------------------------------------------------------------------
1. helper查null的时候helper有几个node就查几个node == null， 不然会null pointer
   
2. DfS如果helper里有arraylist等结构的话因为是引用参数而不是传值参数是需要在每个helper的末尾像backtrack一样去掉尾巴的
不然这个引用参数会在别的循环中叠加使用， 没法分开使用， 详见PathsumII
   
3. DFS每一次递归到底端了以后是会一层一层的回去的
   
4. UNF就是借助一个class用DFS来检查一个directed或者undirected的graph是不是最终都指向一个位置


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



Tree BFS
----------------------------------------------------------------------------------------------------
1.BFS在forloop去poll() queue里面的东西之前一定要注意用一个int在while loop里确认好queue的size

2.常用的whileloop格式
  Queue<> queue = new LinkedList<>();
  while(!queue.isEmpty()){
  int size = queue.size();
  for(int i = 0; i < queue.size(); i++){
  ...
  }
  ...
  }

3.拓扑排序 主要是添加了一个indegree:

    1. 建立一个indegree[],先根据给的数据建一个map或者matrix, 把对应的条件参数++
    Map<Integer, List<Integer>> map = new HashMap<>();
            int[] indegree = new int[numCourses];
            for(int i = 0; i < prerequisites.length; i++){
                if(!map.containsKey(prerequisites[i][1])) map.put(prerequisites[i][1], new ArrayList<>());
                map.get(prerequisites[i][1]).add(prerequisites[i][0]);
                indegree[prerequisites[i][0]]++;
            }

     2. 建立一个priorityQueue, 把indegree里面是0的参数都加进去, 也就是起点
     Queue<Integer> bfs = new LinkedList<Integer>();
             for(int i = 0; i < indegree.length; i++ ){
                 if(indegree[i] == 0) bfs.offer(i);
             }
             int numsTaken = 0;

     3. Whileloop pq和map, 把变为0的参数都bfs一下, numsTaken记录一下
    while(!bfs.isEmpty()){
                int courseTaking = bfs.poll();
                numsTaken++;
                if(!map.containsKey(courseTaking)){
                    continue;
                }
                for(int i : map.get(courseTaking)){
                    int toBeTaken = i;
                    indegree[toBeTaken]--;
                    if(indegree[toBeTaken] == 0){
                        bfs.offer(toBeTaken);
                    }
                }
            }
            if(numsTaken == numCourses) return true;

Comparator
----------------------------------------------------------------------------------------------------
 1. Arrays.sort(points, Comparator.comparing(a -> a[0]*a[0] +a[1]*a[1]))

 2. Arrays.sort(intervals, (a,b) -> (a[0] - b[0]));

 3. List<Integer> list = new ArrayList<>();
   Collections.Sort(list);    (Hashset<>()不能sort）





 4. comparator: reorderLogfiles:
                                 Comparator<String> com = (o1,o2) -> {
                                 String[] split1 = o1.split(" ",2);
                                 String[] split2 = o2.split(" ",2);
                                 return split1[1].compareTo(split2[1]);
                                 };
                                 letters.Sort(com);









 5. KthSmallestSum: 定义一个comparator的class并且implement它：
    建立这个comparator class：


    class com implements Comparator<pair>{
    public int compare (pair a, pair b){
       return a.val - b.val;
    }
    }

    应用这个comparator class：
    

    public ...{
    PriorityQueue<Heap> minHeap = new PriorityQueue<Heap>(k,new com()); （注意！： k在前面）f
    }

6. meetingRoomsII 也可以在 Arrays.Sort()里面直接加comparator：
   

           Arrays.Sort( end,
                 new Comparator<Integer>(){
                                        public int compare(int a, int b)
                                         return a - b; 
                });

7. Arrays.Sort(char[]) 也是可以的
   
8. 要sort一个特定的list并且要新建comparator最好不要用arrays.Sort(listName, Comparator.comparing(...))， 而是listName.Sort(com)
   
9. 如果pq是minHeap， 那么他的comparator应该是从小到大的comparator: return a-b; 因为pq是top出来， 所以他的顺序应该还是从小到大，
所以在fetch items这道题里pq的解法和array的sort的comparator应该是一样的
   
10. return a - b; 是minHeap， 上升趋势， 再强调一遍
    
11. list.Sort(com)也可以被Collections.Sort(list)取代， 如果我们只是要一个普通递增趋势的list
    
12. 当我们在一遍记录频率一边按照频率在heap里面排列是会出错的， 但如果频率是固定的我们随时加进去都会根据它的频率排列到它应该在的位置上

13. Collections.max() 可以直接求出整个collection里的最大值

14. treeMap永远都由key来sort， 基本不要用value来sort

15. 要正常使用treeSet就不要改comparator 
    
16. 找最接近requirement的marking的时候不能用treeSet.higher()； 而是要用treeSet.ceiling(); higher()是不包括等于requirement的marking的

Stack和Queue：
---------------------------------------------------------------------------------------

 1.Deque<List<Integer>> Stack = new ArrayDeque<>();
   Stack<Integer> Stack = new Stack<>();
   Stack.push(E);
   Stack.pop(E);
   Stack.addLast(E);
   Stack.removeLast(E);

 2.Queue<TreeNode> queue = new LinkedList<>();不能写PriorityQueue<>();因为treeNode是无法比较的
   queue.offer(E);
   queue.poll(E);

        Initialization： Queue<E> queue = new PriorityQueue<>();
            boolean: add(E e)&& offer(E e)，加东西，没有超过大小限制return true，否则return false
            Element：element()&&peek()，得到但不删除head of the queue
            Element：poll（)&&remove()，得到并删除head of the queue

   zigzagTraversal: List<TreeNode> list = new ArrayList<>(queue); 可以把queue的东西都放到list里

 3. minHeap == new PriorityQueue<>((a, b) -> (a.compareTo(b))) , PriorityQueue 从大到小，peek是小；（b.compareTo(a))就是从小到大了
     maxHeap  = new PriorityQueue<>(Collections.reverseOrder())= new PriorityQueue<>((a, b) -> (b.compareTo(a))); 由小到大。（谁compareTo谁谁就更大）


 4.  PriorityQueue<Integer> max = new PriorityQueue<>((a, b) -> (b.compareTo(a)));
            PriorityQueue<Integer> min = new PriorityQueue<>((a, b) -> (a.compareTo(b)));

 5. 从pq里面每次poll出来一个元素的runtime是log(n)，全部poll出来就是nlog(n)
 6. queue poll东西出来都是从尾部offer进去，从入口poll出来； 然而stack应该是从入口push进去， 从入口pop出来
 7. PriorityQueue每次offer进去一个东西的runtime都是O(log(k)), 放进去n个就是O(log（n！））, 其实是要小过直接一开始就直接sort一个n大小的list O(nlog(n))的
 8. pq.remove(Integer)可以把某个特定的元素从pq里移出去
 9. 注意在提取多位数的数字的时候可以用 num = num * 10 + (cl[i] - '0');
 10. 将一整个List addAll到一个stack里的话他会头先进去， 所以在必要的时候要学会reverse

Dynamic Programming:
------------------------------------------------------------------------------------------------------------------------

 1.如果碰上有负数的会影响乘积的， 可以用一个maxDp[]和一个minDp来分别记录， 再根据当前是否小于0来多列一项关系式

 2.最好不要用到recursion, 而是用dp[][]叠加最优结果的思路

 3.在二进制中， i/2的所有digit往左移一位就是i， 所以我们求二进制的变化的时候就是i/2的二进制表现形式里的1的个数加上i%2

 4.求连续的最长数列的情况一般都是用dp来记载当前最长的长度然后再根据尾部来更新长度

Sort:
 -----------------------------------------------------------------------------------------------------------------------

 1. Sort有 Selection Sort, Bubble Sort, Heap Sort, Quick Sort, Merge Sort, Insert Sort, Shell Sort, Bucket Sort等算法 要熟悉概念
    
 2. 碰到有平方的排序要看最小的数字是不是负数， 结合two pointer和 merge sort来解题
    
 3. TreeSet<int[]> ts = new TreeSet<>(); 
    
    ts.floor(cur);
    
    ts.ceiling(cur);

Array
 -----------------------------------------------------------------------------------------------------------------------

 1. Arrays.fill(int[] a, int val) 可以把一个list或者一个int[]所有的值都initialize
    
 2. 直接toArray()的话要在括号里确定一下array的dimension ： return res.toArray(new int[res.size()][2]);

BackTrack:
 -----------------------------------------------------------------------------------------------------------------------
  1. Permutations查重:  temp.remove(temp.size()-1); 用来随时去掉最后的那一个元素来随时查重，因为之前包含最后一个数字的数列已经进入另外一个recursion了
     
  2. Backtrack的中心是找到了想要的结果之一就进入新的recursion，然后退回到没有找到想要的结果的情况继续浏览，就是从每一种可能找到结果的可能拓展recursion
     
  3. 一般先用loop去寻找每一种可能，recursion也可以写成多一个forloop

Node\Pointer:
------------------------------------------------------------------------------------------------------------------------

 1. addTwoNumbers: int val2 = l2  != null ? l2.val : 0; 标记一个integer之前做一个判断
 2. addTwoNumbers: ListNode node = new ListNode(val%10); track.next = node; track = node; 先把node放在track之后，然后再让track = node 继续追踪储值
 3. 九章第一课： i节点到j节点的总长度 = j-i+1；如果你把他放在需要找到的哪个指针的后一个，那他的长度就是 j - i , 比较方便。
 4. 九章第一课： 双指针一起移动的方法： 删去最左边的那个， 并且看最左边的那个pointer是不是预值,  然后主指针在forloop里面继续移动


String:
------------------------------------------------------------------------------------------------------------------------
 1.switch(ch){

          case '.' :
          ...
          break;

          case '@' :
          ...
          break;

          case '+':
          ...
          break;

          default:
          }
 2. 九章第一课： minimumWindowSubstring： int[] cntT可以当作map来用:
                                        for(char c: t){
                                         ++cntT[c];
                                         if(cntT(c) == 1){
                                         \\K表示target里面有多少种字母
                                         K++;
                                         }
 3. 九章第一课：minimunWindowSubstring： Array.toCharArray()以后最长255位，建 int[] cntT = new int[256];
 4. substring(a,b) 要头不要尾
 5. StringBuilder :   1. constructor: new StringBuilder();
                      2. 变成String： sb.toString();
                      3. 去尾巴： 1. sb.setLength(sb.length()-1); 2. sb.deleteCharAt(sb.length()-1);
                      4. 插入：insert(int offset, char c)
    网址 https://docs.oracle.com/javase/7/docs/api/java/lang/StringBuilder.html
 6. 22题： backtrack的helper如果用StringBuilder cur就没法backtrack出所有的情况，但如果用String cur就可以
 7. O(logn)一般都是二分法，dp
 8. toString()只能给int实用， 但String.valueOf()可以给几乎所有的数据种类
 9. StringBuilder sb = new StringBuilder(s); 可以用一个sb来承载s， 
    这样的话s会比较方便来setCharAt(i, "!")做替换， 也方便后面统一删除
    
 10. s.replaceAll("!", ""); 可以统一删除一个东西 , s.setCharAt(可以单独改变某个位置的内容)

 11. a. String s.split(".")分割开几个小string或者删除掉"."前面的部分也可以用 s.substring(s.indexOf(".")+1)来替代，而且
速度更快
     b. s.split(" ", 2); 可以限制split出来的String[]的大小
     
 12. String.strip()可以去掉所有的空格， stripLeading()可以去掉前面的， stripTrailing()可以去掉后面的
     
 13. s.startsWith(String head)可以检查这个s的头是不是什么特定的head





Char:
----------------------------------------------------------------------------------------------------

1. 判断Character里面内容是什么：

isDigit
isLetter
isLetterOrDigit
isLowerCase
isUpperCase
isSpaceChar
isDefined

2. 改变Character：

.toLowerCase

.toUpperCase 
   
3. (char)97 = 'a', Integer到Character的转换, ascii代码

4. 保存了ascii代码的表， 65是'A'， 97才是'a'

Math:
----------------------------------------------------------------------------------------------------

1.不同的数字类型：int（10位）Max 2^32;  long（10位）2^64;  short（5位）2^16; float（小数点后6位） 2^32;  double（小数点后16位）2^64

2.（int）tmp 就是只留整数， tmp本来是double

3. 碰到数字一直要找各个digit的题，while(n > 0) n /= 10;
   
4. a/2 可以写成 a>>1 ; a*2 可以写成 a<<1
   
5. peak = a ++ ; 是先使用a再a++ ， 然而peak = ++a; 是a先++再使用a
   
6. Integer i 可以是null， 但int i 只能是数字， 所以在tree里面用的时候我们可以用Integer来modify一个node的val
   
7. Long.intValue() 可以把一个int转换成long， （char)(i + 48);
   
8. 判断一个数n 是不是prime： 可以用一个forloop
       
       for(int i = 2; i*i <= n; i++){
          if(n % i == 0) return false; //假设有整除return false
          }
          return true;
   
9. Collections.min(List<Integer>)可以直接求一段数字的最小值
   
10. 判断一个String里面是不是Integer可以用 Character.isDigit(String的最后一位char);
    
11. Integer.parseInt(String) 可以一次性读取String里面的整个数字
    
12. java里面peek()出来比较大的数会被看作object， 是不能直接用 == 比较的， 要么用equals要么用两个参数来point这两个object的intVal,
    如果是比较小的数在[-128, 127]之内的话是可以直接==比较， 就是在直接比较数值和地址.
    
13. Math有很多题都是跟余数有关的
    
14. Math的四个方向可以用四个二位坐标来代替
    
15. 跟11差不多， Integer.getInteger()也可以取String里面整个Integer
    
16. 但如果要找一个string里面的double的话就必须要用：
    
    float f = Float.valueof(s);
    
    double floor = Math.floor(f);
    
    double ceil = Math.ceil(f);

Boolean:
----------------------------------------------------------------------------------------------------
1. true && false == false;  true || false == true;
   
2. boolean[][] visited = new boolean[length][width]class
   
3.  l2.val+carry>=10? 1:0; 这种句式而避过if


TimeLimit, Overflow：
----------------------------------------------------------------------------------------------------

1.  l + （r-l)/2 比 （l+r)/2好， 因为（l+r)/2加起来的数字太大就会stack overflow (badVersion)
    
2.  mid如果还是false， l  = mid +1 , 如果是 l = mid 的话还是会进入死循环
    
3. recursion最后不删掉尾巴 sb.deleteCharAt(sb.length()-1)的话会进入死循环


Trie：
----------------------------------------------------------------------------------------------------

1. 字母的信息都可以用 int n = c  - 'a' ; 来找到sons = new TrieNode[26] ；字符位置

2.创建： TrieNode root = new TrieNode();

3.结构: 
        
        class TrieNode{
            boolean isWord;
              TrieNode[] sons;

              public TrieNode(){
                  isWord = false;
                  sons = new TrieNode[26];
              }
        }


BinarySearch：
----------------------------------------------------------------------------------------------------
1.中心用法就是判断mid比结果大还是比结果小，然后一直向这个结果挤压

2.除非特殊情况l或者r可以包括target,不然mid找到target以后就直接returntarget，否则l = mid+1 || r = mid-1, 不然会有runtime error

3.validBST意思是一个root以下所有的node的val都比root小

4.BinarySearch有一个比较常用的whileloop格式：

      while(lo <= hi){
          int mid = (lo + hi)/2;
          if( mid > target) hi = mid - 1;
          else if(mid < target) lo = mid+1;
          else return true;
      }

5.Sorted才能用二分法

HashTable：
----------------------------------------------------------------------------------------------------

1. 浏览一个map所有的entry可以用 for（Map.Entry<K,V> e: map.entrySet()) 的 forloop来浏览
   
2. map.values(); 得到的是一个包含所有value的ArrayList<>();
   
3. 如果想去浏览不同组合的String并在map里查重可以先把他们变成char[]再浏览
   
4. HashSet.iterator().next; 可以从hashset的第一个开始一个个提取set里面的东西
   
5. HashSet.remove(i)可以把hashset里面的i这个元素去掉， 而不是第i个数

6. BiPredicate<T,U> 是一个interface:

   a. and(BiPredicate<? super T,? super U> other)

   b. or(BiPredicate<? super T,? super U> other)

   c. negate()

   d. test(T t, U u)

 来源 ： https://docs.oracle.com/javase/8/docs/api/java/util/function/BiPredicate.html




Matrix
----------------------------------------------------------------------------------------------------
1. l = w > 0? Array.matrix[0].length : 0;
   
2. 计算能找到的最大边长长度的时候最好沿着对角线找
   
3. 知道整个matrix m*n的总数的值，则去找特定的坐标是 Array.matrix[cur/n][cur%n]

LinkedList
---------------------------------------------------------------------------------------------------- 
https://docs.oracle.com/javase/7/docs/api/java/util/LinkedList.html

1. return res.toArray(new int[res.size][]);可以直接把linkedList转换成int[][];



----------------------------------------------------------------------------------------------------

