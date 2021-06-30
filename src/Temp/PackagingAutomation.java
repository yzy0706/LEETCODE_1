package Temp;

public class PackagingAutomation {
    public int packaging(int numGroups, int[] arr) {
        int freq[] = new int[arr.length + 2];
        int max = 0;
        for (int i = 0; i < arr.length; ++i) {
            if (arr[i] > arr.length) {
                arr[i] = arr.length + 1;
            }
            freq[arr[i]]++;
            max = Math.max(max, arr[i]);
        }
        int i = 1;
        int j = 0;
        while (i <= max) {
            if (freq[i] > 0 && j < i) {
                j++;
            }
            freq[i]--;
            if (freq[i] < 1) {
                i++;
            }
        }
        return j;
    }
}
