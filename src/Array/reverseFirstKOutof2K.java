package Array;

public class reverseFirstKOutof2K {
    public String reverseStr(String s, int k) {
        int len = s.length(), pos = 0;
        if(len < 1) return "";
        char[] cl = s.toCharArray();
        Queue<char> queue = new LinkedList<>(k);
        boolean betweenK2K = false;
        StringBuilder sb = new StringBuilder();

        while(pos < len ) {
            if(!betweenK2K){
                queue.offer(cl[pos]);
                if(pos % k == 0 && pos % 2k != 0){
                    while(!queue.isEmpty()){
                        sb.append(queue.pull());
                    }
                    betweenK2K = true;
                }

            }

            else if(betweenK2K){
                sb.append(char[pos]);
                if(pos % 2k ==0){
                    betweenK2K = false;
                }
            }

            pos++;
        }


        return sb.toString();
    }
}
