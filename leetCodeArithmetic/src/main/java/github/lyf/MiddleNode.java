package github.lyf;

import github.entity.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lyf
 */
public class MiddleNode {
    /**
     * 给定一个带有头结点 head 的非空单链表，返回链表的中间结点。
     *
     * 如果有两个中间结点，则返回第二个中间结点。
     * 给定链表的结点数介于 1 和 100 之间。
     *
     * 输入：[1,2,3,4,5]
     * 输出：此列表中的结点 3 (序列化形式：[3,4,5])
     * 返回的结点值为 3 。 (测评系统对该结点序列化表述是 [3,4,5])。
     * @param head
     * @return
     */
    public ListNode middleNode(ListNode head) {
        if(head.next==null) return head;
        List<Integer> list=new ArrayList<>();
        /**
         * 先统计有多少节点，将值插入数组中，计算出中间节点的下标
         * 然后再从中间节点的下标开始循环封装新的ListNode 返回
         */
        list.add(head.val);
        ListNode next1 = head.next;
        while (next1!=null){
            list.add(next1.val);
            next1 = next1.next;
        }
        int size = list.size();
        int mid=0;
        if(size%2==1){
            mid=(0+size-1)/2;
        }else {
            mid=((0+size-1)/2)+1+1;
        }
        ListNode first = null,last = null,newNode;
        for(int i=mid;i<size;i++){
            newNode=new ListNode(list.get(i));
            newNode.next=null;
            if(first==null){
                first=newNode;
                last=newNode;
            } else{
                last.next=newNode;
                last=newNode;
            }

        }
        return first;
    }

    /**
     * 利用快慢指针，快指针每次走两步，慢指针每次走一步，所以快指针走的距离为慢指针的两倍，
     * 故当快指针遍历到链表末尾时，慢指针指向记为中间节
     * @param head
     * @return
     */
    public ListNode middleNode1(ListNode head) {
        if (head.next == null) return head;
        // 用快慢指针
        ListNode slow = head;
        while (head != null && head.next!= null) {
            head = head.next.next;
            slow = slow.next;
        }
        return slow;
    }
    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}
