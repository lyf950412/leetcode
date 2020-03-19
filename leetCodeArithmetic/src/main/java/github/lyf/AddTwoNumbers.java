package github.lyf;

/**
 * @author lyf
 */
public class AddTwoNumbers {
    /**
     *  给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
     *
     * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
     *
     * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     *  输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     *  输出：7 -> 0 -> 8
     * 原因：342 + 465 = 807
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l2 == null) {
            return l1;
        }else if(l1 == null && l2 != null){
            return l2;
        }else {
            l1.val = l1.val+l2.val;
            jinwei(l1);
            l1.next = addTwoNumbers(l1.next,l2.next);
            return l1;
        }
    }
    public void jinwei(ListNode l1) {
        if(l1.val>9) {
            if(l1.next == null){
                l1.next = new ListNode(l1.val/10);
            }else{
                l1.next.val += l1.val/10;
                jinwei(l1.next);
            }
            l1.val %= 10;
        }
    }

    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}
