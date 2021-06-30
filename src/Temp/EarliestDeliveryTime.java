package Temp;

import java.util.Collections;
import java.util.List;

public class EarliestDeliveryTime {
    // n = 2, openTimes = [8, 10], deliveryTimeCost = [2, 2, 3, 1, 8, 7, 4, 5];
    // output 16:
    // Greedy, 开门最早的对应delivery时间最长的
    public static int earliestTime(int n, List<Integer> openTimes, List<Integer> deliveryTimeCost) {
        if(n < 1) return 0;
        Collections.sort(deliveryTimeCost, Collections.reverseOrder());
        Collections.sort(openTimes);
        int j = 0;
        int res = 0;
        for (int openTime : openTimes) {
            res = Math.max(res, openTime + deliveryTimeCost.get(j));
            j += 4;
        }
        return res;
    }
//    public static List<String> splitWords(String s) {
//        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
//    }
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int n = Integer.parseInt(scanner.nextLine());
//        List<Integer> openTimes = splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList());
//        List<Integer> deliveryTimeCost = splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList());
//        scanner.close();
//        int res = earliestTime(n, openTimes, deliveryTimeCost);
//        System.out.println(res);
//    }
}
