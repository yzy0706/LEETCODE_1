package Greedy;
import java.util.*;

public class TextJustification {
    // 做法：Greedy做法
    // 1. 尽可能的多囊括一些词语(中间至少要有一个空格)
    // 2.   a. 如果i < len且当前行多于一个词语， 根据maxWidth找出每个词语中间的空格数
    //      b. else just put words with 1 space between them on the array
    // Runtime: O(n^2), Space: O(n)

    public List<String> fullJustify(String[] words, int maxWidth) {
        int len = words.length;
        List<String> res = new ArrayList<>();
        if(len < 1){
            return res;
        }
        // StringBuilder sb = new StringBuilder();
        for(int i = 0; i < len; i ++){
            int tempLen = 0;
            List<String> temp = new ArrayList<>();
            while(i < len && tempLen < maxWidth){
                tempLen += words[i].length();
                if(tempLen + temp.size() > maxWidth){
                    tempLen -= words[i].length();
                    break;
                }
                temp.add(words[i++]);
            }
            StringBuilder sb = new StringBuilder();
            if(i < len && temp.size() > 1){
                int numSpace = maxWidth - tempLen, wordNum = temp.size();
                int avgNumSpace = (maxWidth - tempLen) / (wordNum - 1);
                int remainder = (maxWidth - tempLen) % (wordNum - 1);
                // System.out.println(avgNumSpace);
                String spaces = "";
                while(avgNumSpace > 0){
                    spaces += " ";
                    avgNumSpace --;
                }
                for(int j = 0; j < temp.size() - 1; j++){
                    String word = temp.get(j);
                    sb.append(word + spaces);
                    if(remainder -- > 0){
                        sb.append(' ');
                    }
                }
                sb.append(temp.get(temp.size() - 1));
            }
            else{
                for(String word : temp){
                    if(sb.length() + word.length() == maxWidth){
                        sb.append(word);
                    }
                    else{
                        sb.append(word + ' ');
                    }
                }
                while(sb.length() < maxWidth){
                    sb.append(' ');
                }
            }
            res.add(sb.toString());
            i--;
        }
        return res;
    }
}
