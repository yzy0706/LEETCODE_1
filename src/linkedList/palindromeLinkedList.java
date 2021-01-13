package linkedList;

public class palindromeLinkedList {
    // 中心就是建立一个公用的pointer，然后用一个recursive helper去检查能不能找到跟pointer的val相等的listnode， 公用的pointer一开始等于head，
    // 所以在找到第一个相等的开始得一直做recursion才能找到所有的相等的
    private ListNode pointer;

    private boolean recurse(ListNode cur){
        if(cur != null){
            if(!recurse(cur.next)) return false;
            if(cur.val != pointer.val) return false;
            pointer = pointer.next;
        }
        return true;
    }


    public boolean isPalindrome(ListNode head){
        pointer = head;
        return recurse(head);
    }
}
