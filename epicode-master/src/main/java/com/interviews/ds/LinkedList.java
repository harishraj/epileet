package com.interviews.ds;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

class ListNode {
    public ListNode next;
    public ListNode prev;
    public ListNode last;
    public int data;

    public ListNode(int d, ListNode n, ListNode p) {
        data = d;
        setNext(n);
        setPrevious(p);
    }

    public ListNode(int d) {
        data = d;
    }

    public ListNode() {
    }

    public void setNext(ListNode n) {
        next = n;
        if (this == last) {
            last = n;
        }
        if (n != null && n.prev != this) {
            n.setPrevious(this);
        }
    }

    public void setPrevious(ListNode p) {
        prev = p;
        if (p != null && p.next != this) {
            p.setNext(this);
        }
    }

    public String printForward() {
        if (next != null) {
            return data + "->" + next.printForward();
        } else {
            return ((Integer) data).toString();
        }
    }

    public ListNode clone() {
        ListNode next2 = null;
        if (next != null) {
            next2 = next.clone();
        }
        ListNode head2 = new ListNode(data, next2, null);
        return head2;
    }

}

public class LinkedList {

    // https://leetcode.com/problems/add-two-numbers/

    public static void deleteDups(ListNode head) {
        ListNode current = head;
        while (current != null) {
            /* Remove all future nodes that have the same value */
            ListNode runner = current;
            while (runner.next != null) {
                if (runner.next.data == current.data) {
                    runner.next = runner.next.next;
                } else {
                    runner = runner.next;
                }
            }
            current = current.next;
        }
    }

    /***
     *
     *
     */

    public static boolean deleteNode(ListNode n) {

        if (n == null || n.next == null) {
            return false; // Failure
        }
        ListNode next = n.next;
        n.data = next.data;
        n.next = next.next;
        return true;
    }

    /***
     *
     *
     */

    public static ListNode partition(ListNode node, int x) {

        ListNode head = node;
        ListNode tail = node;

        /* Partition list */
        while (node != null) {
            ListNode next = node.next;
            if (node.data < x) {
                /* Insert node at head. */
                node.next = head;
                head = node;
            } else {
                /* Insert node at tail. */
                tail.next = node;
                tail = node;
            }
            node = next;
        }
        tail.next = null;

        return head;
    }

    // https://leetcode.com/problems/sort-list/

    /***
     *
     * https://leetcode.com/problems/palindrome-linked-list/
     */

    public static boolean isPalindrome(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        Stack<Integer> stack = new Stack<Integer>();

        while (fast != null && fast.next != null) {
            stack.push(slow.data);
            slow = slow.next;
            fast = fast.next.next;
        }

        /* Has odd number of elements, so skip the middle */
        if (fast != null) {
            slow = slow.next;
        }

        while (slow != null) {
            int top = stack.pop().intValue();
            if (top != slow.data) {
                return false;
            }
            slow = slow.next;
        }
        return true;
    }

    /***
     *
     *
     */

    public static ListNode nthToLast(ListNode head, int k) {

        ListNode p1 = head;
        ListNode p2 = head;

        /* Move p1 k nodes into the list.*/
        for (int i = 0; i < k; i++) {
            if (p1 == null) return null; // Out of bounds
            p1 = p1.next;
        }

        /* Move them at the same pace. When p1 hits the end,
         * p2 will be at the right element. */
        while (p1 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p2;
    }

    // https://leetcode.com/problems/odd-even-linked-list/

    public static Result getTailAndSize(ListNode list) {
        if (list == null) return null;

        int size = 1;
        ListNode current = list;
        while (current.next != null) {
            size++;
            current = current.next;
        }
        return new Result(current, size);
    }

    // https://leetcode.com/problems/merge-k-sorted-lists/

    public static ListNode getKthNode(ListNode head, int k) {
        ListNode current = head;
        while (k > 0 && current != null) {
            current = current.next;
            k--;
        }
        return current;
    }

    public static ListNode findIntersection(ListNode list1, ListNode list2) {
        if (list1 == null || list2 == null) return null;

        /* Get tail and sizes. */
        Result result1 = getTailAndSize(list1);
        Result result2 = getTailAndSize(list2);

        /* If different tail nodes, then there's no intersection. */
        if (result1.tail != result2.tail) {
            return null;
        }

        /* Set pointers to the start of each linked list. */
        ListNode shorter = result1.size < result2.size ? list1 : list2;
        ListNode longer = result1.size < result2.size ? list2 : list1;

        /* Advance the pointer for the longer linked list by the difference in lengths. */
        longer = getKthNode(longer, Math.abs(result1.size - result2.size));

        /* Move both pointers until you have a collision. */
        while (shorter != longer) {
            shorter = shorter.next;
            longer = longer.next;
        }

        /* Return either one. */
        return longer;
    }

    /**
     *
     */

    public static ListNode findBeginning(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        // Find meeting point
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                break;
            }
        }

