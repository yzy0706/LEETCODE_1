package linkedList;

import java.util.LinkedList;

public class countAndSay {
    public String countAndSay(int n) {
        //没看懂题目想说啥，总结就是数prevSeq每一个digit的个数，注意StringBuffer的用法，然后承接的nextSeq记得加一个-1
        LinkedList<Integer> firstSeq = new LinkedList<Integer>();
        firstSeq.add(1);
        firstSeq.add(-1);
        LinkedList<Integer> finalSeq = nextSeq(n,firstSeq);
        StringBuffer res= new StringBuffer();
        for(Integer digit : finalSeq){
            res.append(digit.toString());
        }
        return res.toString();


    }


    public LinkedList<Integer> nextSeq(int n,LinkedList<Integer> prevSeq){
        if(n <= 1){
            prevSeq.pollLast();
            return prevSeq;
        }

        LinkedList<Integer> nextSeq = new LinkedList<Integer>();
        Integer prevDigit = null, digitNum = 0;

        for(Integer digit: prevSeq){
            if(prevDigit == null){
                prevDigit = digit;
                digitNum += 1;
            }
            else if(digit == prevDigit){
                digitNum += 1;
            }
            else{
                nextSeq.add(digitNum);
                nextSeq.add(prevDigit);
                prevDigit = digit;
                digitNum = 1;
            }
        }

        nextSeq.add(-1);
        return nextSeq(n-1, nextSeq);


    }
}
