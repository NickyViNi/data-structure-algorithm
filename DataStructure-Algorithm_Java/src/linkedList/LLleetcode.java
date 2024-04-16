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

    //876
    public ListNode middleNode(ListNode head) {
        ListNode fast = head;
        ListNode low = head;
        while (fast != null && fast.next != null) {
            low = low.next;
            fast = fast.next.next;
        }
        return low;
    }
    //141
    public boolean hasCycle(ListNode head) {
        ListNode low = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            low = low.next;
            if (fast == low) {
                return true;
            }
        }
        return false;
    }

    public ListNode findKthFromEnd1(ListNode head, int k) {
	    int len = 0;
	    ListNode curr = head;
	    while (curr != null) {
	        curr = curr.next;
	        len++;
	    }
	    if (k > len) return null;

	    int targetIdx = len - k;
	    ListNode temp = head;
	    while (targetIdx > 0) {
	        temp = temp.next;
	        targetIdx--;
	    }
	    return temp;
	}
    public ListNode findKthFromEnd(ListNode head, int k) {
        if (head == null) return null;
        ListNode fast = head;
        ListNode low = head;
        for (int i = 1; i < k; i++) {
            fast = fast.next;
            if (fast == null) return null;
        }
        while (fast.next != null) {
            fast = fast.next;
            low = low.next;
        }

        return low;
    }

    // 19
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head.next == null) return null;

        ListNode fast = head, slow = head, prev = null;
        for (int i = 1; i < n; i++) {
            fast = fast.next;
            if (fast == null) return null;
        }

        while (fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next;
        }

        if (prev != null) {
            prev.next = slow.next;
        } else {
            head = head.next;
        }
        return head;
    }
}
