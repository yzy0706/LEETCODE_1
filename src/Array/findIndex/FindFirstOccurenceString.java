package Array.findIndex;

public class FindFirstOccurenceString {
    // 做法： 直接return indexOf或者 substring(i, i + need.length());
    // Runtime: O(n), Space: O(1)
   public int solution1 (String haystack, String needle){
       if(needle == "") return 0;
       return haystack.indexOf(needle);
   }

   public int solution2 (String haystack, String needle){
       int res = -1;
       int length = needle.length();
       if(needle.equals("")) return 0;
       for(int i = 0 ; i < haystack.length() - length + 1 ; i++){
           if(haystack.substring(i,i+length).equals(needle)){
               res = i;
               break;
           }
       }
       return res;
   }
}
