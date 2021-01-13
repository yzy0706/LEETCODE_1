package oa2;

import java.util.*;

public class CountTeams {
    //刀的解法直接算N！来做排列组合
    public static int countTeams(int num, int[] skills, int minAssociates, int minLevel, int maxLevel){
        int count = 0;
        for (int i = 0; i < num; ++i) {
            if (skills[i] >= minLevel && skills[i] <= maxLevel) {
                count++;
            }
        }
        long res = 0;
        for (int i = minAssociates; i <= count; ++i) {
            res += combination(count, i);
        }
        return (int) res;
    }

    private static long combination(int n, int k) {
        return factorial(n) / (factorial(k) * factorial(n - k));
    }

    private static long factorial(int n) {
        long res = 1;
        for (int i = 1; i <= n; ++i) {
            res *= i;
        }
        return res;
    }

    public static void main(String[] args){
        int num = 6;
        int[] skills = new int[]{12, 4, 6, 13, 5, 10};
        int minAssociates = 3;
        int minLevel = 4;
        int maxLevel = 10;
        System.out.println(countTeams(num, skills, minAssociates, minLevel, maxLevel));
    }







    //地里给的解法， 做法只要是第一步计算符合要求的length的数量， 然后跟minReviews做排列组合
    public static int countTeams_2(int num, List<Integer> lengths, int minReviews, int  maxLength, int minLength){
        int cnt = 0;
        for(int i : lengths){
            if(i >= minLength && i <= maxLength){
                cnt++;
            }
        }
        int res = 0;
        for(int i = minReviews; i <= cnt; i++){
            res += combine(cnt, i);
        }
        return res;
    }


    public static int combine(int total, int minReview){
        if(total < minReview) return 0;
        if(total == minReview) return 1;
        if(minReview == 0) return 1;
        return combine(total-1, minReview-1) + combine(total-1, minReview);
    }





    //permutation的解法, stack overflow了
    public static Set<List<Integer>> combinations;
    public static int countTeams(int num, List<Integer> lengths, int minReviews, int  maxLength, int minLength){
        List<Integer> qualified = new ArrayList<>();
        for(int i = 0; i < num; i++){
            int cur = lengths.get(i);
            if(cur >= minLength && cur <= maxLength) qualified.add(cur);
        }

        combinations = new HashSet<>();
        List<Integer> cur = new ArrayList<>();
        combine(qualified, minReviews, 0, cur);
        return combinations.size();
    }


    public static void combine(List<Integer> qualified, int minReviews, int curIndex, List<Integer> cur){
        if(cur.size() <= qualified.size() && cur.size() >= minReviews && !combinations.contains(cur)){
            combinations.add(cur);
            return;
        }
        for(int i = 0; i < qualified.size(); i++){
            if(cur.size() == 0 || i != curIndex) {
                cur.add(qualified.get(i));
                cur.sort((a, b) -> a - b);
                combine(qualified, minReviews, i, cur);
                cur.remove(qualified.get(i));
            }
        }

    }

}
