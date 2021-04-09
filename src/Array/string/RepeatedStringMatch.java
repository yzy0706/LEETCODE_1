package Array.string;

public class RepeatedStringMatch {
    //做法: 这题因为用stirng.repeat()的做法有很多bug, 所以我看了答案用了stringbuilder()
    // 1. 用res = 0记录重复a的次数, 用一个StringBuilder sa来记录复制了的a
    // 2. 先把sa一直append a, 一直到sa的长度大于等于b的长度
    // 3.   a. 如果当前b是a的substring, 直接return res;
    //      b. 如果当前b不是a的substring, 那就把sa再加上一个a, 如果加上一个a以后b都不是substring只能说明a怎么复制都没用, 因为当sa的长度刚好大于等于b的时候, 如果b不是substring还可以是因为当前sa尾部缺了什么字母, 但如果再加一个a都没用的话只能说明sa再加上多少个a都没用了, 因为b身的字母排序就有问题

    //Runtime: O(n), Space: O(1), n是b的长度, 可能a只有一个字符, 重复加n次append上a.

    public int repeatedStringMatch_StringBuilder(String a, String b) {
        StringBuilder sa = new StringBuilder();
        int res = 0;
        while(sa.length() < b.length()){
            sa.append(a);
            res ++;
        }
        if(sa.indexOf(b) != -1) return res;
        // System.out.println(sa.toString() + " " + res);
        return sa.append(a).indexOf(b) == -1 ? -1 : res+1;
    }




    public int repeatedStringMatch(String a, String b) {
        if(b.length() == 0) return 0;
        if(a.length() == 0 || a.length() > 1e4 || b.length() > 1e4) return -1;
        int res = 1;
        String compare = a;
        for(int i = 0; i < b.length(); i++){
            String cur = b.substring(0, i+1);
            System.out.println(cur + " " + a);
            // if(i == b.length() - 1 && a.indexOf(cur) == -1) return -1;
            while (compare.indexOf(cur) == -1){
                res ++;
//                compare = a.repeat(res);
                if(compare.length() > 1e4){
                    return -1;
                }
            }
        }
        return res;

    }
}
