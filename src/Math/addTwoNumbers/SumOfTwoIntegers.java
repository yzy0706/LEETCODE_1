package Math.addTwoNumbers;

public class SumOfTwoIntegers {
    // 做法: 正确答案是用bit mask, 暂时先不看了
    // Runtime: O(n), Space: O(1);

    public int getSum_2(int a, int b) {
        return a + b;
    }


    public int getSum(int a, int b) {
        int cnt = 0;
        StringBuilder res = new StringBuilder();
        while(a > 0 && b > 0){
            int ra = a % 10, rb = b % 10;
            int sum = ra + rb + cnt;
            cnt = sum >= 10 ? 1 : 0;
            res.append(sum % 10);
            a /= 10;
            b /= 10;
        }
        while(a > 0){
            int ra = a % 10;
            int sum = ra + cnt;
            cnt = sum >= 10 ? 1 : 0;
            res.append(sum % 10);
            a /= 10;
        }
        while(b > 0){
            int rb = b % 10;
            int sum = rb + cnt;
            cnt = sum >= 10 ? 1 : 0;
            res.append(sum % 10);
            b /= 10;
        }
        return Integer.valueOf(res.reverse().toString());
    }
}
