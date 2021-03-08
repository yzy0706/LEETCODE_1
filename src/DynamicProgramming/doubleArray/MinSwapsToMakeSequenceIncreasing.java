package DynamicProgramming.doubleArray;

public class MinSwapsToMakeSequenceIncreasing {
    //做法 : 主要做法就是以一个double array来记录如果在当前i位置调换或者不调换的总调换次数
    // 1. 先建立两个int[] swap, not_swap来代表当前到了i总共调换的次数, 也可以单纯用两个数字记录之前的调换的总次数, 一开始i = 0的时候
    // not_swap[0] = 0, swap[0] = 1; swap就是假设每一步能换的都换了
    // 2. forloop i从1到len-1, 每一步都先假设当前 swap[i] = len, not_swap[i] = len;
    //      a. 如果当前A、B数列i位置都比之前大, 那么两位都是可以不换的 swap = last_swap + 1; not_swap = last_notSwap;
    //      b. 紧接着, 假如A、B数列自己的i位置都大于对方i-1的位置, 那么证明是可以换的, 那么我们就试着换一下取最小值:
    //              swap[i] = Math.min(swap[i], not_swap[i-1] + 1);
    //              not_swap[i] = Math.min(not_swap[i], swap[i-1]); //当前假设是一定换一下, 看当前的结果会不会更小
    // 3. 最后return Math.min(last_swap, last_notSwap); (swap[len-1], not_swap[len-1]同理)

    // Runtime: O(N), Space: O(N) (int[]), O(1) int

    public int minSwap_intDP(int[] A, int[] B) {
        int len = A.length;
        if(len < 1) return 0;
        int last_swap = 1, last_notSwap = 0;

        for(int i = 1; i < len; i++){
            int swap = len, not_swap = len;
            if(A[i] > A[i-1] && B[i] > B[i-1]){ //可以不调换
                swap = last_swap + 1;
                not_swap = last_notSwap;
            }
            if(A[i] > B[i-1] && B[i] > A[i-1]){ //可以调换
                swap = Math.min(swap, last_notSwap + 1); //假如在当前位置换
                not_swap = Math.min(not_swap, last_swap); //假如在上一个位置换
            }
            last_swap = swap;
            last_notSwap = not_swap; //更新记录上一个的数值
        }
        return Math.min(last_swap, last_notSwap);
    }


    public int minSwap_dp(int[] A, int[] B) {
        int[] swap = new int[A.length];
        int[] not_swap = new int[A.length];

        swap[0] = 1;
        for(int i = 1; i < A.length; i++){
            swap[i] = not_swap[i] = A.length;
            if(A[i] > A[i-1] && B[i] > B[i-1]){ //不能调换
                swap[i] = swap[i-1] + 1;
                not_swap[i] = not_swap[i-1];
            }
            if(A[i] > B[i-1] && B[i] > A[i-1]){ //可以调换
                swap[i] = Math.min(swap[i], not_swap[i-1] + 1); //假如在当前位置换
                not_swap[i] = Math.min(not_swap[i], swap[i-1]); //假如在上一个位置换
            }
        }
        return Math.min(swap[A.length-1], not_swap[A.length-1]);
    }
}
