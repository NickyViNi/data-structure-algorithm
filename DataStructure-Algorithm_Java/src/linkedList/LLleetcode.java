package linkedList;

import java.util.ArrayList;
import java.util.HashSet;

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

    //86 bad solution
    public ListNode partition1(ListNode head, int x) {
        if (head == null || head.next == null) return head;
        ArrayList<ListNode> less = new ArrayList<>();
        ArrayList<ListNode> great = new ArrayList<>();;
        ListNode curr = head;
        while (curr != null) {
            if (curr.val < x) {
                less.add(curr);
            } else {
                great.add(curr);
            }
            curr = curr.next;
        }
        head = null;
        while (great.size() > 0) {
            ListNode temp = great.remove(great.size() - 1);
            temp.next = head;
            head = temp;
        }

        while (less.size() > 0) {
            ListNode temp = less.remove(less.size() - 1);
            temp.next = head;
            head = temp;
        }

        return head;
    }

    //86 good solution
    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) return head;
        ListNode lessTail = new ListNode(0);
        ListNode lessHead = lessTail;
        ListNode moreTail = new ListNode(0);
        ListNode moreHead = moreTail;
        ListNode curr = head;

        while (curr != null) {
            if (x > curr.val) {
                lessTail.next = curr;
                lessTail = curr;
            } else {
                moreTail.next = curr;
                moreTail = curr;
            }
            curr = curr.next;
        }
        moreTail.next = null;
        lessTail.next = moreHead.next;
        head = lessHead.next;

        return head;
    }

    //
    public void removeDuplicates(ListNode head) {
        HashSet<Integer> set = new HashSet<>();
        ListNode curr = head;
        ListNode prev = head;
        while (curr != null) {
            if (set.contains(curr.val)) {
                prev.next = curr.next;
            } else {
                set.add(curr.val);
                prev = curr;
            }
            curr = curr.next;
        }
    }
}
