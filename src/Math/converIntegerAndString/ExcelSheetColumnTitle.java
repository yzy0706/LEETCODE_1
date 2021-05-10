package Math.converIntegerAndString;

public class ExcelSheetColumnTitle {
    // 做法: 相当于是Integer to Roman的26进制做法
    // 1. int remainder = columnNumber % 26; 只是要注意如果当前的remainder是0, 要转换当前的remainder从0到26
    // 2. res.append((char)(65 + actualRemainder - 1));
    // 3. columnNumber = (columnNumber - actual) / 26;

    // Runtime: O(n), Space: O(1), n是columnNumber的根号下26

    public String convertToTitle(int columnNumber) {
        StringBuilder res = new StringBuilder();
        while(columnNumber > 0){
            int remainder = columnNumber % 26;
            int actual = remainder == 0 ? 26 : remainder;
            char cur = (char)(65 + actual - 1);
            res.append(cur);
            columnNumber = (columnNumber - actual) / 26;
        }
        return res.reverse().toString();
    }
}
