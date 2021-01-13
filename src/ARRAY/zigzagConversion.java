package ARRAY;

import java.util.ArrayList;
import java.util.List;

public class zigzagConversion {
    // 这个方法主要是去建立一个拥有numRows个StringBuilder()的Array，然后分别在各个StringBuilder()上分配character， 如果排列趋势是向上的话就往前浏览StringBuilder,
    // 反之就向后浏览
    public String convert(String s, int numRows) {
        if(numRows == 1) return s;
        List<StringBuilder> rows = new ArrayList<>();
        for(int i = 0; i < Math.min(numRows, s.length()); i++) rows.add(new StringBuilder());

        int curRow = 0;
        Boolean down = false;
        for(char c: s.toCharArray()){
            rows.get(curRow).append(c);
            if(curRow == 0 || curRow == numRows - 1) down = !down;
            curRow += down? 1 : -1;
        }

        StringBuilder res = new StringBuilder();
        for(StringBuilder row : rows) res.append(row);

        return res.toString();
    }
}
