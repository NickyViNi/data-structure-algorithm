package linkedList;


public class LLleetcode {
    //definition for singly-linked list.
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode middleNode(ListNode head) {
        ListNode fast = head;
        ListNode low = head;
        while (fast != null && fast.next != null) {
            low = low.next;
            fast = fast.next.next;
        }
        return low;
    }
}
