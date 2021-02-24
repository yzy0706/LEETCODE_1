package Math.addTwoNumbers;

public class AddBinary {
    // 做法: 用的两个pointer一起跑
    // Runtime: O(m), m是比较长的那一个
    public String addBinary(String a, String b) {
        int i = a.length() - 1, j = b.length() - 1;
        int add = 0;
        StringBuilder sb = new StringBuilder();
        while(i >= 0 && j >= 0){
            int num1 = Character.getNumericValue(a.charAt(i));
            int num2 = Character.getNumericValue(b.charAt(j));
            int sum = num1 + num2 + add;
            if(add > 0){
                add --;
            }
            if(sum >= 2){
                sum -= 2;
                add = 1;
            }
            sb.append(sum);
            i--;
            j--;
        }
        while(i >= 0){
            int num1 = Character.getNumericValue(a.charAt(i));
            int sum = num1 + add;
            if(add > 0){
                add --;
            }
            if(sum >= 2){
                sum -= 2;
                add = 1;
            }
            sb.append(sum);
            i--;
        }
        while(j >= 0){
            int num2 = Character.getNumericValue(b.charAt(j));
            int sum = num2 + add;
            if(add > 0){
                add --;
            }
            if(sum >= 2){
                sum -= 2;
                add = 1;
            }
            sb.append(sum);
            j--;
        }

        if(add > 0){
            sb.append(add);
        }

        return sb.reverse().toString();
    }
}
