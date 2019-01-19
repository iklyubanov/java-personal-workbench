package ru.klyubanov.java8.algo.leetcode.ds;

public class AddTwoNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return null;
        }
        int sum = l1.val + l2.val;
        int transferDigit = sum >= 10 ? 1 : 0;
        ListNode r = new ListNode(sum % 10);
        ListNode prev = r;
        l1 = l1.next;
        l2 = l2.next;
        while (l1 != null || l2 != null || transferDigit == 1) {
            sum = (l1 != null ? l1.val : 0) + (l2 != null ? l2.val : 0) + transferDigit;
            transferDigit = sum >= 10 ? 1 : 0;
            ListNode next = new ListNode(sum % 10);
            prev.next = next;
            prev = next;
            if (l1 != null)
                l1 = l1.next;
            if (l2 != null)
                l2 = l2.next;
        }
        return r;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
