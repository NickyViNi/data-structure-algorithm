package linkedList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Stack;

public class LLleetcode {
    //definition for singly-linked list.
    public class ListNode {
        int val;
        ListNode next;
        ListNode random;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    //876 easy
    public ListNode middleNode(ListNode head) {
        ListNode fast = head;
        ListNode low = head;
        while (fast != null && fast.next != null) {
            low = low.next;
            fast = fast.next.next;
        }
        return low;
    }
    //141 easy
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

    // 19 medium
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

    //86 medium good solution
    //Given the head of a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
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

    // remove duplicate value of Node
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

    // 83: easy -> Remove Duplicates from Sorted List
    public ListNode deleteDuplicates(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            if (prev == null) {
                prev = curr;
            } else if (prev.val < curr.val) {
                prev = curr;
            } else {
                prev.next = curr.next;
            }
            curr = curr.next;
        }
        return head;
    }

    //82: medium -> Remove Duplicates (inculded itself) from Sorted List II
    public ListNode deleteDuplicatesIncludeItself(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode newHead = null;
        ListNode newTail = null;
        ListNode curr = head;
        int num = Integer.MIN_VALUE;
        while (curr != null) {
            if (curr.next != null && curr.val == curr.next.val) {
                curr = curr.next;
                num = curr.val;
                continue;
            }

            if (num != curr.val) {
                if (newHead == null) {
                    newHead = curr;
                    newTail = curr;
                } else {
                    newTail.next = curr;
                    newTail = curr;
                }
            }

            curr = curr.next;
        }
        if (newTail != null) newTail.next = null;

        return newHead;
    }

    //1290 easy Convert Binary Number in a Linked List to Integer
    public int getDecimalValue(ListNode head) {
        int decimal = 0;
        ListNode curr = head;
        while (curr != null) {
            decimal = 2 * decimal + curr.val;
            curr = curr.next;
        }
        return decimal;
    }

