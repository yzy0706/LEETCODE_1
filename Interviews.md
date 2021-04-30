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

      


3.  微软

   1. Tech一面： lc 977 , 给了三个解法
      
      a. follow up ： 有没有O(1) Space的解法： 我说之前那个square再sort就行

      b. 为什么不能直接edit array ： 因为可能会把原来的数字替代掉， 这样就找不到之前的， 要另外储存就还是O(N) space


   2. VO

      2021 Apr 27, Microsoft Onsite

1. First VO:
Similar to meeting room problems on leetcode, but it is not asking candidate to merge intervals that is overlapping, instead, this question just asks candidates to check if any of the meetings is overlapping.
Follow Up: 1. What edge cases are on your mind? 2. Any other solutions 3. Time and space complexity



2. Second VO:
   Given a string object “</name=”name0” attr=”attr0”:val=”val0” …/>” design a class XML to store the name, attributes, values of the string, create a method to read the string and return the XML.
   Follow up: 1. I used String.split(String s) method to separate different parts of the string, but the interviewer says there could be different characters  between names and attributes

3. Third VO:
   First question was longest palindrome substring(Leetcode 5).
   Second question was to find a way to find if a number is perfectly square number without using built in method.
   Follow up for question 2: 1. any other method?  2. If you can use Math.sqrt() method , how would you check a number is a perfectly squarable number?


4. Fourth VO:
   First question: merge two sorted lists
   Second question: merge k sorted lists
   Follow up for question 2: 1. Any other way except using heap? 2. Any other way to save space ? 3. If input is a list of ListNodes, what would you do? 4. Is there a solution to handle these ListNodes in O(1) space?