        // Error check - there is no meeting point, and therefore no loop
        if (fast == null || fast.next == null) {
            return null;
        }

		/* Move slow to Head. Keep fast at Meeting Point. Each are k steps from the Loop Start.
		   If they move at the same pace, they must meet at Loop Start.
		   */

        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        // Both now point to the start of the loop.
        return fast;
    }

    /***
     *
     *
     */


    public static ListNode createLinkedList() {

        int[] vals = {3, 5, 8, 5, 10, 2, 1};
        ListNode head = new ListNode(vals[0], null, null);
        ListNode current = head;
        for (int i = 1; i < vals.length; i++) {
            current = new ListNode(vals[i], null, current);
        }
        return head;
    }

    public static void main(String[] args) {

        System.out.println(createLinkedList().printForward());

        /* Partition */
        ListNode hA = partition(createLinkedList(), 5);

        System.out.println(hA.printForward());

    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode c1 = l1;
        ListNode c2 = l2;
        ListNode sentinel = new ListNode(0);
        ListNode d = sentinel;
        int sum = 0;
        while (c1 != null || c2 != null) {
            sum /= 10;
            if (c1 != null) {
                sum += c1.data;
                c1 = c1.next;
            }
            if (c2 != null) {
                sum += c2.data;
                c2 = c2.next;
            }
            d.next = new ListNode(sum % 10);
            d = d.next;
        }
        if (sum / 10 == 1)
            d.next = new ListNode(1);
        return sentinel.next;
    }

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;

        // step 1. cut the list to two halves
        ListNode prev = null, slow = head, fast = head;

        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        prev.next = null;

        // step 2. sort each half
        ListNode l1 = sortList(head);
        ListNode l2 = sortList(slow);

        // step 3. merge l1 and l2
        return merge(l1, l2);
    }

    ListNode merge(ListNode l1, ListNode l2) {
        ListNode l = new ListNode(0), p = l;

        while (l1 != null && l2 != null) {
            if (l1.data < l2.data) {
                p.next = l1;
                l1 = l1.next;
            } else {
                p.next = l2;
                l2 = l2.next;
            }
            p = p.next;
        }

        if (l1 != null)
            p.next = l1;

        if (l2 != null)
            p.next = l2;

        return l.next;
    }

    public ListNode oddEvenList(ListNode head) {
        if (head != null) {

            ListNode odd = head, even = head.next, evenHead = even;

            while (even != null && even.next != null) {
                odd.next = odd.next.next;
                even.next = even.next.next;
                odd = odd.next;
                even = even.next;
            }
            odd.next = evenHead;
        }
        return head;
    }

    // https://leetcode.com/problems/swap-nodes-in-pairs/

    public ListNode mergeKLists(List<ListNode> lists) {
        if (lists == null || lists.size() == 0) return null;

        PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>(lists.size(), new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                if (o1.data < o2.data)
                    return -1;
                else if (o1.data == o2.data)
                    return 0;
                else
                    return 1;
            }
        });

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        for (ListNode node : lists)
            if (node != null)
                queue.add(node);

        while (!queue.isEmpty()) {
            tail.next = queue.poll();
            tail = tail.next;

            if (tail.next != null)
                queue.add(tail.next);
        }
        return dummy.next;
    }

    // https://leetcode.com/problems/plus-one-linked-list/

    /**
     * https://leetcode.com/problems/rotate-list/
     */

    public ListNode rotateRight(ListNode head, int n) {
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy, slow = dummy;

        int i;
        for (i = 0; fast.next != null; i++)//Get the total length
            fast = fast.next;

        for (int j = i - n % i; j > 0; j--) //Get the i-n%i th node
            slow = slow.next;

        fast.next = dummy.next; //Do the rotation
        dummy.next = slow.next;
        slow.next = null;

        return dummy.next;
    }

    public ListNode swapPairs(ListNode head) {
        if ((head == null) || (head.next == null))
            return head;
        ListNode n = head.next;
        head.next = swapPairs(head.next.next);
        n.next = head;
        return n;
    }

    public ListNode plusOne(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode i = dummy;
        ListNode j = dummy;

        while (j.next != null) {
            j = j.next;
            if (j.data != 9) {
                i = j;
            }
        }

        if (j.data != 9) {
            j.data++;
        } else {
            i.data++;
            i = i.next;
            while (i != null) {
                i.data = 0;
                i = i.next;
            }
        }

        if (dummy.data == 0) {
            return dummy.next;
        }

        return dummy;
    }

    /***
     *
     *
     */

    public static class Result {
        public ListNode tail;
        public int size;

        public Result(ListNode tail, int size) {
            this.tail = tail;
            this.size = size;
        }
    }


}