    // 92 medium: reverse LL between left to right range:
    private static ListNode get(ListNode head, int idx) {
        if (idx == 0) return null;
        ListNode temp = head;
        int i = 1;
        while (temp != null && i < idx) {
            temp = temp.next;
            i++;
        }
        return temp;
    }

    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || head.next == null || left >= right) return head;

        ListNode beforeNode = get(head, left - 1);
        ListNode startNode = (beforeNode == null) ? head : beforeNode.next;
        ListNode endNode = get(head, right);
        ListNode afterNode = (endNode == null) ? null : endNode.next;

        ListNode prevNode = null;
        ListNode currNode = startNode;

        while (currNode != afterNode) {
            ListNode nextNode = currNode.next;
            currNode.next = prevNode;
            prevNode = currNode;
            currNode = nextNode;
        }

        if (beforeNode == null) {
            head = prevNode;
        } else {
            beforeNode.next = prevNode;
        }

        startNode.next = afterNode;

        return head;
    }

    //24: medium:
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        while (head != null && head.next != null) {
            ListNode first = head;
            ListNode second = head.next;

            //swap nodes:
            prev.next = second;
            first.next = second.next;
            second.next = first;

            //update head and prev:
            head = first.next;
            prev = first;
        }

        head = dummy.next;
        return head;
    }

    //160 easy -> get interestion node of two linkedList
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode pointA = headA;
        ListNode pointB = headB;

        while (pointA != pointB) {
            pointA = (pointA == null) ? headB : pointA.next;
            pointB =  (pointB == null) ? headA : pointB.next;
        }
        return pointA;
    }

    //23 hard -> Merge K sorted list
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        for (ListNode list : lists) {
            while (list != null) {
                minHeap.add(list.val);
                list = list.next;
            }
        }

        while (minHeap.size() != 0) {
            curr.next = new ListNode(minHeap.poll());
            curr = curr.next;
        }
        return dummy.next;
    }
    //2 medium -> add two number
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            int num1 = l1 != null ? l1.val : 0;
            int num2 = l2 != null ? l2.val : 0;
            int sum = num1 + num2 + carry;
            int remainder = sum % 10;
            carry = sum / 10;
            ListNode newNode = new ListNode(remainder);
            curr.next = newNode;
            curr = newNode;
            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;
        }
        return dummy.next;
    }

    //138 medium -> copy list with random pointer
    public ListNode copyRandomList(ListNode head) {
        HashMap<ListNode, ListNode> nodeMap = new HashMap<>();
        ListNode curr = head;
        while (curr != null) {
            nodeMap.put(curr, new ListNode(curr.val));
            curr = curr.next;
        }
        curr = head;
        while (curr != null) {
            nodeMap.get(curr).next = nodeMap.get(curr.next);
            nodeMap.get(curr).random = nodeMap.get(curr.random);
            curr = curr.next;
        }
        return nodeMap.get(head);
    }
    //50 medium -> calculate pow(x, n)
    public double myPow(double x, int n) {
        double result = 1;
        long n2 = n;
        if (n2 < 0) n2 *= -1;
        while (n2 > 0) {
            if (n2 % 2 == 0) {
                x *= x;
                n2 = n2 / 2;
            } else {
                result *= x;
                n2--;
            }
        }
        if (n < 0) return (double) 1 / result;
        return result;
    }
    //36 medium -> valid sudoku
    public boolean isValidSudoku(char[][] board) {
        HashSet<String> hs = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char boa = board[i][j];
                if (boa != '.') {
                    if (!hs.add(boa + "in row" + i) ||
                        !hs.add(boa + "in col" + j) ||
                        !hs.add(boa + "in block" + i/3 + "-" + j/3)
                    ) return false;
                }
            }
        }
        return true;
    }
    //61 medium -> rotate list
    public ListNode rotateRight(ListNode head, int k) {
        if (k == 0 || head == null || head.next == null) return head;
        int size = 1;
        ListNode tail = head;
        while (tail.next != null) {
            size++;
            tail = tail.next;
        }
        k = k % size;
        if (k == 0) return head;
        tail.next = head;
        for (int i = 1; i <= size - k; i++) {
            tail = tail.next;
        }
        head = tail.next;
        tail.next = null;
        return head;
    }

    public ListNode rotateRight2(ListNode head, int k) {
        if (head == null || head.next == null) return head;
        int len = getLength(head);
        k = k % len;
        if (k == 0) return head;
        int i = 1;
        ListNode curr = head, prev = null, target = null, tail = null;
        while (curr != null) {
            if (i == len - k) prev = curr;
            if (i == len - k + 1) target = curr;
            if (i == len) tail = curr;
            curr = curr.next;
            i++;
        }
        prev.next = null; tail.next = head; head = target;
        return head;
    }
    private int getLength(ListNode head) {
        ListNode curr = head;
        int len = 0;
        while (curr != null) {
            curr = curr.next;
            len++;
        }
        return len;
    }

    //148 medium -> merge sort in LinkedList
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode midNode = getMiddleNode(head);
        ListNode nextNode = midNode.next;
        midNode.next = null;
        return mergeList(sortList(head), sortList(nextNode));
    }
    private ListNode getMiddleNode(ListNode node) {
        ListNode slow = node, fast = node;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    private ListNode mergeList(ListNode a, ListNode b) {
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;
        while (a != null && b != null) {
            if (a.val <= b.val) {
                curr.next = a;
                a = a.next;
            } else {
                curr.next = b;
                b = b.next;
            }
            curr = curr.next;
        }
        curr.next = a == null ? b : a;
        return dummy.next;
    }

    //206 easy -> Reverse Linked List
    //iterative approach
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextNode = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextNode;
        }
        return prev;
    }
    //recursive approach
    public ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode res = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return res;
    }

    //2095 medium ->  Delete the Middle Node of a Linked List
    public ListNode deleteMiddle(ListNode head) {
        if (head == null || head.next == null) return null;
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        slow.next = slow.next.next;
        return head;
    }

    //328 medium -> Odd Even Linked List
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenHead = even;
        while (even != null && even.next != null) {
            odd.next = even.next;
            even.next = even.next.next;
            odd = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }
    public ListNode oddEvenList2(ListNode head) { // don't use this approach
        if (head == null || head.next == null) return head;
        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenHead = even;
        int idx = 0;
        while (even.next != null) {
            odd.next = even.next;
            odd = even;
            even = even.next;
            idx++;
        }
        if (idx % 2 == 0) {
            odd.next = evenHead;
        } else {
            odd.next = null;
            even.next = evenHead;
        }
        return head;
    }

    //2130 medium -> Maximum Twin Sum of a Linked List
    public int pairSum(ListNode head) { // O(1) space
        //find the middle node and reverse from mid to end
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        slow = reverseLinkedList(slow);

        //pair sum
        int max = 0;
        while (slow != null) {
            max = Math.max(max, head.val + slow.val);
            slow = slow.next;
            head = head.next;
        }
        return max;
    }
    private ListNode reverseLinkedList(ListNode node) {
        ListNode prev = null;
        ListNode curr = node;
        while (curr != null) {
            ListNode nextNode = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextNode;
        }
        return prev;
    }

    public int pairSum2(ListNode head) { // O(n) space
        Stack<ListNode> stack = new Stack<>();
        ListNode temp = head;
        while (temp != null) {
            stack.push(temp);
            temp = temp.next;
        }
        int size = stack.size();
        int max = 0;
        while (stack.size() > size / 2) {
            max = Math.max(max, head.val + stack.pop().val);
            head = head.next;
        }
        return max;
    }

    // 142 medium -> linked list cycle II
    //Floyd's Cycle Finding Algorithm
    /* why they will meet at the cycle begins?
     * Explain: Fast's velocity is twice as fast as Slow, so distance of Fast = 2 * distance of Slow.
       let the distance before the cycle = m, the distance of the cycle = n, in the first while loop, assume Fast and Slow
       will meet at point z, the distance before the z in the cycle = y, after the z = x, so x + y = n.
       because S(fast) = m + kn + y, k is how many n, S(slow) = m + y,
       m + kn + y = 2 * (m + y) ==> m = (k - 1)n + x, so when we move pointer fast from the head step by step and move
       the slow form z, they will be reached at the begin of the cycle
     */
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) break;
        }
        if (fast == null || fast.next == null) return null;
        fast = head;
        while (fast != slow) { // why they will meet at the cycle begins?
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
}
