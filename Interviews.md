1. 华为 
   
   一面： 给一个 n * 3的matrix， 任意抽选3个row， 问能组成延对角线堆成的3*3 matrix有几个

   例子：

            a l b     ->      f d a 
         
            s k l             d e l
   
            f d a             a l b
   
            d e l

   
   二面 ： 





2. 字节跳动 
    
    一面：
   
      1. 给一个二叉树， 求二叉树里面最长的路径
   
      2. 给一个字符串，当前的进制， 需要转换到的进制， 返回转换进制以后的字符串: 
      
         例子：  "077"， 8， 10 -> "63"
   

          public String convertNum(String s, int a, int b){
         
            }






   二面：
    
   1.  给一个 List<Integer> nodes, 一个父节点root， 返回当前的nodes是不是二叉树里面的有效路径

      


3.  巨硬

   1. Tech一面： lc 977 , 给了三个解法
      
      a. follow up ： 有没有O(1) Space的解法： 我说之前那个square再sort就行

      b. 为什么不能直接edit array ： 因为可能会把原来的数字替代掉， 这样就找不到之前的， 要另外储存就还是O(N) space
